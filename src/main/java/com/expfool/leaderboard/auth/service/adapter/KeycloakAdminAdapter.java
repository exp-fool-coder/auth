package com.expfool.leaderboard.auth.service.adapter;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Optional;
import java.util.UUID;

public interface KeycloakAdminAdapter {

    Optional<UserRepresentation> findByUsername(String email);

    boolean createUser(UserRepresentation createRequest);

    void updateUser(UserRepresentation updateRequest);

    Optional<UserRepresentation> findByExternalId(UUID externalId);

    void resetPassword(UserRepresentation user, CredentialRepresentation credentials);

}
