package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

