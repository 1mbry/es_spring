package it.syncroweb.logintest.utils;

import it.syncroweb.logintest.model.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

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
     *
     */
    public static String getAppUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public static void authWithoutPassword(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
