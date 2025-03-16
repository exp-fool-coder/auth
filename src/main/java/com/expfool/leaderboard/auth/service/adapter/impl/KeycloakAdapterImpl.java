package com.expfool.leaderboard.auth.service.adapter.impl;

import com.expfool.leaderboard.auth.config.properties.KeycloakClientProperties;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakAuthRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakLogoutRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakRefreshRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakUserInfoResponse;
import com.expfool.leaderboard.auth.external.KeycloakClient;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdapter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import static com.expfool.leaderboard.auth.exception.KeycloakAuthenticationException.authenticationFailed;
import static com.expfool.libs.weblib.exception.InternalServerException.serverError;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdapterImpl implements KeycloakAdapter {

    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";


    private final KeycloakClient keycloakClient;
    private final KeycloakClientProperties properties;


    @Override
    public AccessTokenResponse login(String email, String password) {
        log.debug("Login of user '{}'", email);
        KeycloakAuthRequest request = KeycloakAuthRequest
                .builder()
                .client_id(properties.getKeycloakClientId())
                .client_secret(properties.getKeycloakClientSecret())
                .grant_type(GRANT_TYPE_PASSWORD)
                .username(email)
                .password(password)
                .build();
        try {
            var response = keycloakClient.getToken(request);
            log.debug("Complete login of user '{}'", email);
            return response;
        } catch (FeignException.FeignClientException exception) {
            log.warn("The user's authorization with the email '{}' failed in keycloak", email);
            throw authenticationFailed("Ошибка при попытке авторизации");
        } catch (FeignException exception) {
            log.error("Error while login in keycloak");
            throw serverError("Не удалось осуществить авторизацию, попробуйте позже");
        }

    }

    @Override
    public void logout(String refreshToken) {
        log.debug("Logout by token '{}'", refreshToken);
        KeycloakLogoutRequest request = KeycloakLogoutRequest
                .builder()
                .refresh_token(refreshToken)
                .client_id(properties.getKeycloakClientId())
                .client_secret(properties.getKeycloakClientSecret())
                .build();
        try {
            keycloakClient.logout(request);
            if (log.isDebugEnabled()) {
                log.debug("Complete logout by token '{}'", refreshToken);
            }
        } catch (FeignException.FeignClientException exception) {
            log.warn("The logout from session was failed in keycloak");
            throw authenticationFailed("Ошибка при попытке выхода");
        } catch (FeignException exception) {
            log.error("Error while logout in keycloak");
            throw serverError("Не удалось осуществить выход, попробуйте позже");
        }

    }

    @Override
    public AccessTokenResponse refresh(String refreshToken) {
        log.debug("Refresh by token '{}'", refreshToken);
        KeycloakRefreshRequest request = KeycloakRefreshRequest
                .builder()
                .refresh_token(refreshToken)
                .client_id(properties.getKeycloakClientId())
                .client_secret(properties.getKeycloakClientSecret())
                .grant_type(GRANT_TYPE_REFRESH_TOKEN)
                .build();
        try {
            var response = keycloakClient.refreshToken(request);
            log.debug("Complete refresh by token '{}'", refreshToken);
            return response;
        } catch (FeignException.FeignClientException exception) {
            log.warn("The refresh token request was failed in keycloak");
            throw authenticationFailed("Ошибка при попытке обновления токена");
        } catch (FeignException exception) {
            log.error("Error while refreshing token in keycloak");
            throw serverError("Не удалось обновить токен, попробуйте позже");
        }

    }

    @Override
    public KeycloakUserInfoResponse getUser(String accessToken) {
        try {
            return keycloakClient.getUser(accessToken);
        } catch (FeignException.FeignClientException exception) {
            log.warn("User info request was failed in keycloak");
            throw authenticationFailed("Ошибка при попытке получения информации о пользователе");
        } catch (FeignException exception) {
            log.error("Error while getting user info in keycloak");
            throw serverError("Не удалось получить информацию о пользователе, попробуйте позже");
        }

    }
}
