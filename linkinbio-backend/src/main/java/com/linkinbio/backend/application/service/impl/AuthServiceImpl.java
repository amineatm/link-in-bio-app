package com.linkinbio.backend.application.service.impl;

import com.linkinbio.backend.adapters.dto.auth.TokenResponse;
import com.linkinbio.backend.config.JwtUtil;
import com.linkinbio.backend.domain.model.User;
import com.linkinbio.backend.domain.repository.UserRepository;
import com.linkinbio.backend.domain.service.IAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public TokenResponse register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));

        userRepository.save(user);
        return new TokenResponse(jwtUtil.generateToken(user));
    }

    @Override
    public TokenResponse  login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new TokenResponse(jwtUtil.generateToken(user));
    }
}

