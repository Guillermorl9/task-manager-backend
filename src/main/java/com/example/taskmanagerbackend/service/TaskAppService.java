package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.repository.TaskAppRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAppService {
    private final TaskAppRepository taskAppRepository;

    public List<TaskApp> getAllTasks() {
        return taskAppRepository.findAll();
    }

    public TaskApp getTaskById(Long id) {
        return taskAppRepository.findById(id).orElse(null);
    }

    public TaskApp saveTask(TaskApp taskApp) {
        return taskAppRepository.save(taskApp);
    }

    public void deleteTask(Long id) {
        taskAppRepository.deleteById(id);
    }
}
