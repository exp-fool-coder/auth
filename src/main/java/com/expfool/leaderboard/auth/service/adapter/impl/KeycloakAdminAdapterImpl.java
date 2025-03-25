package com.expfool.leaderboard.auth.service.adapter.impl;

import com.expfool.leaderboard.auth.service.KeycloakManager;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdminAdapter;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.expfool.leaderboard.auth.util.UserAttributesUtil.EXTERNAL_ID;
import static com.expfool.libs.weblib.exception.InternalServerException.serverError;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdminAdapterImpl implements KeycloakAdminAdapter {

    private final static String SEARCH_QUERY_FORMAT = "%s:%s";

    private final KeycloakManager keycloakManager;

    @Override
    public Optional<UserRepresentation> findByUsername(String email) {
        try {
            var usersResource = keycloakManager.getUsersResource();
            var userByEmail = usersResource.searchByUsername(email, true);
            return userByEmail.stream().findFirst();
        } catch (Exception ex) {
            log.error("Error while find user with email '{}' in keycloak", email);
            throw serverError("Не удалось подключиться к внешней системе, попробуйте позже");
        }
    }

    @Override
    public boolean createUser(UserRepresentation user) {
        var usersResource = keycloakManager.getUsersResource();
        try (var response = usersResource.create(user)) {
            return response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL;
        } catch (Exception ex) {
            log.error("Error while registration user with email '{}' in keycloak", user.getEmail());
            throw serverError("Не удалось создать аккаунт пользователя, попробуйте позже");
        }
    }

    @Override
    public void updateUser(UserRepresentation user) {
        try {
            var userResource = getUserResourceById(user.getId());
            userResource.update(user);
        } catch (Exception ex) {
            log.error("Error while update user with email '{}' in keycloak", user.getEmail());
            throw serverError("Не удалось обновить аккаунт пользователя, попробуйте позже");
        }
    }

    @Override
    public Optional<UserRepresentation> findByExternalId(UUID externalId) {
        try {
            var usersResource = keycloakManager.getUsersResource();
            var result = usersResource.searchByAttributes(SEARCH_QUERY_FORMAT.formatted(EXTERNAL_ID, externalId));
            return result.stream().findFirst();
        } catch (Exception ex) {
            log.error("Error find user with external id '{}' in keycloak", externalId);
            throw serverError("Не удалось найти аккаунт пользователя, попробуйте позже");
        }
    }

    @Override
    public void resetPassword(UserRepresentation user, CredentialRepresentation password) {
        try {
            var userResource = getUserResourceById(user.getId());
            userResource.resetPassword(password);
        } catch (Exception ex) {
            log.error("Error while resetting password for user with email '{}'", user.getEmail());
            throw serverError("Не удалось сбросить пароль пользователя, попробуйте позже");
        }
    }

    private UserResource getUserResourceById(String userId) {
        var usersResource = keycloakManager.getUsersResource();
        return usersResource.get(userId);
    }
}
