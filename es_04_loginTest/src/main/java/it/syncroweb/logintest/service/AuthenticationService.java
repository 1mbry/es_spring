package it.syncroweb.logintest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.syncroweb.logintest.dto.request.AuthenticationRequest;
import it.syncroweb.logintest.dto.response.AuthenticationResponse;
import it.syncroweb.logintest.dto.request.RegisterRequest;
import it.syncroweb.logintest.model.Token;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.repository.RoleRepository;
import it.syncroweb.logintest.repository.TokenRepository;
import it.syncroweb.logintest.repository.TokenTypeRepository;
import it.syncroweb.logintest.repository.UserRepository;
import it.syncroweb.logintest.utils.OnRegistrationCompleteEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenTypeRepository tokenTypeRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        /*UserEntity user = UserEntity.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Collections.singletonList(roleRepository.findByName(registerRequest.getRole()).get()))
                .build();*/

        UserEntity user = new UserEntity();
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName(registerRequest.getRole()).get()));


        UserEntity savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        List<Token> tokens = saveUserToken(savedUser, jwtToken);
        savedUser.setTokens(tokens);

        // mi serve per mandare una mail di conferma
        //String appUrl = request.getContextPath();
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(savedUser, request.getLocale(),appUrl));

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));
        UserEntity user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(),request.getUsernameOrEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);

        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(UserEntity userEntity) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(userEntity.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private List<Token> saveUserToken(UserEntity savedUser, String jwtToken) {
        Token token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenTypes(Collections.singletonList(tokenTypeRepository.findByName("BEARER").get()))
                .expired(false)
                .revoked(false)
                .build();
        return Collections.singletonList(tokenRepository.save(token));
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            UserEntity userEntity = this.userRepository.findByUsername(username)
                    .orElseThrow();
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtService.generateToken(userEntity);
                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
            else {
                throw new IOException("non va");
            }
        }
    }


}
