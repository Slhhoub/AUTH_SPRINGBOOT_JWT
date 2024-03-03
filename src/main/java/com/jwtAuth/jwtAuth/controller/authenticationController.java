package com.jwtAuth.jwtAuth.controller;

import com.jwtAuth.jwtAuth.model.User;
import com.jwtAuth.jwtAuth.service.AuthenticationReponse;
import com.jwtAuth.jwtAuth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/admin")
    public String helloAdmin()
    {
        return "admin is work";
    }

    @GetMapping("/user")
    public String helloUser()
    {
        return "user is work";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationReponse> register(@RequestBody User user){
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public  ResponseEntity<AuthenticationReponse> login(@RequestBody User user){
        return ResponseEntity.ok(authenticationService.login(user));
    }
}
