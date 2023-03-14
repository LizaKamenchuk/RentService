package org.kamenchuk.dto.mapper;

import org.kamenchuk.dto.orderDTO.*;
import org.kamenchuk.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "carMark", source = "car.model.mark.mark")
    @Mapping(target = "carModel", source = "car.model.model")
    @Mapping(target = "userName", source = "client.extraUsersData.name")
    @Mapping(target = "userLastname", source = "client.extraUsersData.lastname")
    OrderCreateResponse toDto(Order order);

    @Mapping(target = "car.id",source = "idCar")
    Order toOrder(OrderCreateRequest request);

    Order toOrder(OrderUpdateAdminRequest request);
    @Mapping(target = "carMark", source = "car.model.mark.mark")
    @Mapping(target = "carModel", source = "car.model.model")
    @Mapping(target = "userName", source = "client.extraUsersData.name")
    @Mapping(target = "userLastname", source = "client.extraUsersData.lastname")
    OrderResponse toOrderResponse(Order order);
    @Mapping(target = "car.id",source = "idCar")
    Order toOrder(OrderUpdateClientRequest request);
}
