package com.expfool.leaderboard.auth.service;

import com.expfool.leaderboard.auth.domain.*;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdapter;
import com.expfool.leaderboard.auth.service.adapter.KeycloakAdminAdapter;
import com.expfool.leaderboard.auth.service.adapter.OtpAdapter;
import com.expfool.libs.testlib.IntegrationTest;
import com.expfool.libs.weblib.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@IntegrationTest
@DisplayName("Управление пользователями")
public class AuthServiceImplTest {

    private final static String EMAIL = "email@test.ru";
    private final static String EMAIL_SECOND = "email2@test.ru";
    private final static UUID EXTERNAL_ID = UUID.randomUUID();
    private final static UUID CLIENT_GUID = UUID.randomUUID();

    @Autowired
    private AuthService authService;

    @MockBean
    private KeycloakAdminAdapter keycloakAdminAdapter;

    @MockBean
    private KeycloakAdapter keycloakAdapter;

    @MockBean
    private OtpAdapter otpAdapter;

    @Test
    @DisplayName("Установить новый пароль")
    void resetPassword() {
        var request = new ResetPasswordRequest();
        request.setEmail(EMAIL);
        request.setPassword(UUID.randomUUID().toString());
        request.setOperationCode(UUID.randomUUID().toString());

        var validationResponse = mock(ValidationOtpResponse.class);
        when(validationResponse.getExternalClientId()).thenReturn(EXTERNAL_ID);
        var user = mock(UserRepresentation.class);
        when(user.getUsername()).thenReturn(EMAIL);

        when(otpAdapter.validateCode(request.getOperationCode())).thenReturn(validationResponse);
        when(keycloakAdminAdapter.findByExternalId(EXTERNAL_ID)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> authService.resetPassword(request));
    }

    @Test
    @DisplayName("Установить новый пароль (не совпадают эл. почты)")
    void resetPasswordEmailDoesntMatch() {
        var request = new ResetPasswordRequest();
        request.setEmail(EMAIL);
        request.setPassword(UUID.randomUUID().toString());
        request.setOperationCode(UUID.randomUUID().toString());

        var validationResponse = mock(ValidationOtpResponse.class);
        when(validationResponse.getExternalClientId()).thenReturn(EXTERNAL_ID);
        var user = mock(UserRepresentation.class);
        when(user.getUsername()).thenReturn(EMAIL_SECOND);

        when(otpAdapter.validateCode(request.getOperationCode())).thenReturn(validationResponse);
        when(keycloakAdminAdapter.findByExternalId(EXTERNAL_ID)).thenReturn(Optional.of(user));

        assertThrows(BadRequestException.class, () -> authService.resetPassword(request));
    }

    @Test
    @DisplayName("Установить новый пароль (не найден пользователь)")
    void resetPasswordUserNotFound() {
        // given
        var request = new ResetPasswordRequest();
        request.setEmail(EMAIL);
        request.setPassword(UUID.randomUUID().toString());
        request.setOperationCode(UUID.randomUUID().toString());

        var validationResponse = mock(ValidationOtpResponse.class);
        when(validationResponse.getExternalClientId()).thenReturn(EXTERNAL_ID);

        when(otpAdapter.validateCode(request.getOperationCode())).thenReturn(validationResponse);
        when(keycloakAdminAdapter.findByExternalId(EXTERNAL_ID)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> authService.resetPassword(request));
    }

    @Test
    @DisplayName("Регистрация пользователя")
    void register() {
        var request = new RegistrationRequest();
        request.setEmail(EMAIL);
        request.setExternalId(EXTERNAL_ID);
        request.setClientGuid(CLIENT_GUID);

        when(keycloakAdminAdapter.findByUsername(EMAIL)).thenReturn(Optional.empty());
        when(keycloakAdminAdapter.createUser(any())).thenReturn(true);

        assertDoesNotThrow(() -> authService.register(request));
        verify(keycloakAdminAdapter, times(1)).createUser(any());
    }

