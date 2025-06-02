package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.dto.UserAppDto;
import com.example.taskmanagerbackend.mapper.UserAppMapper;
import com.example.taskmanagerbackend.model.LoginResponse;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.security.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class GoogleAuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final GoogleIdTokenVerifier verifier;

    public GoogleAuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("58855178088-941rnpr9nbo58af6vmub1sha77c6nng4.apps.googleusercontent.com"))
                .build();
    }

    public LoginResponse loginOrRegisterWithGoogle(String idTokenString) throws Exception {
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new Exception("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

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

        return new LoginResponse(token, dto);
    }
}



