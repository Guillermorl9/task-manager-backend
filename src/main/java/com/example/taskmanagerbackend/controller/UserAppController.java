package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserAppController {

    private final UserAppService userAppService;

    @GetMapping("/{userId}")
    public UserApp getUserAppById(@PathVariable Long userId) {
        return userAppService.getUserById(userId);
    }

    @PostMapping
    public UserApp createUserApp(@RequestBody UserApp userApp) {
        return userAppService.createUser(userApp);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserApp(@PathVariable Long userId) {
        userAppService.deleteUser(userId);
    }
}
