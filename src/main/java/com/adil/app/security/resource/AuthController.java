package com.adil.app.security.resource;

import com.adil.app.security.dto.AuthResponse;
import com.adil.app.security.dto.ChangePasswordRequest;
import com.adil.app.security.dto.LoginRequest;
import com.adil.app.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        log.info("Start recource : Login with request: {}", request);
        var result = authService.login(request);
        log.info("End recource : Login with request: {}", request);
        return result;
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        log.info("Start resource : refresh token");
        var result = authService.refreshAccessToken(refreshToken);
        log.info("End resource : refresh token");
        return result;
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        log.info("Start resource : change password");
        authService.changePassword(request);
        log.info("End resource : change password");
    }
}
