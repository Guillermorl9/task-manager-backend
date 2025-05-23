package com.example.taskmanagerbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TaskAppDto {
    private Long id;
    private String title;
    private String date;
    private String time;
    private String description;
    private boolean completed;
}
