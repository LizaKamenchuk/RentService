package org.kamenchuk.dao;

import org.kamenchuk.dto.ExtraUsersDataDto;
import org.kamenchuk.models.ExtraUsersData;

import java.sql.SQLException;

public interface ExtraUsersDao{

    void save(ExtraUsersData extraUsersData) throws SQLException;
    void update(ExtraUsersDataDto extraUsersDataDto) throws SQLException;
    void delete(ExtraUsersDataDto extraUsersDataDto) throws SQLException;

}
