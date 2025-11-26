package com.example.CrudUsers.repositories;

import com.example.CrudUsers.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Integer> {

    Optional<Password> findByUsernameUsername(String username);
}
