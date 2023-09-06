package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.repository.CarRepository;
import org.kamenchuk.repository.ModelRepository;
import org.kamenchuk.repository.OrdersRepository;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.OrderMapper;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Order;
import org.kamenchuk.models.User;
import org.kamenchuk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Class implements OrderService interface
 *
 * @author Liza Kamenchuk
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final CarRepository carRepository;
    private final OrderMapper orderMapper;

    @Autowired
    OrderServiceImpl(OrdersRepository ordersRepository,
                     UserRepository userRepository,
                     OrderMapper orderMapper,
                     CarRepository carRepository,
                     ModelRepository modelRepository) {
        this.ordersRepository = ordersRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.modelRepository = modelRepository;
    }

    @Transactional
    @Override
    public OrderCreateResponse create(Long idUser, OrderCreateRequest request) throws CreationException {
        return Optional.ofNullable(request)
                .map(orderMapper::toOrder)
                .map(order -> setUserStatusCar(idUser,order,request))
                .map(ordersRepository::save)
                .map(orderMapper::toDto)
                .map(response -> setPrice(response,request.getIdCar()))
                .orElseThrow(() -> {
                    log.error("create(). Order isn`t created");
                    return new CreationException("Order isn`t created");
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderResponse updateAdmin(OrderUpdateAdminRequest request, Long idAdmin) throws UpdatingException {
        try {
            Order order = ordersRepository.findById(request.getId()).get();
            order = setAdminsUpdates(request, order, idAdmin);
            Integer idCar = order.getCar().getId();
            OrderResponse response = orderMapper.toOrderResponse(order);
            response = setPrice(response, idCar);
            return response;
        }catch (Exception e){
            log.error("updateAdmin(). Order isn`t updated by admin");
            throw new UpdatingException("Order isn`t updated");
        }


    }

    @Override
    @Transactional
    public OrderResponse updateClient(OrderUpdateClientRequest request,Long idOrder) throws UpdatingException {
        return ordersRepository.findById(idOrder)
                .map(order -> setClientsUpdates(request, order))
                .map(ordersRepository::save)
                .map(orderMapper::toOrderResponse)
                .map(orderResponse -> setPrice(orderResponse, request.getIdCar()))
                .orElseThrow(() -> {
                    log.error("updateClient(). Order isn`t updated by client");
                    return new UpdatingException("It does not update");
                });
    }

    @Override
    @Transactional
    public List<OrderResponse> getByClientId(Long id) {
        return ordersRepository.findOrdersByClient_Id(id).stream()
                .map(this::setPriceForOrdersList)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderResponse> getAll() {
        return ordersRepository.findAll().stream()
                .map(this::setPriceForOrdersList)
                .collect(Collectors.toList());
    }

    private Order setUserStatusCar(Long idUser, Order order, OrderCreateRequest request) throws ResourceNotFoundException {
        Integer carId = request.getIdCar();
        if(userRepository.findById(idUser).isEmpty() || carRepository.findById(carId).isEmpty()) throw new ResourceNotFoundException("User or car does not exist");
        else {
        User user = userRepository.findById(idUser).get();
        Car car = carRepository.findById(carId).get();
        order.setClient(user);
        order.setCar(car);
        order.setStatus(false);
        }
        return order;
    }

    private Order setAdminsUpdates(OrderUpdateAdminRequest request, Order order, Long idAdmin) {
        if(userRepository.findById(idAdmin).isEmpty()) throw new ResourceNotFoundException("Admin with this id does not exist");
        else {
            User admin = userRepository.findById(idAdmin).get();
            if (request.getRefuseReason() != null) order.setRefuseReason(request.getRefuseReason());
            if (request.getStatus() != null) order.setStatus(request.getStatus());
            order.setAdminsLogin(admin.getLogin());
        }
        return order;
    }

    private Order setClientsUpdates(OrderUpdateClientRequest request, Order order) throws ResourceNotFoundException {
        if (request.getFinishDate() != null) order.setFinishDate(request.getFinishDate());
        if (request.getStartDate() != null) order.setStartDate(request.getStartDate());
        if (request.getIdCar() != null) {
            if(carRepository.findById(request.getIdCar()).isEmpty())
                throw new ResourceNotFoundException("This car does not exist");
            else {
                Car newCar = carRepository.findById(request.getIdCar()).get();
                order.setCar(newCar);
            }
        }
        return order;
    }

    private int findDayAmount(LocalDate start, LocalDate finish){
        int days =(finish.getYear()-start.getYear())*365;
        //TODO учесть месяцы
        days += Math.abs(finish.getMonthValue()-start.getMonthValue())*30;
        days += Math.abs(finish.getDayOfMonth()-start.getDayOfMonth());
        return days;
    }

    private OrderResponse setPrice(OrderResponse response,Integer idCar){
        Car car = carRepository.findById(idCar).get();
        Integer price = car.getPrice();
        int days = findDayAmount(response.getStartDate(),response.getFinishDate());
        price *= days;
        response.setPrice(price);
        return response;
    }
    private OrderCreateResponse setPrice(OrderCreateResponse response,Integer idCar){
        Car car = carRepository.findById(idCar).get();
        Integer price = car.getPrice();
        int days = findDayAmount(response.getStartDate(),response.getFinishDate());
        price *= days;
        response.setPrice(price);
        return response;
    }

    private OrderResponse setPriceForOrdersList(Order order) {
        Integer price = order.getCar().getPrice();
        int day = findDayAmount(order.getStartDate(), order.getFinishDate());
        price *= day;
        OrderResponse response = orderMapper.toOrderResponse(order);
        response.setPrice(price);
        return response;
    }
}