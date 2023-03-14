package org.kamenchuk.service.impl;

import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dto.mapper.UserMapper;
import org.kamenchuk.dto.userDTO.AllUsersDataResponse;
import org.kamenchuk.dto.userDTO.UserChangeLoginRequest;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            UserMapper userMapper) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userDao.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        return userDao.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " is not found"));
    }

    @Transactional
    @Override
    public UserResponse createUser(UserCreateRequest requestedUser) {
        return Optional.ofNullable(requestedUser)
                .map(userMapper::save)
                .map(this::setUserRole)
                .map(this::setUserED)
                .map(userDao::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not save user"));
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public UserResponse updateLogin(UserChangeLoginRequest requestedUser) {
        return userDao.findById(requestedUser.getId())
                .map(user -> setUserLogin(user, requestedUser.getLogin()))
                .map(userDao::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Update not succeed"));
    }

    @Override
    public AllUsersDataResponse addExtraData(ExtraUsersData extraUsersData, Long id) {
        return userDao.findById(id)
                .map(user-> setExtraData(extraUsersData,user))
                .map(userMapper::toAllDto)
                .orElseThrow(()->new RuntimeException("ExtraUsersData are not added"));
    }


    private User setUserRole(User user) {
        String usersRole ="user";
        //TODO isPresent
        Role role = roleDao.findFirstByRole(usersRole).get();
        user.setRole(role);
        return user;
    }

    private User setUserED(User user) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ExtraUsersData extraUsersData = user.getExtraUsersData();
        extraUsersData.setRegisterDate(sqlDate);
        user.setExtraUsersData(extraUsersData);
        return user;
    }

    private User setUserLogin(User user, String login) {
        user.setLogin(login);
        return user;
    }

    private User setExtraData(ExtraUsersData extraUsersData, User user){
        user.setExtraUsersData(extraUsersData);
        return user;
    }
}

