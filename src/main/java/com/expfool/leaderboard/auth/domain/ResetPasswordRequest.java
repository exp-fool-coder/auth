package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Запрос на сброс пароля пользователя")
public class ResetPasswordRequest {

    @NotBlank
    @Schema(description = "Код операции", requiredMode = Schema.RequiredMode.REQUIRED)
    private String operationCode;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d._@-]{12,}$")
    @Schema(description = "Новый пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Schema(description = "Логин пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
