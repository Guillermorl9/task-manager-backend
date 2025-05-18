package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}

