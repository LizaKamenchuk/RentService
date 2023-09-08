package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.RoleService;
import org.kamenchuk.service.UserService;
import org.kamenchuk.service.impl.ExtraUsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExtraUsersServiceImplIntegrationTest extends PostgresContainer {
    @Autowired
    private ExtraUsersServiceImpl service;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @Order(1)
    @Test
    void getExtraDataById() throws CreationException, ResourceNotFoundException {
        roleService.create(new RoleResponse("USER"));
        UserCreateRequest request = new UserCreateRequest("login1", "password", "name", "lastname");
        UserResponse userResponse = userService.createUser(request);
        ExtraUserDataUpdateRequest response = service.getExtraDataById(userResponse.getIdED());
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(response.getName(), request.getName());
        });
    }

    @Order(2)
    @Test
    void updateExtraData() throws UpdatingException {
        ExtraUserDataUpdateRequest request = ExtraUserDataUpdateRequest.builder()
                .name("newName")
                .lastname("newLastname")
                .phone("+375225696300")
                .build();
        ExtraUserDataUpdateRequest result = service.updateExtraData(request, 1L);
        assertAll(() -> {
            assertEquals(request.getName(), result.getName());
            assertEquals(request.getLastname(), result.getLastname());
            assertEquals(request.getPhone(), result.getPhone());
        });
    }
}