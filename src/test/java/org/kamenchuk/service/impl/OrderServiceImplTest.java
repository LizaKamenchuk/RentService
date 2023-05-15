package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dao.CarDao;
import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.dao.OrdersDao;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.OrderMapper;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Order;
import org.kamenchuk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OrderServiceImpl.class})
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @MockBean
    private  OrdersDao ordersDao;
    @MockBean
    private  UserDao userDao;
    @MockBean
    private  ModelDao modelDao;
    @MockBean
    private  CarDao carDao;
    @MockBean
    private  OrderMapper orderMapper;
    @Test
    void create() throws CreationException {
        Long idUser = 1L;
        Integer idCar = 1;
        Car car = new Car();
        car.setPrice(200);
        User user = new User();
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        OrderCreateRequest request = OrderCreateRequest.builder()
                .startDate(start)
                .finishDate(finish)
                .idCar(idCar)
                .build();
        OrderCreateResponse response = OrderCreateResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .price(1400)
                .build();
        Order order = new Order();
        when(orderMapper.toOrder(request)).thenReturn(order);
        when(userDao.findById(idUser)).thenReturn(Optional.of(user));
        when(carDao.findById(request.getIdCar())).thenReturn(Optional.of(car));
        when(ordersDao.save(order)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(response);
        OrderCreateResponse result = orderService.create(idUser,request);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateAdmin() throws UpdatingException {
        Long idAdmin = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        Car car = Car.builder()
                .id(1)
                .price(200)
                .build();
        OrderUpdateAdminRequest request = OrderUpdateAdminRequest.builder()
                .id(1L)
                .refuseReason(null)
                .status(true)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .adminsLogin("admin")
                .refuseReason(null)
                .status(true)
                .build();
        Order order = Order.builder()
                .id(1L)
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .status(false)
                .car(car)
                .build();
        User admin = User.builder()
                .id(idAdmin)
                .login("admin")
                .build();
        when(ordersDao.findById(request.getId())).thenReturn(Optional.ofNullable(order));
        when(userDao.findById(idAdmin)).thenReturn(Optional.ofNullable(admin));
        order.setStatus(request.getStatus());
        order.setAdminsLogin(admin.getLogin());
        when(orderMapper.toOrderResponse(order)).thenReturn(response);
        when(carDao.findById(car.getId())).thenReturn(Optional.of(car));
        response.setPrice(1400);
        OrderResponse result = orderService.updateAdmin(request,idAdmin);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void updateClient() throws UpdatingException {
        Long idOrder = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        Car car = Car.builder()
                .id(1)
                .price(200)
                .build();
        OrderUpdateClientRequest request = OrderUpdateClientRequest.builder()
                .startDate(start)
                .finishDate(finish)
                .idCar(car.getId())
                .build();
        Order order = Order.builder()
                .id(idOrder)
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .car(car)
                .status(false)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .status(false)
                .build();
        when(ordersDao.findById(idOrder)).thenReturn(Optional.ofNullable(order));
        when(carDao.findById(car.getId())).thenReturn(Optional.of(car));
        when(ordersDao.save(order)).thenReturn(order);
        when(orderMapper.toOrderResponse(order)).thenReturn(response);
        response.setPrice(1400);
        OrderResponse result = orderService.updateClient(request,idOrder);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void getByClientId() {
        Long idClient = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        Car car = Car.builder()
                .id(1)
                .price(200)
                .build();
        Order order = Order.builder()
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .car(car)
                .status(false)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .status(false)
                .build();
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<OrderResponse> responses = new ArrayList<>();
        when(ordersDao.findOrdersByClient_Id(idClient)).thenReturn(orders);
        when(orderMapper.toOrderResponse(order)).thenReturn(response);
        response.setPrice(1400);
        responses.add(response);
       List<OrderResponse> results = orderService.getByClientId(idClient);
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }

    @Test
    void getAll() {
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        Car car = Car.builder()
                .id(1)
                .price(200)
                .build();
        Order order = Order.builder()
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .car(car)
                .status(false)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .refuseReason(null)
                .status(false)
                .build();
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<OrderResponse> responses = new ArrayList<>();
        when(ordersDao.findAll()).thenReturn(orders);
        when(orderMapper.toOrderResponse(order)).thenReturn(response);
        response.setPrice(1400);
        responses.add(response);
        List<OrderResponse> results = orderService.getAll();
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }
}