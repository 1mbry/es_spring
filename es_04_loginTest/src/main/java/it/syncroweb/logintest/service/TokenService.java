package it.syncroweb.logintest.service;

import it.syncroweb.logintest.model.TokenType;
import it.syncroweb.logintest.repository.TokenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenTypeRepository tokenTypeRepository;

    public TokenType create(TokenType tokenType) {
        return tokenTypeRepository.save(tokenType);
    }
}
