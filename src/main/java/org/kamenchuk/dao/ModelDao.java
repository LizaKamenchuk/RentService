package org.kamenchuk.dao;

import java.sql.SQLException;

public interface ModelDao<Model> extends Dao<Model> {
    void save(Model entity) throws SQLException;
    void update(Model entity) throws SQLException;
}
