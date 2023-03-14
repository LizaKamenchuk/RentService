package org.kamenchuk.dao;

import org.kamenchuk.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Order,Long> {
    List<Order> findOrdersByClient_Id(Long idClient);
}
