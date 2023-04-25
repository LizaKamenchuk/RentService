package com.kamenchuk.gatewaymodule.rentModule.controllers;

import com.kamenchuk.gatewaymodule.authModule.controller.AuthModuleController;
import com.kamenchuk.gatewaymodule.rentModule.feinClient.FeignUserClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rentModule")
public class UserController {
    private final FeignUserClient feignUserClient;

    public UserController(FeignUserClient feignUserClient) {
        this.feignUserClient = feignUserClient;
    }

    @GetMapping(value = "/admin/all")
    public void getAllUser() {
       if(AuthModuleController.STATUS.equals(HttpStatus.OK))
        feignUserClient.getAllUser();
    }

}
