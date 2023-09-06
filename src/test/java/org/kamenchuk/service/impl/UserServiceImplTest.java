package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.repository.RoleRepository;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO почему названеи клсса и файла отличаются?
/**
 * Test for {@link UserService}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private RoleServiceImpl roleService;
    @Autowired
    UserService userService;

    @Test
    void getAllUsersSuccessTest() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = List.of(user1, user2);

        UserResponse userResponse1 = new UserResponse();
        UserResponse userResponse2 = new UserResponse();
        List<UserResponse> userResponses = List.of(userResponse1, userResponse2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(user1)).thenReturn(userResponse1);
        when(userMapper.toDto(user2)).thenReturn(userResponse2);

        List<UserResponse> result = userService.getAllUsers();

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(result, userResponses);
                }
        );
    }

    @Test
    void getAllUsersNotFoundTest() {
        when(userRepository.findAll()).thenReturn(new ArrayList<User>());
        List<UserResponse> result = userService.getAllUsers();

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(0, result.size());
                }
        );
    }

    @Test
    void findByIdSuccessTest() {
        User user = getUser(getExtraUsersData());
        UserResponse userResponse = getUserResponse();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userMapper.toDto(user)).thenReturn(userResponse);

        UserResponse result = userService.findById(1L);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, userResponse);
        });
    }

    @Test
    void createUserSuccess() throws CreationException {
        UserCreateRequest createRequest = getUserCreateRequest();
        User user = getUser(getExtraUsersData());
        UserResponse response = getUserResponse();
        when(userMapper.save(createRequest)).thenReturn(user);
        when(roleRepository.findFirstByRole(user.getRole().getRole())).thenReturn(Optional.ofNullable(user.getRole()));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(response);

        UserResponse result = userService.createUser(createRequest);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, response);
        });
        verify(userRepository).save(user);
    }

    @Test
    void deleteByIdSuccessTest() {
        userService.deleteById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void updateLoginSuccessTest() throws UpdatingException {
        User user = getUser(getExtraUsersData());
        String login = "newLogin";
        Long id = 1L;
        UserResponse response = getUserResponse();
        response.setLogin(login);
        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        user.setLogin(login);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(response);

        UserResponse result = userService.updateLogin(login,id);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, response);
        });
        verify(userRepository).saveAndFlush(user);
    }

    private static User getUser(ExtraUsersData extraUsersData) {
        return User.builder()
                .id(1L)
                .login("login")
                .password("password")
                .role(new Role(2, "USER"))
                .extraUsersData(extraUsersData)
                .build();
    }

    private static ExtraUsersData getExtraUsersData() {

        return ExtraUsersData.builder()
                .id(1L)
                .idPassport(null)
                .name("name")
                .lastname("lastname")
                .dateOfBirth(null)
                .drivingLicense(null)
                .phone(null)
                .registerDate(LocalDate.now())
                .build();
    }

    private static UserCreateRequest getUserCreateRequest() {
        return UserCreateRequest.builder()
                .login("login")
                .password("password")
                .name("name")
                .lastname("lastname")
                .build();
    }

    private static UserResponse getUserResponse() {
        return UserResponse.builder()
                .id(1L)
                .login("login")
                .roleResponse(new RoleResponse("USER"))
                .build();
    }

}