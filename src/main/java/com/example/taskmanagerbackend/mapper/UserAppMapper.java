package com.example.taskmanagerbackend.mapper;

import com.example.taskmanagerbackend.dto.CategoryAppDto;
import com.example.taskmanagerbackend.dto.TaskAppDto;
import com.example.taskmanagerbackend.dto.TaskListAppDto;
import com.example.taskmanagerbackend.dto.UserAppDto;
import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.model.UserApp;

import java.util.stream.Collectors;

public class UserAppMapper {

    public static UserAppDto toDto(UserApp user) {
        if (user == null) return null;

        UserAppDto dto = new UserAppDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setPhotoUrl(user.getPhotoUrl());

        if (user.getCategories() != null) {
            dto.setCategories(user.getCategories()
                    .stream()
                    .map(UserAppMapper::toCategoryDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static CategoryAppDto toCategoryDto(CategoryApp category) {
        if (category == null) return null;

        CategoryAppDto dto = new CategoryAppDto();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setIcon(category.getIcon());

        if (category.getLists() != null) {
            dto.setLists(category.getLists()
                    .stream()
                    .map(UserAppMapper::toTaskListDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static TaskListAppDto toTaskListDto(TaskListApp list) {
        if (list == null) return null;

        TaskListAppDto dto = new TaskListAppDto();
        dto.setId(list.getId());
        dto.setTitle(list.getTitle());

        if (list.getTasks() != null) {
            dto.setTasks(list.getTasks()
                    .stream()
                    .map(UserAppMapper::toTaskDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static TaskAppDto toTaskDto(TaskApp task) {
        if (task == null) return null;

        TaskAppDto dto = new TaskAppDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDate(task.getDate() != null ? task.getDate().toString() : null);
        dto.setTime(task.getTime());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }
}
