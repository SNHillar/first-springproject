package com.example.CrudUsers.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "credencial_acceso")
@Data
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 256)
    private String hashPassword;

    @Column(length = 64)
    private String salt;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User username;
    @Column(name = "last_change")
    private LocalDateTime lastChange;
    @Column (name = "require_reset")
    private boolean requirePasswordChange;
}
