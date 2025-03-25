package com.expfool.leaderboard.auth.service.impl;

import com.expfool.leaderboard.auth.domain.*;
import com.expfool.leaderboard.auth.mapper.AccessResponseMapper;
import com.expfool.leaderboard.auth.mapper.UserInfoMapper;
import com.expfool.leaderboard.auth.mapper.UserRepresentationMapper;
import com.expfool.leaderboard.auth.service.AuthService;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdapter;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdminAdapter;
import com.expfool.leaderboard.auth.service.adapter.OtpAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepresentationMapper userRepresentationMapper;
    private final AccessResponseMapper accessResponseMapper;
    private final OtpAdapter otpAdapter;
    private final KeycloakAdapter keycloakAdapter;
    private final KeycloakAdminAdapter keycloakAdminAdapter;
    private final UserInfoMapper userInfoMapper;

    @Override
    public void register(RegistrationRequest registrationRequest) {

    }

    @Override
    public AccessResponse login(AuthRequest request) {
        return null;
    }

    @Override
    public void logout(LogoutRequest request) {

    }

    @Override
    public AccessResponse refresh(RefreshRequest request) {
        return null;
    }

    @Override
    public void addPermissions(ActionPermissionsRequest request) {

    }

    @Override
    public void deletePermissions(ActionPermissionsRequest request) {

    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

    }

    @Override
    public UserResponse getUserInfo(String accessToken) {
        return null;
    }
}
