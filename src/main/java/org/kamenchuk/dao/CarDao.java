package org.kamenchuk.dao;

import org.kamenchuk.dto.CUCarDto;

import java.sql.SQLException;

public interface CarDao<Car> extends Dao<Car> {

    void save(CUCarDto entity) throws SQLException;
    void update(CUCarDto entity) throws SQLException;

}
