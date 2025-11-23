package com.example.CrudUsers.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "credencial_acceso")
@Data
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 256)
    private String hashPassword;

    @Column(length = 64)
    private String salt;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User username;

    private LocalDateTime lastChange;
    private boolean requirePasswordChange;
}
