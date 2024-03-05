package it.syncroweb.logintest.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPassword.toString().getBytes());
            BigInteger hash = new BigInteger(1,md.digest());
            String result = hash.toString(16);
            while (result.length() < 32){
                result = "0" + result;
            }
            return result;
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
