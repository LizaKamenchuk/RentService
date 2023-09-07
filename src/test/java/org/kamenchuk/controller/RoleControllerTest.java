package org.kamenchuk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RoleController.class})
class RoleControllerTest {

    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleServiceImpl roleService;

    @Test
    void deleteRole() {
    }

    @Test
    void createRoleSuccess() throws CreationException {
        RoleResponse role = new RoleResponse("SUPERADMIN");
        RoleResponse response = role;
        when(roleService.create(role)).thenReturn(response);
        RoleResponse result = roleController.createRole(role);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}