package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.dto.UserAppDto;
import com.example.taskmanagerbackend.mapper.UserAppMapper;
import com.example.taskmanagerbackend.model.LoginResponse;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.security.JwtUtil;
import com.example.taskmanagerbackend.service.GoogleAuthService;
import com.example.taskmanagerbackend.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final GoogleAuthService googleAuthService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, GoogleAuthService googleAuthService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.googleAuthService = googleAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            final String token = jwtUtil.generateToken(authRequest.getEmail());
            UserApp userApp = userService.findByEmail(authRequest.getEmail());
            UserAppDto userAppDto = UserAppMapper.toDto(userApp);
            return ResponseEntity.ok(new LoginResponse(token, userAppDto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        try {
            UserApp existingUser = userService.findByEmail(authRequest.getEmail());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "Mail is already registered"));
            }

            UserApp userApp = userService.registerUser(authRequest.getEmail(), authRequest.getPassword(), authRequest.getName(), authRequest.getLastname());
            UserAppDto dto = UserAppMapper.toDto(userApp);
            final String token = jwtUtil.generateToken(userApp.getEmail());
            return ResponseEntity.ok(new LoginResponse(token, dto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error when registering the user: " + e.getMessage()));
        }
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        try {
            System.out.println("Token recibido en try" + request.getIdToken());
            LoginResponse response = googleAuthService.loginOrRegisterWithGoogle(request.getIdToken());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Token recibido en catch" + request.getIdToken());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @Setter
    @Getter
    public static class AuthRequest {
        private String email;
        private String password;
        private String name;
        private String lastname;
    }

    @Getter
    @Setter
    public static class GoogleLoginRequest {
        private String idToken;
    }
}
