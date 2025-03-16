package com.expfool.leaderboard.auth.mapper;

import com.expfool.leaderboard.auth.domain.UserResponse;
import com.expfool.leaderboard.auth.domain.keycloak.KeycloakUserInfoResponse;
import com.expfool.libs.mapper.DirectMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserInfoMapper extends DirectMapper<KeycloakUserInfoResponse,  UserResponse> {
}
