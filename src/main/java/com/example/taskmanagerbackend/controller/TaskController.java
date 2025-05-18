package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.repository.TaskAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    private TaskAppRepository taskAppRepository;

    @GetMapping
    public List<TaskApp> getAllTasks() {
        return taskAppRepository.findAll();
    }

    @PostMapping
    public TaskApp createTask(@RequestBody TaskApp taskApp) {
        return taskAppRepository.save(taskApp);
    }
}
