package org.kamenchuk.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    /**
     * описание метода
     * @param id
     * @return
     */
    T get(Long id) ;
    List<T> getAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    void insert(T entity) throws SQLException;
}
