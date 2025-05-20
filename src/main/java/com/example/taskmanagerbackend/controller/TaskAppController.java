package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.service.TaskAppService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/categories/{categoryId}/lists/{taskListId}/tasks/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskAppController {

    private final TaskAppService taskAppService;

   @GetMapping
    public List<TaskApp> getTaskListTasks(@PathVariable Long taskListId) {
        return taskAppService.getTaskListTasks(taskListId);
    }

    @PostMapping
    public TaskApp createTask(@PathVariable Long taskListId, @RequestBody TaskApp taskApp) {
        return taskAppService.saveTask(taskListId, taskApp);
    }


    @GetMapping("/{taskId}")
    public TaskApp getTask(@PathVariable Long taskId) {
        return taskAppService.getTaskById(taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskAppService.deleteTask(taskId);
    }
}
