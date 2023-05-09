package org.kamenchuk.rentModule.feinClient;

import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "feignUserClient", url = "http://localhost:8080/rent_module/user")
public interface FeignUserClient {
    @GetMapping(value = "/admin/all")
    List<UserResponse> getAllUser();
    @GetMapping(value = "/admin/findById/{id}")
    UserResponse findById(@PathVariable Long id);

    @PostMapping(value = "/create")
    UserResponse create(@RequestBody UserCreateRequest userDto);

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Long id);

    @PatchMapping("/updateLogin")
    UserResponse updateLogin(@RequestParam String newLogin, @RequestParam Long id);
}
