package com.expfool.leaderboard.auth.mapper;

import com.expfool.leaderboard.auth.domain.RegistrationRequest;
import org.keycloak.representations.account.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserRepresentationMapper {

    @Mappings(value = {
            @Mapping(source = "email", target = "username")
    })
    UserRepresentation toUserRepresentation(RegistrationRequest registrationRequest);
}

