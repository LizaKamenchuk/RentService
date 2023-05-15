package org.kamenchuk.rentModule.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.rentModule.feinClient.FeignRoleClient;
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
    private RoleController controller;

    @MockBean
    private FeignRoleClient feignRoleClient;

    @Test
    void createRole() {
        String role = "ADMIN";
        RoleResponse response = new RoleResponse(role);
        when(feignRoleClient.createRole(role)).thenReturn(response);
        RoleResponse result = controller.createRole(role);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}