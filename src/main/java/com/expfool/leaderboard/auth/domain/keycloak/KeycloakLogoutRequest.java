package com.expfool.leaderboard.auth.domain.keycloak;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeycloakLogoutRequest {

    private String client_id;

    private String client_secret;

    private String refresh_token;
}
