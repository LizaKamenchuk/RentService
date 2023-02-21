package org.kamenchuk.dao;

import org.kamenchuk.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository<Order,Long> {

}
