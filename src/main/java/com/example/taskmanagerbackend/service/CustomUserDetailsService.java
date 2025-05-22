package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAppRepository userAppRepository;

    public CustomUserDetailsService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserApp userApp = userAppRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userApp.getEmail())
                .password(userApp.getPassword())
                .authorities("USER")
                .build();
    }
}
