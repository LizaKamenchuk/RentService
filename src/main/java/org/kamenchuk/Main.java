package org.kamenchuk;

import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.factories.DaoFactory;
import org.kamenchuk.models.Car;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionPool.INSTANCE.createPool();
        List<Car> cars =  DaoFactory.INSTANCE.getCarDaoImpl().getAll();

        cars.forEach(System.out::println);
    }
}
