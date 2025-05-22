package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
}

