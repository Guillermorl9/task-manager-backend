package com.example.taskmanagerbackend.model;

import com.example.taskmanagerbackend.dto.UserAppDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String token;
    private UserAppDto user;

    public LoginResponse(String token, UserAppDto user) {
        this.token = token;
        this.user = user;
    }

}
