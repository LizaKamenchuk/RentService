package org.kamenchuk.controller;

import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.service.RoleService;
import org.kamenchuk.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    RoleController(RoleServiceImpl roleService){
        this.roleService = roleService;
    }

    @DeleteMapping("/admin/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) throws ResourceNotFoundException {
       return roleService.delete(id);
    }

    @PostMapping("/admin/createRole/{role}")
    public RoleResponse createRole(@PathVariable String role) throws CreationException {
        return roleService.create(role);
    }
}
