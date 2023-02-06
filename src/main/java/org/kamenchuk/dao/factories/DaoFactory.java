package org.kamenchuk.dao.factories;

import org.kamenchuk.dao.impl.CarDaoImpl;
import org.kamenchuk.dao.impl.OrderDaoImpl;
import org.kamenchuk.dao.impl.UserDaoImpl;
import org.kamenchuk.dao.impl.ExtraUsersDaoImpl;


public enum DaoFactory {
    INSTANCE;

    private final UserDaoImpl userDaoImpl = new UserDaoImpl();
    private final ExtraUsersDaoImpl extraUsersDaoImpl = new ExtraUsersDaoImpl();
    private final OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    private final CarDaoImpl carDaoImpl = new CarDaoImpl();


    public UserDaoImpl getUserDao() {
        return userDaoImpl;
    }

    public ExtraUsersDaoImpl getExtraUsersDao() {
        return extraUsersDaoImpl;
    }

    public OrderDaoImpl getOrderDao() {
        return orderDaoImpl;
    }

    public CarDaoImpl getCarDaoImpl() {
        return carDaoImpl;
    }
}
