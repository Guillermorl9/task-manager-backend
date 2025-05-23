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
public class UserAppDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String photoUrl;
    private List<CategoryAppDto> categories;
}
