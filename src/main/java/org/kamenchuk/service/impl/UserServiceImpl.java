package org.kamenchuk.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.User;
import org.kamenchuk.models.UserAuthResponse;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.service.RoleService;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class implements UserService interface
 *
 * @author Liza Kamenchuk
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("findById(). User isn`t found");
                    return new ResourceNotFoundException("User with id = " + id + " is not found");
                });
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest requestedUser) throws CreationException {
        return Optional.ofNullable(requestedUser)
                .map(userMapper::save)
                .map(this::setUserRole)
                .map(this::setUserED)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("createUser(). Can not create user");
                    return new CreationException("Can not save user");
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse updateLogin(String newLogin, Long id) throws UpdatingException {
        return userRepository.findById(id)
                .map(user -> setUserLogin(user, newLogin))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("updateLogin(). Login update isn`t succeed");
                    return new UpdatingException("Update not succeed");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public UserAuthResponse getUserByLogin(String login) {
        return userRepository.getUserByLogin(login)
                .map(userMapper::toAuthDto)
                .orElseThrow(() -> {
                    log.error("getUserByLogin(). User isn`t found");
                    return new ResourceNotFoundException("User with login = " + login + " is not found");
                });
    }

    @Override
    @Transactional
    public UserResponse changeUserRole(Long id, RoleResponse role) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setRole(roleService.getRoleByRole(role.getRole()));
                    return user;
                })
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.info("changeUsersRole(). Role isn`t changed");
                    return new ResourceNotFoundException("Role isn`t changed");
                });

    }

    private User setUserRole(User user) {
        try {
            user.setRole(roleService.getRoleByRole("USER"));
            return user;
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException(exception.getMessage() + ". Создате роль USER");
        }
    }

    private User setUserED(User user) {
        LocalDate date = LocalDate.now();
        ExtraUsersData extraUsersData = user.getExtraUsersData();
        extraUsersData.setRegisterDate(date);
        user.setExtraUsersData(extraUsersData);
        return user;
    }

    private User setUserLogin(User user, String login) {
        if (!login.isEmpty() && !login.isBlank()) user.setLogin(login);
        return user;
    }

}

