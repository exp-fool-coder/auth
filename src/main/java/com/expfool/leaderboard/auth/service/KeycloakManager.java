package com.expfool.leaderboard.auth.service;

import org.keycloak.admin.client.resource.UsersResource;

public interface KeycloakManager {

    UsersResource getUsersResource();
}
