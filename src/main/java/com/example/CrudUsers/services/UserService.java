package com.example.CrudUsers.services;

import com.example.CrudUsers.models.Password;
import com.example.CrudUsers.models.User;
import com.example.CrudUsers.repositories.UserRepository;
import com.example.CrudUsers.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HashUtils hashingUtils;

    // Constructor Injection
    @Autowired
    public UserService(UserRepository userRepository, HashUtils hashingUtils) {
        this.userRepository = userRepository;
        this.hashingUtils = hashingUtils;
    }

    @Transactional
    public User registerUser(User username, String passwordPlano) {
        if (userRepository.findByEmail(username.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El Email '" + username.getEmail() + "' ya está registrado.");
        }
        if (userRepository.findByUsernameAndEliminadoFalse(username.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already in use" + username.getUsername());
        }
        // Save user and password logic here

        String salt = hashingUtils.generateSalt();
        String hash = hashingUtils.generateHash(passwordPlano, salt);

        Password password = new Password();

        password.setSalt(salt);
        password.setHashPassword(hash);

        username.setPassword(password);
        password.setUsername(username);

        return userRepository.save(username);
    }

}
