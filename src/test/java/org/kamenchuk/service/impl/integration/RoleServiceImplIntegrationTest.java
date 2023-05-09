package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.management.relation.RoleNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.class)
class RoleServiceImplIntegrationTest extends PostgresContainer{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleServiceImpl roleService;

    @Order(1)
    @Test
    void create() throws Exception {
        String roleName = "ADMIN";
        Role role = new Role();
        role.setRole(roleName);
        RoleResponse result = roleService.create(roleName);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getRole(), roleName);
            assertNotNull(roleDao.findFirstByRole(result.getRole()));
        });
    }

    @Order(2)
    @Test
    public void delete() throws RoleNotFoundException {
        Integer id = 1;
        assertNotNull(roleDao.getRoleById(id));
        roleService.delete(id);
    }

}