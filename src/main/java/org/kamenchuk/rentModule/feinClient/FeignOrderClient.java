package org.kamenchuk.rentModule.feinClient;

import org.kamenchuk.dto.orderDTO.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "feignOrderClient", url = "http://localhost:8080/rent_module/order")
public interface FeignOrderClient {

    @PostMapping(value = "/create")
     OrderCreateResponse create(@RequestParam Long idUser, @RequestBody OrderCreateRequest request);

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Long id);

    @PatchMapping(value = "/admin/update")
    OrderResponse updateAdmin(@RequestBody OrderUpdateAdminRequest request, @RequestParam Long idAdmin);

    @PatchMapping(value = "/update")
    OrderResponse updateClient(@RequestBody OrderUpdateClientRequest request, @RequestParam Long idOrder);

    @GetMapping(value = "/getOrderByClientId/{id}")
     List<OrderResponse> getByClientsId(Long idClient);

    @GetMapping(value = "/admin/getAll")
     List<OrderResponse> getAll();
}
