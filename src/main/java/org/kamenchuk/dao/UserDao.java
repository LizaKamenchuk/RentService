package org.kamenchuk.dao;

import org.kamenchuk.dto.CreateUserDto;

import java.sql.SQLException;

public interface UserDao<UserDto> extends Dao<UserDto> {

    void insert(CreateUserDto user) throws SQLException;
    void update(CreateUserDto user);
}
