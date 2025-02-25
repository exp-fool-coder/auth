package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные авторизации")
public class AccessResponse {

    @Schema(description = "Токен доступа")
    private String accessToken;

    @Schema(description = "Время жизни токена доступа в секундах")
    private long expiresIn;

    @Schema(description = "Токен обновления сессии")
    private String refreshToken;

    @Schema(description = "Время жизни токена обновления")
    private long refreshExpiresIn;
}
