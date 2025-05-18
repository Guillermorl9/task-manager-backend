package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.TaskListApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListAppRepository extends JpaRepository<TaskListApp, Long> {
    List<TaskListApp> findByCategoryId(Long categoryId);
}
