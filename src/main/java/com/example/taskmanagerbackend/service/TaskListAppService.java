package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.repository.CategoryAppRepository;
import com.example.taskmanagerbackend.repository.TaskListAppRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListAppService {
    private final TaskListAppRepository taskListAppRepository;
    private final CategoryAppRepository categoryAppRepository;

    public List<TaskListApp> getCategoryTaskLists(Long categoryId) {
        return taskListAppRepository.findByCategoryId(categoryId);
    }

    public List<TaskListApp> getAllTaskLists() {
        return taskListAppRepository.findAll();
    }

    public List<TaskListApp> getAllTaskListsByUser(Long userId) {
        return taskListAppRepository.findByCategoryUserAppId(userId);
    }

    public TaskListApp getTaskListById(Long id) {
        return taskListAppRepository.findById(id).orElse(null);
    }

    public TaskListApp saveTaskList(Long categoryId, TaskListApp taskListApp) {
        CategoryApp category = categoryAppRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        taskListApp.setCategory(category);
        return taskListAppRepository.save(taskListApp);
    }


    public void deleteTaskList(Long id) {
        taskListAppRepository.deleteById(id);
    }
}
