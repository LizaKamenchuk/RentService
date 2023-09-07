package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long> {
    List<Order> findOrdersByClient_Id(@NotNull Long idClient);
}
