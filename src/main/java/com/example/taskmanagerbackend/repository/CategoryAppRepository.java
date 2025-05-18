package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.CategoryApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryAppRepository extends JpaRepository<CategoryApp, Long> {
    List<CategoryApp> findByUserId(Long userId);
}
