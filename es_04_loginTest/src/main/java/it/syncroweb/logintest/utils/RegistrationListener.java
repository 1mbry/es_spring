package it.syncroweb.logintest.utils;

import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {

        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        jwtService.createEmailToken(user,token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/api/v1/auth/registrationConfirm?token=" + token;
        String message = messages.getMessage("test", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
        mailSender.send(email);
    }
}
