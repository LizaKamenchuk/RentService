package org.kamenchuk.rentModule.controllers;

import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.rentModule.feinClient.FeignUserClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/rent_module/user")
public class UserController {
    private final FeignUserClient feignUserClient;

    public UserController(FeignUserClient feignUserClient) {
        this.feignUserClient = feignUserClient;
    }
    @GetMapping(value = "/admin/all")
    public List<UserResponse> getAllUser() {
         return feignUserClient.getAllUser();
    }
    @GetMapping(value = "/admin/findById/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return feignUserClient.findById(id);
    }

    @PostMapping(value = "/create")
    public UserResponse create(@RequestBody UserCreateRequest userDto) {
        return feignUserClient.create(userDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        feignUserClient.delete(id);

    }

    @PatchMapping("/updateLogin")
    public UserResponse updateLogin(@RequestParam String newLogin, @RequestParam Long id) {
        return feignUserClient.updateLogin(newLogin,id);
    }

}
