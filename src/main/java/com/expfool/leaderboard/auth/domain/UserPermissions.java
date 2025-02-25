package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Данные запроса на обновление прав доступа пользователя")
public class UserPermissions {

    @NotNull
    @Schema(description = "Внешний идентификатор пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID externalId;

    @NotEmpty
    @Schema(description = "Алиасы прав доступа пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> permissions;
}