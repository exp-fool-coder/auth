package com.expfool.leaderboard.auth.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import static com.expfool.leaderboard.auth.config.properties.BaseProperties.PROPERTIES;

@Data
@Validated
@ConfigurationProperties(value = PROPERTIES + ".keycloak.admin")
public class KeycloakAdminProperties {

    @NotBlank
    private String adminRealm;

    @NotBlank
    private String clientId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String url;

    @NotBlank
    private String realm;
}
