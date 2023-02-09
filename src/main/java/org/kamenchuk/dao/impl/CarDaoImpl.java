package org.kamenchuk.dao.impl;

import org.kamenchuk.dto.CUCarDto;
import org.kamenchuk.models.Car;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Car> getById(Long id){

        return null;
    }

    @Override
    public List<Car> getAll() {

        return null;
    }

    @Override
    public void update(CUCarDto car) throws SQLException {

    }

    @Override
    public void delete(Car car) throws SQLException {
    }

    @Override
    public void save(CUCarDto entity) throws SQLException {

    }

}
