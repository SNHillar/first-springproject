package com.example.CrudUsers.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "username", cascade = CascadeType.ALL, orphanRemoval = true)
    // Si se elimina el usuario, se elimina su contraseña asociada
    private Password password;
    private boolean active;
    private boolean eliminado;
}
