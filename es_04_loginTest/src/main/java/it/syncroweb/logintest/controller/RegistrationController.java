package it.syncroweb.logintest.controller;

import it.syncroweb.logintest.dto.request.AuthenticationRequest;
import it.syncroweb.logintest.dto.request.RegisterRequest;
import it.syncroweb.logintest.model.EmailToken;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JavaMailSender mailSender;

    /*
     * Registrazione nuovo utente
     */
    @PostMapping("/user/registration")
    public ResponseEntity<?> registerUserAccount(@Valid RegisterRequest register, HttpServletRequest request) {
        LOGGER.info("Registering user account with information: {}", register);

        UserEntity user = userService.registerNewUserAccount(register);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), Utils.getAppUrl(request)));
        return new ResponseEntity<>("Email sent", HttpStatus.CREATED);
    }

    /*
     * Conferma Registrazione utente
     */
    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam(name = "token") String token) {
        String emailToken = userService.validateEmailToken(token);
        if (emailToken.equals("valid")) {
            UserEntity userEntity = userService.getUser(token);
            //userEntity.setEnables(true);
            userService.saveRegisteredUser(userEntity);
            String accessToken = jwtService.generateToken(userEntity);
            String refreshToken = jwtService.generateRefreshToken(userEntity);
            Utils.authWithoutPassword(userEntity);
            // devo ritornare l'accessToken e il refreshToken
            return new ResponseEntity<>("User registered", HttpStatus.OK);
        }
        return new ResponseEntity<>("Token scaduto", HttpStatus.BAD_REQUEST);
    }


    /*
     * Richiedi un nuovo token di conferma registrazione utente
     */
    @GetMapping("/user/resendRegistrationToken")
    public ResponseEntity<?> resendRegistrationToken(HttpServletRequest request, @RequestParam("token") String existingToken) {
        EmailToken newToken = userService.generateNewEmailToken(existingToken);
        UserEntity user = userService.getUser(newToken.getToken());
        mailSender.send(Utils.constructResendEmailToken(Utils.getAppUrl(request), request.getLocale(), newToken, user));
        return new ResponseEntity<>("Conferma email",HttpStatus.OK);
    }

    /*
     *
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>("dd", HttpStatus.OK);
    }
}