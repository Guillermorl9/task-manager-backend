package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.service.TaskListAppService;
import com.example.taskmanagerbackend.service.UserAppService;
import lombok.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskListAppController {

    private final TaskListAppService taskListAppService;
    private final UserAppService userAppService;

    @GetMapping("/{categoryId}/lists")
    public List<TaskListApp> getCategoryTaskLists(@PathVariable Long categoryId) {
        return taskListAppService.getCategoryTaskLists(categoryId);
    }

    @GetMapping("/user/lists")
    public List<TaskListApp> getAllTaskLists(@AuthenticationPrincipal UserDetails userDetails) {
        UserApp user = userAppService.getUserByEmail(userDetails.getUsername());
        return taskListAppService.getAllTaskListsByUser(user.getId());
    }

    @PostMapping("/{categoryId}/lists")
    public TaskListApp createTaskList(@PathVariable Long categoryId, @RequestBody TaskListApp taskListApp) {
        return taskListAppService.saveTaskList(categoryId, taskListApp);
    }

    @GetMapping("/{taskListId}")
    public TaskListApp getTaskList(@PathVariable Long taskListId) {
        return taskListAppService.getTaskListById(taskListId);
    }

    @DeleteMapping("/lists/{taskListId}")
    public void deleteTaskList(@PathVariable Long taskListId) {
        taskListAppService.deleteTaskList(taskListId);
    }
}

