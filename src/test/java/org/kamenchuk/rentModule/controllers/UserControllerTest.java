package org.kamenchuk.rentModule.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.rentModule.feinClient.FeignUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserController.class})
class UserControllerTest {

    @Autowired
    private UserController controller;
    @MockBean
    private FeignUserClient feignUserClient;

    @Test
    void getAllUser() {
        List<UserResponse> responses = new ArrayList<>();
        when(feignUserClient.getAllUser()).thenReturn(responses);
        List<UserResponse> results = controller.getAllUser();
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }

    @Test
    void findById() {
        Long id =1L;
        UserResponse response = new UserResponse();
        when(feignUserClient.findById(id)).thenReturn(response);
        UserResponse result = controller.findById(id);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void create() {
        UserCreateRequest request = new UserCreateRequest();
        UserResponse response = new UserResponse();
        when(feignUserClient.create(request)).thenReturn(response);
        UserResponse result = controller.create(request);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void delete() {
    }

    @Test
    void updateLogin() {
        String login = "login";
        Long id = 1L;
        UserResponse response = UserResponse.builder()
                .login(login)
                .id(id)
                .build();
        when(feignUserClient.updateLogin(login,id)).thenReturn(response);
        UserResponse result = controller.updateLogin(login,id);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}