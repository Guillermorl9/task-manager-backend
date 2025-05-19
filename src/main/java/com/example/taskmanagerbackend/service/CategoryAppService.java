package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.CategoryAppRepository;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAppService {

    private final CategoryAppRepository categoryAppRepository;
    private final UserAppRepository userAppRepository;

    public List<CategoryApp> getCategoriesByUser(Long userId) {
        return categoryAppRepository.findByUserAppId(userId);
    }

    public CategoryApp createCategoryForUser(Long userId, CategoryApp categoryApp) {
        UserApp user = userAppRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        categoryApp.setUserApp(user);
        return categoryAppRepository.save(categoryApp);
    }

    public void deleteCategory(Long categoryId) {
        CategoryApp categoryApp = categoryAppRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryAppRepository.delete(categoryApp);
    }

    public CategoryApp getCategory(Long categoryId) {
        return categoryAppRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

}
