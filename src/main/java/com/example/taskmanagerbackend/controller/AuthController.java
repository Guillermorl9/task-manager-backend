package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.security.JwtUtil;
import com.example.taskmanagerbackend.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final String token = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        UserApp userApp = userService.registerUser(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok("Usuario registrado");
    }

    @Setter
    @Getter
    public static class AuthRequest {
        private String username;
        private String password;

    }

    @Setter
    @Getter
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

    }
}
