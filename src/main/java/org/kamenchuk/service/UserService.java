package org.kamenchuk.service;

import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.models.UserAuthResponse;

import java.util.List;

/**
 * Interface UserService for work with User
 *
 * @author Lisa Kamenchuk
 */
public interface UserService {

    /**
     * Gets all users
     *
     * @return List<UserResponse>
     */
    List<UserResponse> getAllUsers();

    /**
     * Finds user by id
     *
     * @param id - users id
     * @return UserResponse
     */
    UserResponse findById(Long id);

    /**
     * Creates user
     *
     * @param requestedUser - UserCreateRequest entity
     * @return UserResponse
     */
    UserResponse createUser(UserCreateRequest requestedUser) throws CreationException;

    /**
     * Deletes by users id
     *
     * @param id - users id
     */
    void deleteById(Long id);

    /**
     * Updates users login
     *
     * @param newLogin - new login for update
     * @param id       - users id
     * @return UserResponse
     */
    UserResponse updateLogin(String newLogin, Long id) throws UpdatingException;

    /**
     * get user by login for authentication
     *
     * @param login - users login
     * @return UserAuthResponse
     */
    UserAuthResponse getUserByLogin(String login);

    /**
     * change old role to new
     *
     * @param id   - users id
     * @param role - users new role
     * @return UserResponse
     */
    UserResponse changeUserRole(Long id, RoleResponse role);

}
