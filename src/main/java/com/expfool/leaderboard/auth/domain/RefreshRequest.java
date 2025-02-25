package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Запрос на обновление токена")
public class RefreshRequest {

    @NotBlank
    @Schema(description = "Refresh-токен сессии", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;
}

