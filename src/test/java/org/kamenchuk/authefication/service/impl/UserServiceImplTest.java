package org.kamenchuk.authefication.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.authefication.models.Role;
import org.kamenchuk.authefication.models.User;
import org.kamenchuk.authefication.repository.RoleRepository;
import org.kamenchuk.authefication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;

    @MockBean
    private UserRepository repository;
    @MockBean
    private RoleRepository roleRepository;

    @Test
    void getByLogin() {
        String login = "login";
        User response = new User();
        when(repository.getUserByLogin(login)).thenReturn(response);
        User result = userService.getByLogin(login);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void loadUserByUsername() {
        String login = "login";
        Role role = new Role(1,"ADMIN");
        User response = User.builder()
                .login(login)
                .role(role)
                .build();
        when(repository.getUserByLogin(login)).thenReturn(response);
        when(roleRepository.getRoleById(response.getRole().getId())).thenReturn(role);
        User result = (User) userService.loadUserByUsername(login);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}