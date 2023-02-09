package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.models.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModelDaoImpl implements ModelDao<Model> {

    private final String ID = "carsModels.id",
            MODEL = "carsModels.model",
            MARK_ID_IN_MODELS = "carsModels.idMark",
            ID_MARK = "carsMarks.id",
            MARK = "carsMarks.mark";
    private final String GET_BY_ID = "SELECT " + MARK + ", " + MODEL +
            "FROM carsModels" +
            "JOIN carsMarks ON " + ID_MARK + " = " + MARK_ID_IN_MODELS +
            "WHERE " + ID + " = ?";
    private final String GET_ALL = "SELECT " + MARK + ", " + MODEL +
            "FROM carsModels" +
            "JOIN carsMarks ON " + ID_MARK + " = " + MARK_ID_IN_MODELS;
    private final String DELETE = "DELETE FROM carsModels WHERE " + ID + " =?";
    private final String INSERT = "INSERT INTO carsModels (model,idMark) VALUES (?,?)";

    @Override
    public Optional<Model> getById(Long id) {

        return null;
    }

    @Override
    public List<Model> getAll() {

        return null;
    }

    @Override
    public void update(Model entity) throws SQLException {

    }

    @Override
    public void delete(Model model) throws SQLException {

    }


    @Override
    public void save(Model entity) throws SQLException {

    }
}
