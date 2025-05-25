package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.service.TaskListAppService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/{categoryId}/lists")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskListAppController {

    private final TaskListAppService taskListAppService;

    @GetMapping
    public List<TaskListApp> getCategoryTaskLists(@PathVariable Long categoryId) {
        return taskListAppService.getCategoryTaskLists(categoryId);
    }

    @PostMapping
    public TaskListApp createTaskList(@PathVariable Long categoryId, @RequestBody TaskListApp taskListApp) {
        return taskListAppService.saveTaskList(categoryId, taskListApp);
    }

    @GetMapping("/{taskListId}")
    public TaskListApp getTaskList(@PathVariable Long taskListId) {
        return taskListAppService.getTaskListById(taskListId);
    }

    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable Long taskListId) {
        taskListAppService.deleteTaskList(taskListId);
    }
}

