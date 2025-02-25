package com.expfool.leaderboard.auth.domain.keycloak;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeycloakAuthRequest {

    private String username;

    private String password;

    private String grant_type;

    private String client_id;

    private String client_secret;
}
