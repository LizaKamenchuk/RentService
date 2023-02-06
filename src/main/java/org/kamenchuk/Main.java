package org.kamenchuk;

import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.factories.DaoFactory;
import org.kamenchuk.dto.UserDto;
import org.kamenchuk.mapper.UserMapper;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectionPool.INSTANCE.createPool();

        UserMapper userMapper = new UserMapper();

//        Role role = new Role(1,"admin");
//        CreateUserDto user = userMapper.toCreateUserDto(User.builder()
//                .login("newAdmin4")
//                .password("11111")
//                .role(role)
//                .build());
//
//        DaoFactory.INSTANCE.getUserDao().insert(user);

        List<UserDto> users = DaoFactory.INSTANCE.getUserDao().getAll();
        users.stream()
               // .map(userMapper::toUserDto)
                .forEach(System.out::println);
   }
}
