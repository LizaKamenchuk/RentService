package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplIntegrationTest extends PostgresContainer {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleDao roleDao;


    @Order(3)
    @Test
    void getAllUsers() {
        List<UserResponse> results = userService.getAllUsers();
        assertAll(() -> {
            assertNotNull(results);
            assertEquals(results.size(), 2);
        });
    }

    @Order(2)
    @Test
    void findById() throws CreationException {
        UserResponse response = userService.createUser(new UserCreateRequest("login", "password", "name", "lastname"));
        UserResponse result = userService.findById(response.getId());
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, response);
        });
    }

    @Order(1)
    @Test
    void createUser() throws CreationException {
        Role role = Role.builder().role("USER").build();
        roleDao.save(role);
        UserCreateRequest request = UserCreateRequest.builder()
                .login("log")
                .name("name")
                .password("pas")
                .lastname("lastname")
                .build();
        UserResponse result = userService.createUser(request);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getLogin(), request.getLogin());
            assertNotEquals(result.getId(), null);
        });
    }


    @Order(4)
    @Test
    void deleteById() throws CreationException {
        UserResponse response = userService.createUser(new UserCreateRequest("loginLogin", "pass", "name", "lastname"));
        userService.deleteById(response.getId());
        assertThrows(Exception.class, () -> userService.findById(response.getId()));
    }

    @Order(5)
    @Test
    void updateLogin() throws UpdatingException {
        String newLogin = "newLogin";
        UserResponse result = userService.updateLogin(newLogin, 1L);
        assertEquals(result.getLogin(), newLogin);
        assertEquals(result.getLogin(), userService.findById(1L).getLogin());
    }
}