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

    public UserApp registerUser(String email, String password, String name, String lastname) {
        if (userAppRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        UserApp user = new UserApp();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setLastname(lastname);

        return userAppRepository.save(user);
    }

    public UserApp findByEmail(String email) {
        return userAppRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
