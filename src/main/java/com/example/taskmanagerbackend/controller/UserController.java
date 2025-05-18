package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserAppRepository userAppRepository;

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public List<UserApp> getAllUsers() {
        return userAppRepository.findAll();
    }

    // Endpoint para agregar un nuevo usuario
    @PostMapping
    public UserApp createUser(@RequestBody UserApp userApp) {
        return userAppRepository.save(userApp);
    }
}
