package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements org.kamenchuk.dao.CarDao<Car> {
    private final String ID = "cars.id",
            CAR_NUMBER = "cars.carNumber",
            PRICE = "cars.price",
            LIMITATIONS = "cars.limitations",
            IMAGE_ID = "cars.idImage",
            ID_MODEL_IN_CARS = "cars.idModel",
            MODEL_ID = "carsModels.id",
            ID_MARK_IN_MODELS = "carsModels.idMark",
            MARK_ID = "carsMarks.id",
            MODEL = "carsModels.model",
            MARK = "carsMarks.mark";

    private final String GET_BY_ID = "SELECT " + ID + ", " + CAR_NUMBER + ", " + PRICE + ", "
            + LIMITATIONS + ", " + IMAGE_ID + ", " + MODEL + ", " + MARK +
            "FROM cars" +
            "JOIN carsModels ON "+ID_MODEL_IN_CARS +" = " + MODEL_ID +
            "JOIN carsMarks ON "+ID_MARK_IN_MODELS+" = "+ MARK_ID +
            "WHERE "+ID+" = ?";
    private final String GET_ALL = "SELECT " + ID + ", " + CAR_NUMBER + ", " + PRICE + ", "
            + LIMITATIONS + ", " + IMAGE_ID + ", " + MODEL + ", "+ MARK +
            "FROM cars" +
            "JOIN carsModels ON "+ID_MODEL_IN_CARS +" = " + MODEL_ID +
            "JOIN carsMarks ON "+ID_MARK_IN_MODELS+" = " + MARK_ID;
    private final String UPDATE = "UPDATE cars SET carNumber = ?, "+
            "price = ?, limitations = ?, idImage = ?, idModel = ?";

    private final String DELETE = "DELETE FROM cars WHERE " + ID + " = ?";

    private Car car;
    private ArrayList<Car> cars = new ArrayList<>();

    @Override
    public Car get(Long id){
        try(ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
            ResultSet rs = ps.executeQuery()){
            car = buildCar(rs);
            return car;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAll() {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cars.add(buildCar(rs));
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car car) {
        try(ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ResultSet rs = ps.executeQuery()){
            ps.setString(1,car.getCarNumber());
            ps.setInt(2,car.getPrice());
            ps.setString(3,car.getLimitations());
            ps.setInt(4,car.getModel().getIdModel());
            ps.setInt(5,car.getIdImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car car){
        try(ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ResultSet rs = ps.executeQuery()){
            ps.setInt(1,car.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Car entity) throws SQLException {

    }

    private Car buildCar(ResultSet rs) {
        try (rs) {
            Model model = new Model();
            model.setIdModel(rs.getInt("idModel"));
            return Car.builder()
                    .id(rs.getInt("id"))
                    .carNumber(rs.getString("carNumber"))
                    .price(rs.getInt("price"))
                    .limitations(rs.getString("limitations"))
                    .idImage(rs.getInt("idImage"))
                    .model(model)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
