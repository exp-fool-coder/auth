package com.expfool.leaderboard.auth.service.adapter.impl;

import com.expfool.leaderboard.auth.service.adapter.KeycloakAdminAdapter;
import org.keycloak.representations.account.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.Optional;
import java.util.UUID;

public class KeycloakAdminAdapterImpl implements KeycloakAdminAdapter {
    @Override
    public Optional<UserRepresentation> findByUsername(String email) {
            return Optional.empty();
    }

    @Override
    public boolean createUser(UserRepresentation createRequest) {
        return false;
    }

    @Override
    public void updateUser(UserRepresentation updateRequest) {

    }

    @Override
    public Optional<UserRepresentation> findByExternalId(UUID externalId) {
        return Optional.empty();
    }

    @Override
    public void resetPassword(UserRepresentation user, CredentialRepresentation credentials) {

    }
}
