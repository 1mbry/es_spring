package it.syncroweb.logintest.service;

import it.syncroweb.logintest.dto.request.RegisterRequest;
import it.syncroweb.logintest.exception.UserAlreadyExistException;
import it.syncroweb.logintest.model.EmailToken;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.repository.EmailTokenRepository;
import it.syncroweb.logintest.repository.RoleRepository;
import it.syncroweb.logintest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailTokenRepository emailTokenRepository;


    /*
     * Registrazione nuovo utente
     */
    public UserEntity registerNewUserAccount(RegisterRequest register){
        if(emailExists(register.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + register.getEmail());
        }
        UserEntity user = new UserEntity();
        user.setFirstname(register.getFirstname());
        user.setLastname(register.getLastname());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setEmail(register.getEmail());
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));

        return userRepository.save(user);
    }

    /*
     * Verifico che il token sia valido, nel caso vada bene attivo l'account
     */
    public String validateEmailToken(String token){
        EmailToken emailToken = emailTokenRepository.findByToken(token);
        if(emailToken == null){
            return "invalid token";
        }

        UserEntity userEntity = emailToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if((emailToken.getExpirationTime()
                .getTime() - calendar.getTime()
                .getTime()) <= 0){
            emailTokenRepository.delete(emailToken);
            return "token expired";
        }

        //userEntity.setEnable(true);
        return "token valido";
    }

    /*
     *
     */
    public UserEntity getUser(final String emailToken) {
        final EmailToken token = emailTokenRepository.findByToken(emailToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    /*
     * Check della mail se esiste
     */
    private boolean emailExists(String email){
        return userRepository.findByEmail(email) != null;
    }
}
