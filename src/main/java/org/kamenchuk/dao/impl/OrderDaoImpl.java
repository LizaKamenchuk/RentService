package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.OrdersDao;
import org.kamenchuk.dto.UForAdminOrderDto;
import org.kamenchuk.dto.UForUserOrderDto;
import org.kamenchuk.models.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrdersDao<Order> {

    private final String ID = "id",
            START_DATE = "startDate",
            FINISH_DATE = "finishDate",
            STATUS = "status",
            CAR = "idCar",
            CLIENT = "idUser",
            ADMINS_LOGIN = "adminsLogin",
            REFUSE_REASON = "refuseReason";

    private final String GET_BY_ID = "SELECT * FROM orders WHERE " + ID + " = ? ";
    private final String GET_ALL = "SELECT * FROM orders";
    private final String DELETE = "DELETE FROM orders WHERE " + ID + " = ?";
    private final String UPDATE = "UPDATE orders SET " + START_DATE + " = ?, " + FINISH_DATE + " = ?, " + CAR + " = ? " +
            "WHERE " + ID + " = ?";
    private final String UPDATE_FOR_ADMIN = "UPDATE orders SET "
            + STATUS + " =?," + REFUSE_REASON + " =?" + ADMINS_LOGIN + " =?" + REFUSE_REASON + " =?" +
            "WHERE " + ID + " =?";
    private final String INSERT = "INSERT INTO orders ("
            + START_DATE + ", " + FINISH_DATE + ", " + STATUS + ", " + CAR + ", " + CLIENT + ADMINS_LOGIN + ", " + REFUSE_REASON +
            ") VALUES (?,?,?,?,?,?)";

    @Override
    public Optional<Order> getById(Long id) {

        return null;
    }

    @Override
    public List<Order> getAll() {

        return null;
    }

    @Override
    public void updateForUser(UForUserOrderDto order) {

    }

    @Override
    public void updateForAdmin(UForAdminOrderDto order) throws SQLException {

    }

    @Override
    public void delete(Order order) {

    }


    @Override
    public void save(Order entity) throws SQLException {

    }

}
