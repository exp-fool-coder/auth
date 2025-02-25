package com.expfool.leaderboard.auth.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import static com.expfool.leaderboard.auth.config.properties.BaseProperties.PROPERTIES;

@Data
@Validated
@ConfigurationProperties(value = PROPERTIES + ".keycloak.client")
public class KeycloakClientProperties {

    @NotBlank
    private String keycloakClientId;

    @NotBlank
    private String keycloakClientSecret;
}
