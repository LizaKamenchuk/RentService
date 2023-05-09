package org.kamenchuk.rentModule.controllers;

import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.rentModule.feinClient.FeignRoleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/role")
public class RoleController {
    private final FeignRoleClient feignRoleClient;

    @Autowired
    public RoleController(FeignRoleClient feignRoleClient) {
        this.feignRoleClient = feignRoleClient;
    }

    @DeleteMapping("/admin/deleteRole/{id}")
    public void deleteRole(@PathVariable Integer id){
        feignRoleClient.deleteRole(id);
    }

    @PostMapping("/admin/createRole/{role}")
    public RoleResponse createRole(@PathVariable String role) {
        return feignRoleClient.createRole(role);
    }
}
