package org.kamenchuk.service;


import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;

import java.util.List;

public interface OrderService {
    /**
     * Creates order
     * @param idUser - users id from DB
     * @param request - OrderCreateRequest entity
     * @return OrderCreateResponse
     */
    OrderCreateResponse create(Long idUser, OrderCreateRequest request) throws CreationException;

    /**
     * Deletes order bu id
     * @param id - orders id
     */
    void deleteById(Long id);

    /**
     * Methods for admin to update order
     * @param request - OrderUpdateAdminRequest entity
     * @param idAdmin - users id, with role is admin
     * @return OrderResponse
     */
    OrderResponse updateAdmin(OrderUpdateAdminRequest request, Long idAdmin) throws UpdatingException;

    /**
     * Method for client to update order
     * @param request - OrderUpdateClientRequest entity
     * @param idOrder - orders id from DB
     * @return OrderResponse
     */
    OrderResponse updateClient(OrderUpdateClientRequest request,Long idOrder) throws UpdatingException;

    /**
     * Gets orders by clients id
     * @param id - clients id
     * @return List<OrderResponse>
     */
    List<OrderResponse> getByClientId(Long id);

    /**
     * Gets all orders
     * @return List<OrderResponse>
     */
    List<OrderResponse> getAll();


}
