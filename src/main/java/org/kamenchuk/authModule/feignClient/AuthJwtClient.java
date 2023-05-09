package org.kamenchuk.authModule.feignClient;

import org.kamenchuk.authModule.feignClient.dto.LoginCredentials;
import org.kamenchuk.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authJwtClient", url = "http://localhost:8084/auth")
public interface AuthJwtClient {

    @GetMapping(value = "/loadUserDetails", consumes = {MediaType.APPLICATION_JSON_VALUE})
    User loadUserDetails(@RequestParam String name);
    @GetMapping(path = "/extractName",produces = {MediaType.APPLICATION_JSON_VALUE})
    String extractName(@RequestParam String jwt);

    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> createAuthenticationToken(@RequestBody LoginCredentials body);
    @PostMapping(path = "/validateToken")
    boolean validateToken(@RequestParam String jwt,@RequestBody User userDetails);
}
