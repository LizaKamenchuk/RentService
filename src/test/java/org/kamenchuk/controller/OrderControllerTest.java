package org.kamenchuk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderController.class})
class OrderControllerTest {
    @Autowired
    private OrderController orderController;
    @MockBean
    private OrderServiceImpl orderService;

    @Test
    void create() throws CreationException {
        Long idUser = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        OrderCreateRequest request = OrderCreateRequest.builder()
                .startDate(start)
                .finishDate(finish)
                .idCar(1)
                .build();
        OrderCreateResponse response = OrderCreateResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .carMark("BMW")
                .carModel("X5")
                .userName("Mike")
                .userLastname("Jons")
                .price(1400)
                .build();
        when(orderService.create(idUser, request)).thenReturn(response);
        OrderCreateResponse result = orderController.create(idUser, request);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, response);
        });

    }

    @Test
    void delete() {
    }

    @Test
    void updateAdmin() throws UpdatingException {
        Long idAdmin = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        OrderUpdateAdminRequest request = OrderUpdateAdminRequest.builder()
                .id(1L)
                .refuseReason(null)
                .status(true)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .carMark("BMW")
                .carModel("X5")
                .userName("Mike")
                .userLastname("Jons")
                .price(1400)
                .adminsLogin("admin")
                .refuseReason(null)
                .status(true)
                .build();
        when(orderService.updateAdmin(request, idAdmin)).thenReturn(response);
        OrderResponse result = orderController.updateAdmin(request, idAdmin);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, response);
        });
    }

    @Test
    void updateClient() throws UpdatingException {
        Long idOrder = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 26);
        OrderUpdateClientRequest request = OrderUpdateClientRequest.builder()
                .startDate(start)
                .finishDate(finish)
                .idCar(1)
                .build();
        OrderResponse response = OrderResponse.builder()
                .startDate(start)
                .finishDate(finish)
                .carMark("BMW")
                .carModel("X5")
                .userName("Mike")
                .userLastname("Jons")
                .price(1400)
                .adminsLogin("admin")
                .refuseReason(null)
                .status(true)
                .build();
        when(orderService.updateClient(request,idOrder)).thenReturn(response);
        OrderResponse result = orderController.updateClient(request,idOrder);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getAdminsLogin(), response.getAdminsLogin());
        });
        verify(orderService).updateClient(request,idOrder);
    }

    @Test
    void getByClientsId() {
        Long idClient = 1L;
        List<OrderResponse> responseList = new ArrayList<>();
        when(orderService.getByClientId(idClient)).thenReturn(responseList);
        List<OrderResponse> resultList = orderController.getByClientsId(idClient);
        assertEquals(resultList,responseList);
    }

    @Test
    void getAll() {
        List<OrderResponse> responseList = new ArrayList<>();
        when(orderService.getAll()).thenReturn(responseList);
        List<OrderResponse> resultList = orderController.getAll();
        assertEquals(resultList,responseList);
    }
}