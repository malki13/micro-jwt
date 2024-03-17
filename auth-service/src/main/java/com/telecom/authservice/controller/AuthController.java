package com.telecom.authservice.controller;

import com.telecom.authservice.data.dto.AuthRequest;
import com.telecom.authservice.data.dto.AuthResponse;
import com.telecom.authservice.data.entity.UserCredential;
import com.telecom.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public AuthResponse getToken(@RequestBody AuthRequest authRequest){
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(service.generateToken(authRequest.getUsername()));
//            return service.generateToken(authRequest.getUsername());
            return authResponse;
        }
        throw new RuntimeException("No se puede validar el usuario, revise credenciales");

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token")String token){
        service.validateToken(token);
        return "Token es valido";
    }

}
