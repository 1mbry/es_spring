package it.syncroweb.logintest.controller;

import it.syncroweb.logintest.model.TokenType;
import it.syncroweb.logintest.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {

    @Autowired
    private TokenService tokenService;

    //create a token type
    @PostMapping("")
    public ResponseEntity<TokenType> createTokenType(@RequestBody TokenType tokenType) {
        TokenType createdTokenType = tokenService.create(tokenType);
        return new ResponseEntity<>(createdTokenType, HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public String getUserInfo(@RequestAttribute("id") String idUser){
        return "User ID: " + idUser;
    }
}
