package org.kamenchuk.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.models.roleDTO.RoleResponse;
import org.kamenchuk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserController.class})
public class UserControllerTest {
    @MockBean
    private UserServiceImpl usersService;
    @Autowired
    UserController userController;

    @Before("setUp")
    public void setUpData() {
    }

    @Test
    void getAllUsersSuccessTest() {
        UserResponse userResponse1 = new UserResponse();
        UserResponse userResponse2 = new UserResponse();
        List<UserResponse> userResponses = List.of(userResponse1, userResponse2);

        when(usersService.getAllUsers()).thenReturn(userResponses);
        List<UserResponse> result = userController.getAllUser();

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, userResponses);
        });
        verify(usersService).getAllUsers();
    }

    @Test
    void getAllUsersNotFoundTest() {
      when(usersService.getAllUsers()).thenReturn(new ArrayList<UserResponse>());
      List<UserResponse> result = userController.getAllUser();

      assertAll(()->{
          assertNotNull(result);
          assertEquals(0,result.size());
      });
    }

    @Test
    void findByIdSuccessTest(){
        UserResponse userResponse = getUserResponse();
        when(usersService.findById(1L)).thenReturn(userResponse);

        UserResponse result = userController.findById(1L);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, userResponse);
        });
        verify(usersService).findById(1L);
    }

    @Test
    void createSuccessTest() throws CreationException {
        UserCreateRequest request = getUserCreateRequest();
        UserResponse response = getUserResponse();

        when(usersService.createUser(request)).thenReturn(response);

        UserResponse result = userController.create(request);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });

        verify(usersService).createUser(request);
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
                .roleResponse(new RoleResponse("user"))
                .build();
    }

}