    @Test
    @DisplayName("Регистрация пользователя (пользователь с таким логином уже есть)")
    void registerEmailAlreadyExists() {
        var request = new RegistrationRequest();
        request.setEmail(EMAIL);
        request.setExternalId(EXTERNAL_ID);
        request.setClientGuid(CLIENT_GUID);

        var user = mock(UserRepresentation.class);
        user.setUsername(EMAIL);

        when(keycloakAdminAdapter.findByUsername(EMAIL)).thenReturn(Optional.of(user));
        assertThrows(BadRequestException.class, () -> authService.register(request));
        verify(keycloakAdminAdapter, times(0)).createUser(any());
    }

    @Test
    @DisplayName("Регистрация пользователя (клиентская ошибка keycloak)")
    void registerKeycloakClientError() {
        var request = new RegistrationRequest();
        request.setEmail(EMAIL);
        request.setExternalId(EXTERNAL_ID);
        request.setClientGuid(CLIENT_GUID);

        when(keycloakAdminAdapter.findByUsername(EMAIL)).thenReturn(Optional.empty());
        when(keycloakAdminAdapter.createUser(any(UserRepresentation.class))).thenReturn(false);
        assertThrows(BadRequestException.class, () -> authService.register(request));
        verify(keycloakAdminAdapter, times(1)).createUser(any());
    }

    @Test
    @DisplayName("Добавление прав доступа пользователю")
    void addPermissions() {
        var externalId = UUID.randomUUID();
        var userPermissions = new UserPermissions();
        userPermissions.setExternalId(externalId);
        userPermissions.setPermissions(List.of("PERMISSION"));

        var request = new ActionPermissionsRequest();
        request.setUserPermissions(List.of(userPermissions));

        var user = mock(UserRepresentation.class);
        when(keycloakAdminAdapter.findByExternalId(externalId)).thenReturn(Optional.of(user));
        authService.addPermissions(request);
        verify(keycloakAdminAdapter, times(1)).updateUser(any());
    }

    @Test
    @DisplayName("Добавление прав доступа пользователю (пользователь не найден)")
    void addPermissionsUserNotFound() {
        var externalId = UUID.randomUUID();
        var userPermissions = new UserPermissions();
        userPermissions.setExternalId(externalId);
        userPermissions.setPermissions(List.of("PERMISSION"));

        var request = new ActionPermissionsRequest();
        request.setUserPermissions(List.of(userPermissions));

        when(keycloakAdminAdapter.findByExternalId(externalId)).thenReturn(Optional.empty());
        authService.addPermissions(request);
        verify(keycloakAdminAdapter, times(0)).updateUser(any());
    }

    @Test
    @DisplayName("Удаление прав доступа у пользователя")
    void deletePermissions() {
        var externalId = UUID.randomUUID();
        var userPermissions = new UserPermissions();
        userPermissions.setExternalId(externalId);
        userPermissions.setPermissions(List.of("PERMISSION"));

        var request = new ActionPermissionsRequest();
        request.setUserPermissions(List.of(userPermissions));

        var user = mock(UserRepresentation.class);
        when(keycloakAdminAdapter.findByExternalId(externalId)).thenReturn(Optional.of(user));
        authService.deletePermissions(request);
        verify(keycloakAdminAdapter, times(1)).updateUser(any());
    }

    @Test
    @DisplayName("Удаление прав доступа у пользователя (пользователь не найден)")
    void deletePermissionsUserNotFound() {
        var externalId = UUID.randomUUID();
        var userPermissions = new UserPermissions();
        userPermissions.setExternalId(externalId);
        userPermissions.setPermissions(List.of("PERMISSION"));

        var request = new ActionPermissionsRequest();
        request.setUserPermissions(List.of(userPermissions));

        when(keycloakAdminAdapter.findByExternalId(externalId)).thenReturn(Optional.empty());
        authService.deletePermissions(request);
        verify(keycloakAdminAdapter, times(0)).updateUser(any());
    }
}
