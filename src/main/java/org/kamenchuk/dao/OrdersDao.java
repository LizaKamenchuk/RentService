package org.kamenchuk.dao;

import java.sql.SQLException;

public interface OrdersDao<Order> extends Dao<Order> {

    void updateForAdmin(Order entity) throws SQLException;
}
