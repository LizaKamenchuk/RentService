package org.kamenchuk.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    /**
     * описание метода
     *
     * @param id
     * @return
     */
    Optional<T> getById(Long id) ;
    List<T> getAll() throws SQLException;
    void delete(T entity) throws SQLException;
}
