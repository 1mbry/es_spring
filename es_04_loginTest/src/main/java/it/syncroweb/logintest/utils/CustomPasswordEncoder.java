package it.syncroweb.logintest.utils;

import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5HashUtils.getMD5Hash(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
