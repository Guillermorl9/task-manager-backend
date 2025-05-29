package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.service.TaskAppService;
import lombok.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskAppController {

    private final TaskAppService taskAppService;

    @GetMapping
    public List<TaskApp> getTasksByList(@PathVariable Long listId) {
        return taskAppService.getTasksByList(listId);
    }

    @GetMapping("/tasks/today")
    public List<TaskApp> getTodayTasks(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return taskAppService.getTodayTasks(email);
    }

    @GetMapping("/tasks/upcoming")
    public List<TaskApp> getUpcomingTasksByEmail(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return taskAppService.getUpcomingTasksByEmail(email);
    }

    @GetMapping("/all-tasks")
    public List<TaskApp> getAllUserTasks(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return this.taskAppService.getAllTasksByEmail(email);
    }

    @PostMapping("/{listId}/tasks")
    public TaskApp createTask(@PathVariable Long listId, @RequestBody TaskApp taskApp) {
        return taskAppService.createTask(listId, taskApp);
    }

    @GetMapping("/{taskId}")
    public TaskApp getTask(@PathVariable Long taskId) {
        return taskAppService.getTaskById(taskId);
    }

    @PutMapping("/tasks/{taskId}")
    public TaskApp updateTask(@PathVariable Long taskId, @RequestBody TaskApp updatedTask) {
        return taskAppService.updateTask(taskId, updatedTask);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskAppService.deleteTask(taskId);
    }
}

