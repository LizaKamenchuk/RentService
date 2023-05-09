package org.kamenchuk.rentModule.feinClient;

import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "feignRoleClient", url = "http://localhost:8080/rent_module/role")
public interface FeignRoleClient {

    @DeleteMapping(value = "/admin/deleteRole/{id}")
    void deleteRole(@PathVariable Integer id);

    @PostMapping(value = "/admin/createRole/{role}")
    RoleResponse createRole(@PathVariable String role);
}
