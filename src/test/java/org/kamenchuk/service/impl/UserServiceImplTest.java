package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dto.mapper.UserMapper;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserService}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceTest {
    @MockBean
    private UserDao userDao;
    @MockBean
    private RoleDao roleDao;
    @MockBean
    private UserMapper userMapper;

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

        when(userDao.findAll()).thenReturn(users);
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
        UserResponse userResponse1 = new UserResponse();
        UserResponse userResponse2 = new UserResponse();
        List<UserResponse> userResponses = List.of(userResponse1, userResponse2);

        when(userDao.findAll()).thenReturn(new ArrayList<User>());
        List<UserResponse> result = userService.getAllUsers();

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(0, result.size());
                }
        );
    }

}