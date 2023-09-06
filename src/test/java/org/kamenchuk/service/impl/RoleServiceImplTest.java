package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.repository.RoleRepository;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.mapper.RoleMapper;
import org.kamenchuk.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RoleServiceImpl.class})
class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private RoleMapper roleMapper;

    @Test
    void create() throws CreationException {
        String role ="SUPERADMIN";
        Role newRole = Role.builder()
                .role(role)
                .build();
        Role idRole = new Role();
        idRole.setId(3);
        RoleResponse response = RoleResponse.builder()
                .role(role)
                .build();
        when(roleRepository.save(newRole)).thenReturn(idRole);
        when(roleMapper.toDtoResponse(idRole)).thenReturn(response);
        RoleResponse result = roleService.create(role);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}