package org.kamenchuk.dao;

import java.sql.SQLException;

public interface ExtraUsersDao<User>  {
    void update(User entity, String[] params) throws SQLException;
    void delete(User entity) throws SQLException;
}
