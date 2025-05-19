package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.repository.TaskListAppRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListAppService {
    private final TaskListAppRepository taskListAppRepository;

    public List<TaskListApp> getAllTaskLists() {
        return taskListAppRepository.findAll();
    }

    public TaskListApp getTaskListById(Long id) {
        return taskListAppRepository.findById(id).orElse(null);
    }

    public TaskListApp saveTaskList(TaskListApp taskListApp) {
        return taskListAppRepository.save(taskListApp);
    }

    public void deleteTaskList(Long id) {
        taskListAppRepository.deleteById(id);
    }
}
