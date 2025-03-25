package com.expfool.leaderboard.auth.service.impl;

import com.expfool.leaderboard.auth.config.properties.KeycloakAdminProperties;
import com.expfool.leaderboard.auth.service.KeycloakManager;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakManagerImpl implements KeycloakManager {

    private final Keycloak keycloak;
    private final KeycloakAdminProperties keycloakAdminProperties;

    @Override
    public UsersResource getUsersResource() {
        var realm = keycloakAdminProperties.getRealm();
        var realmResource = keycloak.realm(realm);
        return realmResource.users();
    }
}
