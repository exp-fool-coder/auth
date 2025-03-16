package com.expfool.leaderboard.auth.external;

import com.expfool.leaderboard.auth.domain.keycloak.KeycloakAuthRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakLogoutRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakRefreshRequest;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakUserInfoResponse;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "keycloak", url = "${leaderboard.auth.feign.keycloak-url}")
public interface KeycloakClient {

    @PostMapping(value = "/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse getToken(@RequestBody KeycloakAuthRequest authRequest);

    @PostMapping(value = "/logout", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    void logout(@RequestBody KeycloakLogoutRequest logoutRequest);

    @PostMapping(value = "/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse refreshToken(@RequestBody KeycloakRefreshRequest refreshRequest);

    @GetMapping(value = "/userinfo")
    KeycloakUserInfoResponse getUser(@RequestHeader(value = "Authorization") String token);
}
