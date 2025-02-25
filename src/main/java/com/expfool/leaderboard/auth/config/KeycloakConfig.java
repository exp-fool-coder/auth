package com.expfool.leaderboard.auth.config;

import com.expfool.leaderboard.auth.config.properties.KeycloakAdminProperties;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    private final KeycloakAdminProperties adminProperties;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(adminProperties.getUrl())
                .realm(adminProperties.getAdminRealm())
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(adminProperties.getClientId())
                .username(adminProperties.getUsername())
                .password(adminProperties.getPassword())
                .build();
    }
}
