package it.syncroweb.logintest.utils;

import it.syncroweb.logintest.model.EmailToken;
import it.syncroweb.logintest.model.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Utils {

    private static MessageSource messages;

    private static final int EXPIRATION = 60 * 24;

    /*
     *
     */
    public static Date calculateExpiryDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, Utils.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    /*
     * Update del token scaduto per la Email
     */
    public static void updateEmailToken(EmailToken emailToken, String token) {
        emailToken.setToken(token);
        emailToken.setExpirationTime(calculateExpiryDate());
    }


    /*
     *
     */
    public static String getAppUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
    /*
     *
     */
    public static void authWithoutPassword(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /*
     *
     */
    public static SimpleMailMessage constructResendEmailToken(String contexPath, Locale locale, EmailToken emailToken, UserEntity user){
        String confirmationUrl = contexPath + "/registrationConfirm?token=" + emailToken.getToken();
        String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);

    }

    /*
     *
     */
    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final UserEntity user) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    /*
     *
     */
    private static SimpleMailMessage constructEmail(String subject, String body, UserEntity user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        return email;
    }
}
