package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.OrdersDao;
import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Order;
import org.kamenchuk.models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrdersDao<Order> {

    private final String ID = "id",
            START_DATE = "startDate",
            FINISH_DATE = "finishDate",
            STATUS = "status",
            CAR = "idCar",
            CLIENT = "idUser",
            ADMINS_LOGIN = "adminsLogin",
            REFUSE_REASON = "refuseReason";

    private ArrayList<Order> orders = new ArrayList<>();
    private final String GET_BY_ID = "SELECT * FROM orders WHERE " + ID + " = ? ";
    private final String GET_ALL = "SELECT * FROM orders";
    private final String DELETE = "DELETE FROM orders WHERE " + ID + " = ?";
    private final String  UPDATE = "UPDATE orders SET "+START_DATE+" = ?, "+FINISH_DATE+" = ?, "+ CAR +" = ? "+
            "WHERE "+ID+" = ?";
    private final String UPDATE_FOR_ADMIN = "UPDATE orders SET "+STATUS+" =?,"+ REFUSE_REASON +" =?"+
            "WHERE "+ ID + " =?";

    @Override
    public Order get(Long id) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
             ResultSet rs = ps.executeQuery(GET_BY_ID)) {
            ps.setLong(1, id);
            Order order = buildOrder(rs);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery(GET_ALL)) {
            while (rs.next()) {
                orders.add(buildOrder(rs));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order, String[] params) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE);
             ResultSet rs = ps.executeQuery(UPDATE)) {
            ps.setDate(1, Date.valueOf(order.getStartDate()));
            ps.setDate(2,Date.valueOf(order.getFinishDate()));
            ps.setInt(3,order.getCar().getId());
            ps.setLong(4,order.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateForAdmin(Order order) throws SQLException {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_FOR_ADMIN);
             ResultSet rs = ps.executeQuery(UPDATE_FOR_ADMIN)) {
           ps.setBoolean(1,order.getStatus());
            ps.setLong(3,order.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Order order) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE);
             ResultSet rs = ps.executeQuery(DELETE)) {
            ps.setLong(1, order.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Order entity) throws SQLException {

    }

    private Order buildOrder(ResultSet rs) throws SQLException {
        Car car = new Car();
        User user = new User();
        car.setId(rs.getInt(CAR));
        user.setId(rs.getLong(CLIENT));
        return Order.builder()
                .id(rs.getLong(ID))
                .startDate(rs.getDate(START_DATE).toLocalDate())
                .finishDate(rs.getDate(FINISH_DATE).toLocalDate())
                .status(rs.getBoolean(STATUS))
                .car(car)
                .client(user)
                .adminsLogin(rs.getString(ADMINS_LOGIN))
                .refuseReason(rs.getString(REFUSE_REASON))
                .build();
    }


}
