package org.kamenchuk.controller;


import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.OrderService;
import org.kamenchuk.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create")
    public OrderCreateResponse create(@RequestParam Long idUser, @RequestBody OrderCreateRequest request) throws CreationException {
        return orderService.create(idUser, request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @PatchMapping(value = "/admin/update")
    public OrderResponse updateAdmin(@RequestBody OrderUpdateAdminRequest request, @RequestParam Long idAdmin) throws UpdatingException {
        return orderService.updateAdmin(request, idAdmin);
    }

    @PatchMapping(value = "/update")
    public OrderResponse updateClient(@RequestBody OrderUpdateClientRequest request, @RequestParam Long idOrder) throws UpdatingException {
        return orderService.updateClient(request,idOrder);
    }

    @GetMapping(value = "/getOrderByClientId/{id}")
    public List<OrderResponse> getByClientsId(Long idClient) {
        return orderService.getByClientId(idClient);
    }

    @GetMapping(value = "/admin/getAll")
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }
}
