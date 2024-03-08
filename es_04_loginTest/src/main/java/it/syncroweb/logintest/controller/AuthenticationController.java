package it.syncroweb.logintest.controller;

import it.syncroweb.logintest.dto.AuthenticationRequest;
import it.syncroweb.logintest.dto.AuthenticationResponse;
import it.syncroweb.logintest.dto.RegisterRequest;
import it.syncroweb.logintest.model.EmailToken;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.repository.EmailTokenRepository;
import it.syncroweb.logintest.repository.UserRepository;
import it.syncroweb.logintest.service.AuthenticationService;
import it.syncroweb.logintest.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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
    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam(name = "token") String token){

        Locale locale = request.getLocale();

        EmailToken emailToken = jwtService.getEmailToken(token);
        if(emailToken == null){
            return "errore";
        }
        return "funzia";
    }
}
