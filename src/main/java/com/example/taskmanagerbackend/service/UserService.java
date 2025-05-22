package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserApp registerUser(String email, String password) {
        if (userAppRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        UserApp user = new UserApp();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userAppRepository.save(user);
    }
}
