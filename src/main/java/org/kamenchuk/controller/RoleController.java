package org.kamenchuk.controller;

import org.kamenchuk.models.roleDTO.RoleResponse;
import org.kamenchuk.service.RoleService;
import org.kamenchuk.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    RoleController(RoleServiceImpl roleService){
        this.roleService = roleService;
    }

    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable Integer id){
        roleService.delete(id);
    }

    @PostMapping("/createRole/{role}")
    public RoleResponse createRole(@PathVariable String role){
        return roleService.create(role);
    }
}
