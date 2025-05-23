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
public class CategoryAppDto {
    private Long id;
    private String title;
    private String icon;
    private List<TaskListAppDto> lists;
}
