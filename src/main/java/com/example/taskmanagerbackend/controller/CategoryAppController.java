package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.service.CategoryAppService;
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
public class CategoryAppController {

    private final CategoryAppService categoryAppService;
    private final UserAppService userAppService;
    private final TaskListAppService taskListAppService;

    @GetMapping
    public List<CategoryApp> getUserCategories(@AuthenticationPrincipal UserDetails userDetails) {
        UserApp user = userAppService.getUserByEmail(userDetails.getUsername());
        return categoryAppService.getCategoriesByUser(user.getId());
    }

    @GetMapping("/lists")
    public List<TaskListApp> getAllTaskLists(@AuthenticationPrincipal UserDetails userDetails) {
        UserApp user = userAppService.getUserByEmail(userDetails.getUsername());
        return this.taskListAppService.getAllTaskLists();
    }

    @PostMapping
    public CategoryApp createUserCategory(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestBody CategoryApp categoryApp) {
        UserApp user = userAppService.getUserByEmail(userDetails.getUsername());
        return categoryAppService.createCategoryForUser(user.getId(), categoryApp);
    }

    @GetMapping("/{categoryId}")
    public CategoryApp getCategory(@PathVariable Long categoryId) {
        return categoryAppService.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryAppService.deleteCategory(categoryId);
    }
}
