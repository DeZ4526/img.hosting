package com.sservice.img.hosting.service;

import com.sservice.img.hosting.dto.AuthenticationResponse;
import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.dto.UserRegisterRequest;
import com.sservice.img.hosting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(JwtService jwtService,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService1) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService1;
    }
    public String registerUser(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new EntityNotFoundException("A user with that name already exists!");
        }

        UserData user = new UserData();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode( request.getPassword()));

        userRepository.save(user);
        return "Регистрация прошла успешно!";
    }

    public ResponseEntity<?> loginUser(UserRegisterRequest request) {
        Optional<UserData> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException("A user with that name was not found!");
        }

        UserData user = userOpt.get();

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            String jwt = jwtService.generateToken(user.getUsername());

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            throw new IllegalStateException("Incorrect password!");
        }
    }
}
