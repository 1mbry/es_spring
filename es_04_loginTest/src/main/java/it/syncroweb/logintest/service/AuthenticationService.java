package it.syncroweb.logintest.service;

import it.syncroweb.logintest.dto.AuthenticationResponse;
import it.syncroweb.logintest.dto.RegisterRequest;
import it.syncroweb.logintest.model.Token;
import it.syncroweb.logintest.model.TokenType;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.repository.RoleRepository;
import it.syncroweb.logintest.repository.TokenRepository;
import it.syncroweb.logintest.repository.TokenTypeRepository;
import it.syncroweb.logintest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singletonList(roleRepository.findByName(request.getRole()).get()))
                .build();
        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenTypes(Collections.singletonList(tokenTypeRepository.findByName("BEARER").get()))
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        /*UserEntity user = new UserEntity();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));*/

        /*Role roles = roleRepository.findByName(request.getRole()).get();
        user.setRoles(Collections.singletonList(roles));
*/


    }

    /*public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        /*
        var user = UserEntity.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }*/
}
