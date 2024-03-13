package it.syncroweb.logintest.controller;

import it.syncroweb.logintest.dto.request.RegisterRequest;
import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.service.JwtService;
import it.syncroweb.logintest.service.UserService;
import it.syncroweb.logintest.utils.OnRegistrationCompleteEvent;
import it.syncroweb.logintest.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private JwtService jwtService;

    /*
     * Registrazione nuovo utente
     */
    @PostMapping("/user/registration")
    public ResponseEntity<?> registerAccountUser(@Valid RegisterRequest register, HttpServletRequest request) {
        LOGGER.info("Registering user account with information: {}", register);

        UserEntity user = userService.registerNewUserAccount(register);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), Utils.getAppUrl(request)));
        return new ResponseEntity<>("Email sent", HttpStatus.CREATED);
    }

    /*
     * Conferma Registrazione utente
     */
    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(HttpServletRequest request, @RequestParam("token") String token) {
        Locale locale = request.getLocale();
        String emailToken = userService.validateEmailToken(token);
        if (emailToken.equals("valid")) {
            UserEntity userEntity = userService.getUser(token);
            String jwtToken = jwtService.generateToken(userEntity);
            Utils.authWithoutPassword(userEntity);
            return new ResponseEntity<>("User registered", HttpStatus.OK);
        }
        return new ResponseEntity<>("Token scaduto", HttpStatus.BAD_REQUEST);
    }


    /*
     * Richiedi un nuovo token di conferma registrazione utente
     */
    /*
    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(HttpServletRequest request, @RequestParam("token") String token) {

        return new ResponseEntity<>("dsadasd",HttpStatus.OK);
    }*/


}