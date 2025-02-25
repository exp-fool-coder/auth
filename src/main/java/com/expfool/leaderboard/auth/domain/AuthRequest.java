package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Запрос на авторизацию пользователя по паролю")
public class AuthRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9!#$%&*+\\-=?^_~]+(.[A-Za-z0-9!#$%&*+\\-=?^_~]+)*@(?![.\\-])[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$")
    @Schema(description = "Почта пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Schema(description = "Пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
