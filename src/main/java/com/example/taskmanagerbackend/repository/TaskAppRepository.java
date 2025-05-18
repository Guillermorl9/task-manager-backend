package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.TaskApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAppRepository extends JpaRepository<TaskApp, Long> {
    List<TaskApp> findByTaskListId(Long taskListId);
}
