package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserAppRepository userAppRepository;

    public UserApp getUserById(Long userId) {
        return userAppRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserApp createUser(UserApp userApp) {
        return userAppRepository.save(userApp);
    }

    public void deleteUser(Long userId) {
        UserApp userApp = userAppRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userAppRepository.delete(userApp);
    }
}
