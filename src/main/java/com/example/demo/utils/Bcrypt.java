package com.example.demo.utils;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
    
}
