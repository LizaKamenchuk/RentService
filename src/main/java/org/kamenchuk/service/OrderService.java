package org.kamenchuk.service;


import org.kamenchuk.dto.orderDTO.*;

import java.util.List;

public interface OrderService {
    OrderCreateResponse create(Long idUser, OrderCreateRequest request);
    void deleteById(Long id);

    OrderResponse updateAdmin(OrderUpdateAdminRequest request, Long idAdmin);
    OrderResponse updateClient(OrderUpdateClientRequest request);
    List<OrderResponse> getByClientId(Long id);
    List<OrderResponse> getAll();


}
