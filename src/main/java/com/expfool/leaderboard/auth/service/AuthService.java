package com.expfool.leaderboard.auth.service;

import com.expfool.leaderboard.auth.domain.*;

public interface AuthService {

    void register(RegistrationRequest registrationRequest);

    AccessResponse login(AuthRequest request);

    void logout(LogoutRequest request);

    AccessResponse refresh(RefreshRequest request);

    void addPermissions(ActionPermissionsRequest request);

    void deletePermissions(ActionPermissionsRequest request);

    void resetPassword(ResetPasswordRequest request);

    UserResponse getUserInfo(String accessToken);

}
