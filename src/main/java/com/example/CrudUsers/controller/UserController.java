package com.example.CrudUsers.controller;

import com.example.CrudUsers.dto.UserRegistrationDTO;
import com.example.CrudUsers.models.User;
import com.example.CrudUsers.services.PasswordService;
import com.example.CrudUsers.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    public UserController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }


    // User Registration Endpoint
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setActivo(true);
        newUser.setEliminado(false);
        User registeredUser = userService.registerUser(newUser, userDTO.getPasswordPlano());
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserRegistrationDTO loginDTO) {
        User loggedInUser = passwordService.login(loginDTO.getUsername(), loginDTO.getPasswordPlano());
        if(loggedInUser == null){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(loggedInUser);
    }
}
