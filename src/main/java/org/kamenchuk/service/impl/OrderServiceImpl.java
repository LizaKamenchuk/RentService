package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.CarMapper;
import org.kamenchuk.mapper.OrderMapper;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.Order;
import org.kamenchuk.repository.OrdersRepository;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.OrderService;
import org.kamenchuk.service.UserService;
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
    //    private final UserRepository userRepository;
//    private final CarRepository carRepository;
    private final OrderMapper orderMapper;
    private final CarService carService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CarMapper carMapper;

    @Autowired
    OrderServiceImpl(OrdersRepository ordersRepository,
                     OrderMapper orderMapper,
                     CarService carService,
                     UserService userService,
                     UserMapper userMapper,
                     CarMapper carMapper) {
        this.ordersRepository = ordersRepository;
        this.userService = userService;
        this.orderMapper = orderMapper;
        this.carService = carService;
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    @Transactional
    @Override
    public OrderCreateResponse create(Long idUser, OrderCreateRequest request) throws CreationException {
        return Optional.ofNullable(request)
                .map(orderMapper::toOrder)
                .map(order -> setUserStatusCar(idUser, order, request))
                .map(ordersRepository::save)
                .map(orderMapper::toDto)
                .map(response -> setPrice(response, request.getIdCar()))
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
            Order order = ordersRepository.findById(request.getId()).orElseThrow(() -> {
                throw new ResourceNotFoundException(String.format("Order with %s is not exist", request.getId()));
            });
            setAdminsUpdates(request, order, idAdmin);
            Integer idCar = order.getCar().getId();
            OrderResponse response = orderMapper.toOrderResponse(order);
            setPrice(response, idCar);
            return response;
        } catch (Exception e) {
            log.error("updateAdmin(). Order isn`t updated by admin");
            throw new UpdatingException("Order isn`t updated");
        }


    }

    @Override
    @Transactional
    public OrderResponse updateClient(OrderUpdateClientRequest request, Long idOrder) throws UpdatingException, ResourceNotFoundException {
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
        UserResponse user = userService.findById(idUser);
        CarResponse car = carService.getCarById(carId);
        order.setClient(userMapper.toUser(user));
        order.setCar(carMapper.toCar(car));
        order.setStatus(false);
        return order;
    }

    private Order setAdminsUpdates(OrderUpdateAdminRequest request, Order order, Long idAdmin) {
        UserResponse admin = userService.findById(idAdmin);
        if (request.getRefuseReason() != null) order.setRefuseReason(request.getRefuseReason());
        if (request.getStatus() != null) order.setStatus(request.getStatus());
        order.setAdminsLogin(admin.getLogin());
        return order;
    }

    private Order setClientsUpdates(OrderUpdateClientRequest request, Order order) throws ResourceNotFoundException {
        if (request.getFinishDate() != null) order.setFinishDate(request.getFinishDate());
        if (request.getStartDate() != null) order.setStartDate(request.getStartDate());
        if (request.getIdCar() != null) {
            CarResponse newCar = carService.getCarById(request.getIdCar());
            order.setCar(carMapper.toCar(newCar));
        }
        return order;
    }

    private int findDayAmount(LocalDate start, LocalDate finish) {
        int days = (finish.getYear() - start.getYear()) * 365;
        //TODO учесть месяцы
        days += Math.abs(finish.getMonthValue() - start.getMonthValue()) * 30;
        days += Math.abs(finish.getDayOfMonth() - start.getDayOfMonth());
        return days;
    }

    private OrderResponse setPrice(OrderResponse response, Integer idCar) throws ResourceNotFoundException {
        CarResponse car = carService.getCarById(idCar);
        Integer price = car.getPrice();
        int days = findDayAmount(response.getStartDate(), response.getFinishDate());
        price *= days;
        response.setPrice(price);
        return response;
    }

    private OrderCreateResponse setPrice(OrderCreateResponse response, Integer idCar) {
        CarResponse car = carService.getCarById(idCar);
        Integer price = car.getPrice();
        int days = findDayAmount(response.getStartDate(), response.getFinishDate());
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