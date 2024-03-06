package it.syncroweb.logintest.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashUtils {

    public static String getMD5Hash(CharSequence input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.toString().getBytes());
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
}
