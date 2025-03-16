package com.expfool.leaderboard.auth.mapper;

import com.expfool.leaderboard.auth.domain.AccessResponse;
import org.keycloak.representations.AccessTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccessResponseMapper {

    @Mapping(target = "accessToken", source = "token")
    AccessResponse map(AccessTokenResponse response);
}
