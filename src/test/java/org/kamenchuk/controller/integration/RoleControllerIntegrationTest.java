package org.kamenchuk.controller.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.service.RoleService;
import org.kamenchuk.service.impl.integration.PostgresContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleControllerIntegrationTest extends PostgresContainer {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private RoleService roleService;
    private final static String REQUEST_MAPPING = "/rent_module/role";

    @Order(2)
    @Test
    void deleteRole() {
        Integer id = 1;
        restTemplate.delete(REQUEST_MAPPING + "/admin/deleteRole/{id}",id);
        assertThrows(ResourceNotFoundException.class,()->roleService.delete(id));
    }

    @Order(1)
    @Test
    void createRole() {
        String role = "USER";
        RoleResponse createdRole = RoleResponse.builder().role(role).build();
        ResponseEntity<RoleResponse> response = restTemplate.postForEntity(REQUEST_MAPPING + "/admin/createRole/{role}", String.class, RoleResponse.class, role);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), createdRole);
    }
}