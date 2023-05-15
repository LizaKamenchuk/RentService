package org.kamenchuk.authModule.controller;

import org.kamenchuk.authModule.feignClient.AuthJwtClient;
import org.kamenchuk.authModule.feignClient.dto.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth_module")
public class AuthModuleController {
    private final AuthJwtClient jwtClient;

    @Autowired
    public AuthModuleController(AuthJwtClient jwtClient) {
        this.jwtClient = jwtClient;
    }

    @PostMapping (value = "/login")
    public  ResponseEntity<String> authenticate(@RequestBody LoginCredentials body) {
       ResponseEntity <String> token = jwtClient.createAuthenticationToken(body);
       return token;
    }

}
