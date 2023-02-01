package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.models.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelDaoImpl implements ModelDao<Model> {

    private final String ID = "carsModels.id",
            MODEL = "carsModels.model",
            MARK_ID_IN_MODELS = "carsModels.idMark",
            ID_MARK = "carsMarks.id",
            MARK = "carsMarks.mark";
    private final String GET_BY_ID = "SELECT " + MARK + ", " + MODEL +
            "FROM carsModels" +
            "JOIN carsMarks ON "+ID_MARK+" = "+MARK_ID_IN_MODELS+
            "WHERE "+ID+" = ?";
    private final String GET_ALL = "SELECT " + MARK + ", " + MODEL +
            "FROM carsModels" +
            "JOIN carsMarks ON "+ID_MARK+" = "+MARK_ID_IN_MODELS;
    private final String DELETE ="DELETE FROM carsModels WHERE "+ID+" =?";

    ArrayList<Model> models = new ArrayList<>();

    @Override
    public Model get(Long id)  {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
             ResultSet rs = ps.executeQuery(GET_BY_ID)) {
            ps.setLong(1,id);
            Model model = buildModel(rs);
            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Model> getAll() {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery(GET_ALL)) {
           while (rs.next()){
               models.add(buildModel(rs));
           }
            return models;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Model entity, String[] params) throws SQLException {

    }

    @Override
    public void delete(Model model) throws SQLException {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
             ResultSet rs = ps.executeQuery(GET_BY_ID)) {
            ps.setLong(1,model.getIdModel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Model entity) throws SQLException {

    }

    private Model buildModel(ResultSet rs) throws SQLException {
        return Model.builder()
                .idModel(rs.getInt("id"))
                .model(rs.getString("model"))
                .idMark(rs.getInt("idMark"))
                .mark(rs.getString("mark"))
                .build();
    }
}
