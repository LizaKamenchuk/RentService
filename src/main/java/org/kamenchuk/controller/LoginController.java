package org.kamenchuk.controller;

import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.kamenchuk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private final UserService userService;

    @Autowired
    LoginController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
//    @GetMapping(value = "/p")
//    public String getPage(){
//        return userService.getPage();
//    }
}
