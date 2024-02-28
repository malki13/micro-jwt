package com.telecom.authservice.controller;

import com.telecom.authservice.data.entity.UserCredential;
import com.telecom.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @GetMapping("/token")
    public String getToken(UserCredential userCredential){
        return service.generateToken(userCredential.getNombre());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token")String token){
        service.validateToken(token);
        return "Token es valido";
    }

}
