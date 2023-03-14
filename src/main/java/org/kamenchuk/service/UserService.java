package org.kamenchuk.service;

import org.kamenchuk.dto.userDTO.AllUsersDataResponse;
import org.kamenchuk.dto.userDTO.UserChangeLoginRequest;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.models.ExtraUsersData;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse findById(Long id);

    UserResponse createUser(UserCreateRequest requestedUser);

    void deleteById(Long id);

    UserResponse updateLogin(UserChangeLoginRequest requestedUser);

    AllUsersDataResponse addExtraData(ExtraUsersData extraUsersData,Long id);
}
