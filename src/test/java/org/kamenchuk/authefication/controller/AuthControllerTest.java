package org.kamenchuk.authefication.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.kamenchuk.authefication.dto.LoginCredentials;
import org.kamenchuk.authefication.dto.UserDto;
import org.kamenchuk.authefication.models.Role;
import org.kamenchuk.authefication.models.User;
import org.kamenchuk.authefication.security.jwt.JwtFilter;
import org.kamenchuk.authefication.security.jwt.JwtUtil;
import org.kamenchuk.authefication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthController.class})
class AuthControllerTest {
    @Autowired
    AuthController authController;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private AuthenticationManager authManager;
    @MockBean
    private JwtFilter jwtFilter;

    @Test
    void createAuthTokenRightCredentials() throws Exception {
        User user = User.builder()
                .login("admin")
                .password("admin2")
                .role(new Role(1, "ADMIN"))
                .id(1L)
                .build();
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aG9yaXR5IjoiVVNFUiIsImxv" +
                "Z2luIjoidXNlciIsImV4cCI6MTY4MzY1ODI4OSwiaWF0IjoxNjgzNjIyMjg5fQ" +
                ".yqs28prElu6OB98xVyTmtPBUGNqXWr9lSU6rLPiZ2ts";
        LoginCredentials rightCredentials = new LoginCredentials("admin", "admin2");

        UserDetails userDetails = user;
        when(userService.loadUserByUsername(rightCredentials.getLogin())).thenReturn(userDetails);
        when(jwtUtil.generateToken(user)).thenReturn(jwt);
        ResponseEntity<String> response = new ResponseEntity<>(jwt, HttpStatus.OK);
        ResponseEntity<String> result = authController.createAuthenticationToken(rightCredentials);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getBody().substring(0, 9), response.getBody().substring(0, 9));
            assertEquals(result.getStatusCode(), response.getStatusCode());
        });
    }

    @Test
    void createAuthTokenWrongCredentials() throws Exception {
//        LoginCredentials wrongCredentials = new LoginCredentials("aaaa", "admi2");
//        authController.createAuthenticationToken(wrongCredentials);
    }

    @Test
    void getTokenFilter() {
    }

    @Test
    void extractName() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aG9yaXR5IjoiVVNFUiIsImxv" +
                "Z2luIjoidXNlciIsImV4cCI6MTY4MzY1ODI4OSwiaWF0IjoxNjgzNjIyMjg5fQ" +
                ".yqs28prElu6OB98xVyTmtPBUGNqXWr9lSU6rLPiZ2ts";
        String name = "user";
        when(jwtUtil.extractUsername(jwt)).thenReturn(name);
        String result = authController.extractName(jwt);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, name);
        });
    }

    @Test
    void loadUserDetails() {
        String name = "admin";
        User user = User.builder()
                .login("admin")
                .password("admin2")
                .role(new Role(1, "ADMIN"))
                .id(1L)
                .build();
        UserDto response = UserDto.builder()
                .login(user.getUsername())
                .role(user.getAuthorities().toString())
                .build();
        when(userService.loadUserByUsername(name)).thenReturn(user);
        UserDto result = authController.loadUserDetails(name);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, response);
        });
    }

    @Test
    void validateTokenSuccess() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aG9yaXR5IjoiVVNFUiIsImxv" +
                "Z2luIjoidXNlciIsImV4cCI6MTY4MzY1ODI4OSwiaWF0IjoxNjgzNjIyMjg5fQ" +
                ".yqs28prElu6OB98xVyTmtPBUGNqXWr9lSU6rLPiZ2ts";
        UserDto userDto = UserDto.builder()
                .login("user")
                .role("USER")
                .build();
        when(jwtUtil.validateToken(jwt,userDto)).thenReturn(true);
        boolean result = authController.validateToken(jwt,userDto);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, true);
        });
    }
    @Test
    void validateTokenFail() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aG9yaXR5IjoiVVNFUiIsImxv" +
                "Z2luIjoidXNlciIsImV4cCI6MTY4MzY1ODI4OSwiaWF0IjoxNjgzNjIyMjg5fQ" +
                ".yqs28prElu6OB98xVyTmtPBUGNqXWr9lSU6rLPiZ2ts";
        UserDto userDto = UserDto.builder()
                .login("user")
                .role("ADMIN")
                .build();
        when(jwtUtil.validateToken(jwt,userDto)).thenReturn(false);
        boolean result = authController.validateToken(jwt,userDto);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, false);
        });
    }
}