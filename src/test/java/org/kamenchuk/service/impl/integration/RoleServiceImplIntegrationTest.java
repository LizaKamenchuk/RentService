package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kamenchuk.repository.RoleRepository;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.class)
class RoleServiceImplIntegrationTest extends PostgresContainer{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleServiceImpl roleService;

    @Order(1)
    @Test
    void create() throws Exception {
        String roleName = "ADMIN";
        Role role = new Role();
        role.setRole(roleName);
        RoleResponse result = roleService.create(new RoleResponse(roleName));
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getRole(), roleName);
            assertNotNull(roleRepository.findFirstByRole(result.getRole()));
        });
    }

    @Order(2)
    @Test
    public void delete() throws ResourceNotFoundException {
        Integer id = 1;
        assertNotNull(roleRepository.getRoleById(id));
        roleService.delete(id);
    }

}