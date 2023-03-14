package org.kamenchuk.controller;


import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.service.OrderService;
import org.kamenchuk.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create")
    public OrderCreateResponse create(@RequestParam Long idUser, @RequestBody OrderCreateRequest request) {
        return orderService.create(idUser, request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @PatchMapping(value = "/updateAdmin")
    public OrderResponse updateAdmin(@RequestBody OrderUpdateAdminRequest request, @RequestParam Long idAdmin) {
        return orderService.updateAdmin(request, idAdmin);
    }

    @PatchMapping(value = "/updateClient")
    public OrderResponse updateClient(@RequestBody OrderUpdateClientRequest request) {
        return orderService.updateClient(request);
    }

    @GetMapping(value = "/getOrderByClientId/{id}")
    public List<OrderResponse> getByClientsId(Long idClient) {
        return orderService.getByClientId(idClient);
    }

    @GetMapping(value = "/getAll")
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }
}
