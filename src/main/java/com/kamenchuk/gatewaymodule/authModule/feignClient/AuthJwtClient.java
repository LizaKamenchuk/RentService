package com.kamenchuk.gatewaymodule.authModule.feignClient;

import com.kamenchuk.gatewaymodule.authModule.feignClient.dto.LoginCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authJwtClient", url = "http://localhost:8084/auth")
public interface AuthJwtClient {

    @GetMapping(path = "/login", produces = "application/json")
    ResponseEntity<String> createAuthenticationToken(@RequestBody LoginCredentials body);
}
