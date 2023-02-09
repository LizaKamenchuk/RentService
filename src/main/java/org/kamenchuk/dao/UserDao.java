package org.kamenchuk.dao;

import org.kamenchuk.dto.UCUserDto;

public interface UserDao<User> extends Dao<User> {

    void save(UCUserDto user);
    void update(UCUserDto user);
}
