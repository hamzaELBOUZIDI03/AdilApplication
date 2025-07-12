package com.adil.app.security.service;

import com.adil.app.exception.FunctionalException;
import com.adil.app.security.domain.AppUser;
import com.adil.app.security.dto.AuthResponse;
import com.adil.app.security.dto.ChangePasswordRequest;
import com.adil.app.security.dto.LoginRequest;
import com.adil.app.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AppUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        log.info("Start service login request : {}", request);
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            AppUser user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String accessToken = jwtService.generateToken(user.getUsername());
            String refreshToken = jwtService.generateToken(user.getUsername());

            user.setRefreshToken(refreshToken);
            userRepository.save(user);

            var result = AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            log.info("End service login request : {}", request);
            return result;
        } catch (BadCredentialsException e) {
            throw new FunctionalException("Invalid username or password");
        }
    }

    public AuthResponse refreshAccessToken(String refreshToken) {
        log.info("Start refresh access token");
        String username = jwtService.validateTokenAndGetUsername(refreshToken);
        if (username == null) throw new FunctionalException("Invalid refresh token");

        AppUser user = userRepository.findByUsername(username).orElseThrow();
        if (!refreshToken.equals(user.getRefreshToken()))
            throw new FunctionalException("Invalid refresh token");

        String newAccessToken = jwtService.generateToken(username);
        var result =  AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
        log.info("End refresh access token");
        return result;
    }

    public void changePassword(ChangePasswordRequest request) {
        log.info("Start service : change password");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = userRepository.findByUsername(username).orElseThrow();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new FunctionalException("Invalid old password");
        }
        if (request.getNewPassword().length() < 8) {
            throw new FunctionalException("Password too short");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        log.info("Start service : change password");
    }
}
