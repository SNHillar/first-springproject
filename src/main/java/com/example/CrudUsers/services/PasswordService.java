package com.example.CrudUsers.services;


import com.example.CrudUsers.models.Password;
import com.example.CrudUsers.models.User;
import com.example.CrudUsers.repositories.PasswordRepository;
import com.example.CrudUsers.utils.HashUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PasswordService {

    // Dependencies
    private final PasswordRepository passwordRepository;
    private final HashUtils hashingUtils;

    // Constructor Injection
    public PasswordService(PasswordRepository passwordRepository, HashUtils hashingUtils) {
        this.passwordRepository = passwordRepository;
        this.hashingUtils = hashingUtils;
    }

    // Login method
    public User login(String username, String passwordPlano) {
        Optional<Password> passwordOpt = passwordRepository.findByUsername(username);

        if (passwordOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        // Get the Password entity
        Password password = passwordOpt.get();

        if (!hashingUtils.checkPassword(passwordPlano, password.getHashPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        // Return the associated User
        return password.getUsername();

    }

    // Change password method
    @Transactional
    public void changePassword(long id, String newPasswordPlano) {

        if (newPasswordPlano == null || newPasswordPlano.isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
        Optional<Password> passwordOpt = passwordRepository.findById(id);

        if (passwordOpt.isEmpty()) {
            throw new IllegalArgumentException("Password record not found");
        }

        Password password = passwordOpt.get();
        String newSalt = hashingUtils.generateSalt();
        String newHashedPassword = hashingUtils.generateHash(newPasswordPlano, newSalt);
        password.setHashPassword(newHashedPassword);
    }
}
