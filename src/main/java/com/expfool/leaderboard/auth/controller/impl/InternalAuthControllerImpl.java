package com.expfool.leaderboard.auth.controller.impl;

import com.expfool.leaderboard.auth.controller.InternalAuthController;
import com.expfool.leaderboard.auth.domain.ActionPermissionsRequest;
import com.expfool.leaderboard.auth.domain.RegistrationRequest;
import com.expfool.leaderboard.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InternalAuthControllerImpl implements InternalAuthController {

    private final AuthService authService;

    @Override
    public void registerUser(RegistrationRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Register user request: {}", request);
        }
        authService.register(request);
    }

    @Override
    public void addPermissions(ActionPermissionsRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Add permissions request for '{}' users", request.getUserPermissions().size());
        }
        authService.addPermissions(request);
    }

    @Override
    public void deletePermissions(ActionPermissionsRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Delete permissions request for '{}' users", request.getUserPermissions().size());
        }
        authService.deletePermissions(request);
    }
}