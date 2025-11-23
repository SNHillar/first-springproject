package com.example.CrudUsers.utils;

import org.springframework.stereotype.Component;
import org.mindrot.jbcrypt.BCrypt;

@Component
public class HashUtils {

    public String generateSalt() {
        return BCrypt.gensalt();
    }

    public String generateHash(String passwordPlain, String salt) {
        return BCrypt.hashpw(passwordPlain, salt);
    }

    public boolean checkPassword(String passwordPlain, String storedHash) {
        return BCrypt.checkpw(passwordPlain, storedHash);
    }
}
