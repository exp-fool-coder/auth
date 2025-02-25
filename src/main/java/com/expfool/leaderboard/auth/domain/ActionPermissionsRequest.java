package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Запрос на обновление прав доступа пользователей")
public class ActionPermissionsRequest {

    @NotEmpty
    @Schema(description = "Данные изменения прав доступа пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<UserPermissions> userPermissions;

}
