package org.kamenchuk.controller;

import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.models.UserAuthResponse;
import org.kamenchuk.service.UserService;
import org.kamenchuk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(path = "/rent_module/user")
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

    @GetMapping(value = "/loadUser")
    public UserAuthResponse getUserByLogin(String login) {
        return userService.getUserByLogin(login);
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

    @PostMapping("/updateLogin/{id}")
    public UserResponse updateLogin(@RequestParam String newLogin, @PathVariable Long id) throws UpdatingException {
        return userService.updateLogin(newLogin,id);
    }

    @PostMapping("/admin/changeUserRole/{id}")
    public UserResponse changeUserRole(@PathVariable Long id,@RequestParam RoleResponse role){
     return userService.changeUserRole(id,role);
    }
}
