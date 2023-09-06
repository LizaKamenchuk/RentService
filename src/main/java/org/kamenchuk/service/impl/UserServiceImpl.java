package org.kamenchuk.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.repository.RoleRepository;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;
import org.kamenchuk.models.UserAuthResponse;
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
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleServiceImpl roleService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public UserResponse findById(Long id) {
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
    public UserAuthResponse getUserByLogin(String login) {
        return userRepository.getUserByLogin(login)
                .map(userMapper::toAuthDto)
                .orElseThrow(() -> {
                    log.error("getUserByLogin(). User isn`t found");
                    return new ResourceNotFoundException("User with login = " + login + " is not found");
                });
    }

    @Override
    public UserResponse changeUserRole(Long id, String role) {
        Role newRole = roleService.getRoleByRole(role);
        if(role.isEmpty()){
            throw new ResourceNotFoundException("Role does not exist. Create first this role");
        }else {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> value.setRole(newRole));
            return user
                    .map(userMapper::toDto)
                    .orElseThrow(() -> {
                        log.info("changeUsersRole(). Role isn`t changed");
                        return new ResourceNotFoundException("Role isn`t changed");
                    });
        }
    }

    private User setUserRole(User user) {
        String usersRole = "USER";
        if (roleRepository.findFirstByRole(usersRole).isEmpty()) {
            throw new RuntimeException("Создате роль USER");
        }
        Role role = roleRepository.findFirstByRole(usersRole).get();
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
        if (!login.isEmpty() && !login.isBlank()) user.setLogin(login);
        return user;
    }

    private User setExtraData(ExtraUsersData extraUsersData, User user) {
        user.setExtraUsersData(extraUsersData);
        return user;
    }
}

