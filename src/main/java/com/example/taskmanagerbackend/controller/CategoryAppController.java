package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.service.CategoryAppService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryAppController {

    private final CategoryAppService categoryAppService;

    @GetMapping
    public List<CategoryApp> getUserCategories(@PathVariable Long userId) {
        return categoryAppService.getCategoriesByUser(userId);
    }

    @PostMapping
    public CategoryApp createUserCategory(@PathVariable Long userId, @RequestBody CategoryApp categoryApp) {
        return categoryAppService.createCategoryForUser(userId, categoryApp);
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
