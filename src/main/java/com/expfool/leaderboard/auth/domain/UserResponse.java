package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Данные пользователя")
public class UserResponse {

    @Schema(description = "Имя пользователя")
    private String email;

    @Schema(description = "Уникальный идентификатор пользователя")
    private UUID externalId;

    @Schema(description = "Список прав доступа пользователя")
    private List<String> userPermissions;
}
