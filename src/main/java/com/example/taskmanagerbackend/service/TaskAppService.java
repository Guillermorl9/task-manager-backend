package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.repository.TaskAppRepository;
import com.example.taskmanagerbackend.repository.TaskListAppRepository;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAppService {

    private final TaskAppRepository taskAppRepository;
    private final TaskListAppRepository taskListAppRepository;

    public List<TaskApp> getTasksByList(Long taskListId) {
        return taskAppRepository.findByTaskListAppId(taskListId);
    }

    public TaskApp getTaskById(Long id) {
        return taskAppRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task no encontrada con id: " + id));
    }

    public TaskApp createTask(Long taskListId, TaskApp taskApp) {
        TaskListApp taskList = taskListAppRepository.findById(taskListId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada con id: " + taskListId));

        taskApp.setTaskListApp(taskList);
        return taskAppRepository.save(taskApp);
    }

    public List<TaskApp> getNextTop4PendingTasks(String email) {
        Pageable topFour = org.springframework.data.domain.PageRequest.of(0, 4);
        LocalDate today = LocalDate.now();
        return taskAppRepository.findNextTop4PendingTasksByEmailAfterToday(email, today, topFour);
    }

    public List<TaskApp> getTodayTasks(String userEmail) {
        LocalDate today = LocalDate.now();
        return taskAppRepository.findByUserEmailAndDate(userEmail, today);
    }

    public List<TaskApp> getUpcomingTasksByEmail(String email) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate fourDaysLater = tomorrow.plusDays(3);
        return taskAppRepository.findUpcomingTasksByUserEmail(email, tomorrow, fourDaysLater);
    }

    public List<TaskApp> getAllTasksByEmail(String email) {
        return taskAppRepository.findByUserEmail(email);
    }

    public TaskApp updateTask(Long taskId, TaskApp updatedTask) {
        TaskApp existingTask = taskAppRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task no encontrada con id: " + taskId));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDate(updatedTask.getDate());
        existingTask.setTime(updatedTask.getTime());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());

        return taskAppRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        if (!taskAppRepository.existsById(id)) {
            throw new RuntimeException("Task no encontrada con id: " + id);
        }
        taskAppRepository.deleteById(id);
    }
}

