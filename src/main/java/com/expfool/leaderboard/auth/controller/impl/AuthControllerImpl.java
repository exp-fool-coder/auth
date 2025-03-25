package com.expfool.leaderboard.auth.controller.impl;

import com.expfool.leaderboard.auth.controller.AuthController;
import com.expfool.leaderboard.auth.domain.AccessResponse;
import com.expfool.leaderboard.auth.domain.AuthRequest;
import com.expfool.leaderboard.auth.domain.LogoutRequest;
import com.expfool.leaderboard.auth.domain.RefreshRequest;
import com.expfool.leaderboard.auth.domain.ResetPasswordRequest;
import com.expfool.leaderboard.auth.domain.UserResponse;
import com.expfool.leaderboard.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public AccessResponse login(AuthRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Login user request for user with email: {}", request.getEmail());
        }
        return authService.login(request);
    }

    @Override
    public void logout(LogoutRequest request) {
        log.debug("Logout user request");
        authService.logout(request);
    }

    @Override
    public AccessResponse refresh(RefreshRequest request) {
        log.debug("Refresh token request");
        return authService.refresh(request);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        log.info("Reset password request for user with email '{}' ", request.getEmail());
        authService.resetPassword(request);
        log.info("Password was successfully reset");
    }

    @Override
    public UserResponse getUserInfo(String accessToken) {
        log.debug("User info request by access token");
        return authService.getUserInfo(accessToken);
    }
}
