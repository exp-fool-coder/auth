package com.expfool.leaderboard.auth.exception;

import com.expfool.libs.weblib.exception.CustomRuntimeException;
import com.expfool.libs.weblib.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static com.expfool.leaderboard.auth.exception.AuthErrorCode.AUTH_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class KeycloakAuthenticationException extends CustomRuntimeException {
    public KeycloakAuthenticationException(ErrorCode errorCode, HttpStatus status, String message) {
        super(errorCode, status, message);
    }

    public static KeycloakAuthenticationException authenticationFailed(String message) {
        return new KeycloakAuthenticationException(AUTH_FAILED, UNAUTHORIZED, message);
    }

}
