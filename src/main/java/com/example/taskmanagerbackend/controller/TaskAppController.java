package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.service.TaskAppService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists/{listId}/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskAppController {

    private final TaskAppService taskAppService;

    @GetMapping
    public List<TaskApp> getTasksByList(@PathVariable Long listId) {
        return taskAppService.getTasksByList(listId);
    }

    @PostMapping
    public TaskApp createTask(@PathVariable Long listId, @RequestBody TaskApp taskApp) {
        return taskAppService.createTask(listId, taskApp);
    }

    @GetMapping("/{taskId}")
    public TaskApp getTask(@PathVariable Long taskId) {
        return taskAppService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public TaskApp updateTask(@PathVariable Long taskId, @RequestBody TaskApp updatedTask) {
        return taskAppService.updateTask(taskId, updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskAppService.deleteTask(taskId);
    }
}

