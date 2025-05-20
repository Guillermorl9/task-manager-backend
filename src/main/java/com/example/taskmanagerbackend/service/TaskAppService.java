package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.repository.TaskAppRepository;
import com.example.taskmanagerbackend.repository.TaskListAppRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAppService {

    private final TaskAppRepository taskAppRepository;
    private final TaskListAppRepository taskListAppRepository;

    public List<TaskApp> getTaskListTasks(Long taskListId) {
        return taskAppRepository.findByTaskListAppId(taskListId);
    }

    public TaskApp getTaskById(Long id) {
        return taskAppRepository.findById(id).orElse(null);
    }

    public TaskApp saveTask(Long taskListId, TaskApp taskApp) {
        TaskListApp taskList = taskListAppRepository.findById(taskListId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        taskApp.setTaskListApp(taskList);
        return taskAppRepository.save(taskApp);
    }


    public void deleteTask(Long id) {
        taskAppRepository.deleteById(id);
    }
}
