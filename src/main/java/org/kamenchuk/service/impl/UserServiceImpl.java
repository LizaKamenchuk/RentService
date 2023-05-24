package org.kamenchuk.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO см CarServiceImpl
@Service
@Slf4j
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
    @Transactional
    public List<UserResponse> getAllUsers() {
        return userDao.findAll().stream()
                .map(it->it)
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    @Override
    public UserResponse findById(Long id) {
        return userDao.findById(id)
                .map(it->it)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("findById(). User isn`t found");
                    return new ResourceNotFoundException("User with id = " + id + " is not found");
                });
    }

    @Transactional
    @Override
    public UserResponse createUser(UserCreateRequest requestedUser) throws CreationException {
        return Optional.ofNullable(requestedUser)
                .map(userMapper::save)
                .map(this::setUserRole)
                .map(this::setUserED)
                .map(userDao::save)
                .map(it->it)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("createUser(). Can not create user");
                    return new CreationException("Can not save user");
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public UserResponse updateLogin(String newLogin, Long id) throws UpdatingException {
        return userDao.findById(id)
                .map(it->it)
                .map(user -> setUserLogin(user, newLogin))
                .map(it->it)
                .map(userDao::saveAndFlush)
                .map(it->it)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("updateLogin(). Login update isn`t succeed");
                    return new UpdatingException("Update not succeed");
                });
    }

    private User setUserRole(User user) {
        String usersRole = "USER";
        //TODO isPresent
        Role role = roleDao.findFirstByRole(usersRole).get();
        user.setRole(role);
        return user;
    }

    private User setUserED(User user) {
        LocalDate date = LocalDate.now();
        ExtraUsersData extraUsersData = user.getExtraUsersData();
        extraUsersData.setRegisterDate(date);
        user.setExtraUsersData(extraUsersData);
        return user;
    }

    private User setUserLogin(User user, String login) {
        if (!login.isEmpty()) user.setLogin(login);
        return user;
    }

    private User setExtraData(ExtraUsersData extraUsersData, User user) {
        user.setExtraUsersData(extraUsersData);
        return user;
    }
}

