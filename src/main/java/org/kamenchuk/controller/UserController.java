package org.kamenchuk.controller;

import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.UserService;
import org.kamenchuk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/all")
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/admin/findById/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/create")
    public UserResponse create(@RequestBody UserCreateRequest userDto) throws CreationException {
        return userService.createUser(userDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);

    }

    @PatchMapping("/updateLogin")
    public UserResponse updateLogin(@RequestParam String newLogin, @RequestParam Long id) throws UpdatingException {
        return userService.updateLogin(newLogin,id);
    }

}
