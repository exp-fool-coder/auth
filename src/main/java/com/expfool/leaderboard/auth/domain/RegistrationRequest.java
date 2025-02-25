package com.expfool.leaderboard.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на регистрацию пользователя")
public class RegistrationRequest {

    @NotNull
    @Schema(description = "Внутренний идентификатор пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID externalId;

    @NotNull
    @Schema(description = "Внешний идентификатор пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID clientGuid;

    @NotBlank
    @Schema(description = "Полное Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotBlank
    @Schema(description = "Фамилия пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotBlank
    @Schema(description = "Корпоративная почта пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9!#$%&*+\\-=?^_~]+(.[A-Za-z0-9!#$%&*+\\-=?^_~]+)*@(?![.\\-])[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,100})$")
    private String email;
}