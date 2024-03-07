package it.syncroweb.logintest.controller;

import it.syncroweb.logintest.dto.AuthenticationRequest;
import it.syncroweb.logintest.dto.AuthenticationResponse;
import it.syncroweb.logintest.dto.RegisterRequest;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.repository.UserRepository;
import it.syncroweb.logintest.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest request){
        return new ResponseEntity<>(service.register(registerRequest,request), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> mostra(){
        List<UserEntity> userEntity = userRepository.findAll();
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
