package com.expfool.leaderboard.auth.domain.keycloak;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class KeycloakUserIngoResponse {

    private String email;

    @JsonProperty(value = "external_id")
    private UUID externalId;

    @JsonProperty(value = "user_permissions")
    private List<String> userPermissions;

}
