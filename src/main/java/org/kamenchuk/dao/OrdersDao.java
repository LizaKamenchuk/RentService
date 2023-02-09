package org.kamenchuk.dao;

import org.kamenchuk.dto.UForAdminOrderDto;
import org.kamenchuk.dto.UForUserOrderDto;

import java.sql.SQLException;

public interface OrdersDao<Order> extends Dao<Order> {

    void updateForAdmin(UForAdminOrderDto entity) throws SQLException;
    void save(Order entity) throws SQLException;
    void updateForUser(UForUserOrderDto entity) throws SQLException;
}
