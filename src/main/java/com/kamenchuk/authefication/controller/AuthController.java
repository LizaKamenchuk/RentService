package com.kamenchuk.authefication.controller;

import com.kamenchuk.authefication.dto.LoginCredentials;
import com.kamenchuk.authefication.models.User;
import com.kamenchuk.authefication.security.jwt.JwtUtil;
import com.kamenchuk.authefication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final UserServiceImpl service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    @Autowired
    public AuthController(UserServiceImpl service,
                          JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginCredentials body) throws Exception {
        System.out.println(body.toString());
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(body.getLogin(), body.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Username or Password", e);
        }
        final User user = (User) service.loadUserByUsername(body.getLogin());
        final String jwtToken = jwtUtil.generateToken(user);
        System.out.println(jwtToken);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
