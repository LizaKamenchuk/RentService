package org.kamenchuk.rentModule.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.rentModule.feinClient.FeignOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OrderController.class})
class OrderControllerTest {
    @Autowired
    private OrderController controller;
    @MockBean
    private FeignOrderClient feignOrderClient;

    @Test
    void create() {
        Long idUser = 1L;
        OrderCreateRequest request = new OrderCreateRequest();
        OrderCreateResponse response = new OrderCreateResponse();
        when(feignOrderClient.create(idUser,request)).thenReturn(response);
        OrderCreateResponse result = controller.create(idUser,request);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void updateAdmin() {
        Long idAdmin = 1L;
        OrderUpdateAdminRequest request = new OrderUpdateAdminRequest();
        OrderResponse response = new OrderResponse();
        when(feignOrderClient.updateAdmin(request,idAdmin)).thenReturn(response);
        OrderResponse result = controller.updateAdmin(request,idAdmin);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void updateClient() {
        Long idOrder = 1L;
        OrderUpdateClientRequest request = new OrderUpdateClientRequest();
        OrderResponse response = new OrderResponse();
        when(feignOrderClient.updateClient(request,idOrder)).thenReturn(response);
        OrderResponse result = controller.updateClient(request,idOrder);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void getByClientsId() {
        Long idClient = 1L;
        List<OrderResponse> responses = new ArrayList<>();
        when(feignOrderClient.getByClientsId(idClient)).thenReturn(responses);
        List<OrderResponse> results = controller.getByClientsId(idClient);
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }

    @Test
    void getAll() {
        List<OrderResponse> responses = new ArrayList<>();
        when(feignOrderClient.getAll()).thenReturn(responses);
        List<OrderResponse> results = controller.getAll();
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }
}