package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.dto.UserAppDto;
import com.example.taskmanagerbackend.mapper.UserAppMapper;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.security.JwtUtil;
import com.example.taskmanagerbackend.service.UserAppService;
import com.example.taskmanagerbackend.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        try {
            String idToken = request.getIdToken();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList("58855178088-941rnpr9nbo58af6vmub1sha77c6nng4.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID Token");
            }

            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("given_name");
            String lastname = (String) payload.get("family_name");

            UserApp user = userService.findByEmail(email);

            if (user == null) {
                user = new UserApp();
                user.setEmail(email);
                user.setName(name);
                user.setLastname(lastname);
                user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                userService.save(user);
            }

            String token = jwtUtil.generateToken(user.getEmail());
            UserAppDto dto = UserAppMapper.toDto(user);

            return ResponseEntity.ok(new LoginResponse(token, dto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar login con Google: " + e.getMessage());
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

    @Getter
    @Setter
    public static class GoogleLoginRequest {
        private String idToken;
    }
}
