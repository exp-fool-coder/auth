package com.expfool.leaderboard.auth.controller;

import com.expfool.leaderboard.auth.domain.ActionPermissionsRequest;
import com.expfool.leaderboard.auth.domain.RegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "internal-auth", description = "API для работы с данными пользователя")
public interface InternalAuthController {

    @Operation(summary = "Регистрация пользователя")
    @PostMapping(value = "/auth/register", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Аккаунт успешно создан"),
            @ApiResponse(responseCode = "400", description = "Аккаунт уже существует")
    })
    void registerUser(@Parameter(description = "Данные аккаунта", required = true)
                      @RequestBody @NotNull @Valid RegistrationRequest request);

    @Operation(summary = "Добавление прав доступа пользователя")
    @PostMapping(value = "/auth/users/permissions", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Добавление прав доступа прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Возникли проблемы с добавлением")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addPermissions(@Parameter(description = "Данные прав доступа пользователя", required = true)
                        @RequestBody @NotNull @Valid ActionPermissionsRequest request);

    @Operation(summary = "Удаление прав доступа пользователя")
    @DeleteMapping(value = "/auth/users/permissions", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Удаление прав доступа прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Возникли проблемы с удалением")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePermissions(@Parameter(description = "Данные прав доступа пользователя", required = true)
                           @RequestBody @NotNull @Valid ActionPermissionsRequest request);
}
