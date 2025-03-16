package com.expfool.leaderboard.auth.service.adapter;

import com.expfool.leaderboard.auth.domain.keycloak.KeycloakUserInfoResponse;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakAdapter {

    AccessTokenResponse login(String email, String password);

    void logout(String refreshToken);

    AccessTokenResponse refresh(String refreshToken);

    KeycloakUserInfoResponse getUser(String accessToken);

}
