package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.dto.UserAppDto;
import com.example.taskmanagerbackend.mapper.UserAppMapper;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.security.JwtUtil;
import com.example.taskmanagerbackend.service.UserAppService;
import com.example.taskmanagerbackend.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
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
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        final String token = jwtUtil.generateToken(authRequest.getEmail());
        UserApp userApp = userService.findByEmail(authRequest.getEmail());
        UserAppDto userAppDto = UserAppMapper.toDto(userApp);
        return ResponseEntity.ok(new LoginResponse(token, userAppDto));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody AuthRequest authRequest) {
        UserApp userApp = userService.registerUser(authRequest.getEmail(), authRequest.getPassword(), authRequest.getName(), authRequest.getLastname());
        System.out.println("Endpoint register: " + authRequest.getEmail() + " " + authRequest.getPassword() + " " + authRequest.getName() + " " + authRequest.getLastname());
        UserAppDto dto = UserAppMapper.toDto(userApp);
        final String token = jwtUtil.generateToken(userApp.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, dto));
    }

    @Setter
    @Getter
    public static class AuthRequest {
        private String email;
        private String password;
        private String name;
        private String lastname;
    }

    @Setter
    @Getter
    public static class LoginResponse {
        private String token;
        private UserAppDto user;

        public LoginResponse(String token, UserAppDto user) {
            this.token = token;
            this.user = user;
        }

    }
}
