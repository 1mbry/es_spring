package it.syncroweb.logintest.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.syncroweb.logintest.dto.AuthenticationRequest;
import it.syncroweb.logintest.dto.AuthenticationResponse;
import it.syncroweb.logintest.dto.RegisterRequest;
import it.syncroweb.logintest.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    /*@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }*/
}
