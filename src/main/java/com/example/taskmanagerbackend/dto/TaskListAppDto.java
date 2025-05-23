package com.example.taskmanagerbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TaskListAppDto {
    private Long id;
    private String title;
    private List<TaskAppDto> tasks;
}
