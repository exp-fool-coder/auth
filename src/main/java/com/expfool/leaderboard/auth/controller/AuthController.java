package com.expfool.leaderboard.auth.controller;

import com.expfool.leaderboard.auth.domain.AccessResponse;
import com.expfool.leaderboard.auth.domain.AuthRequest;
import com.expfool.leaderboard.auth.domain.LogoutRequest;
import com.expfool.leaderboard.auth.domain.RefreshRequest;
import com.expfool.leaderboard.auth.domain.ResetPasswordRequest;
import com.expfool.leaderboard.auth.domain.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "auth", description = "API для управления аутентификацией и авторизацией пользователей")
public interface AuthController {

    @Operation(summary = "Авторизация пользователя")
    @PostMapping(value = "/api/v1/auth/login", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Авторизация прошла успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    AccessResponse login(@Parameter(description = "Данные для входа", required = true)
                         @RequestBody @NotNull @Valid AuthRequest request);

    @Operation(summary = "Логаут пользователя")
    @PostMapping(value = "/api/v1/auth/logout", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Логаут прошел успешно"
            ),
            @ApiResponse(responseCode = "400",
                    description = "Некорректные данные"
            ),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void logout(@Parameter(description = "Данные текущей сессии", required = true)
                @RequestBody @NotNull @Valid LogoutRequest request);


    @Operation(summary = "Обновление токена")
    @PostMapping(value = "/api/v1/auth/refresh", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление токена прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Возникли проблемы с обновлением токена"),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    AccessResponse refresh(@Parameter(description = "Данные текущей сессии", required = true)
                           @RequestBody @NotNull @Valid RefreshRequest request);


    @Operation(summary = "Сброс пароля")
    @PostMapping(value = "/api/v1/auth/users/password", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Сброс пароля прошел успешно"),
            @ApiResponse(responseCode = "400", description = "Возникли проблемы со сбросом пароля")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void resetPassword(@Parameter(description = "Данные для сброса пароля", required = true)
                       @RequestBody @NotNull @Valid ResetPasswordRequest request);

    @Operation(summary = "Информация о текущем пользователе")
    @GetMapping(value = "/api/v1/auth/me")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно передана"),
            @ApiResponse(responseCode = "400", description = "Ошибка при выполнении запроса информации")
    })
    UserResponse getUserInfo(@RequestHeader(value = HttpHeaders.AUTHORIZATION) @NotBlank String accessToken);
}