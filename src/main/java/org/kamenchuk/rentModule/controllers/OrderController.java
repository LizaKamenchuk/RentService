package org.kamenchuk.rentModule.controllers;

import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.rentModule.feinClient.FeignOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent_module/order")
public class OrderController {
    private final FeignOrderClient feignOrderClient;

    @Autowired
    public OrderController(FeignOrderClient feignOrderClient) {
        this.feignOrderClient = feignOrderClient;
    }

    @PostMapping(value = "/create")
    public OrderCreateResponse create(@RequestParam Long idUser, @RequestBody OrderCreateRequest request) {
        return feignOrderClient.create(idUser, request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        feignOrderClient.delete(id);
    }

    @PatchMapping(value = "/admin/update")
    public OrderResponse updateAdmin(@RequestBody OrderUpdateAdminRequest request, @RequestParam Long idAdmin) {
        return feignOrderClient.updateAdmin(request, idAdmin);
    }

    @PatchMapping(value = "/update")
    public OrderResponse updateClient(@RequestBody OrderUpdateClientRequest request, @RequestParam Long idOrder) {
        return feignOrderClient.updateClient(request, idOrder);
    }

    @GetMapping(value = "/getOrderByClientId/{id}")
    public List<OrderResponse> getByClientsId(Long idClient) {
        return feignOrderClient.getByClientsId(idClient);
    }

    @GetMapping(value = "/admin/getAll")
    public List<OrderResponse> getAll() {
        return feignOrderClient.getAll();
    }
}
