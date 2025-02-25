package com.expfool.leaderboard.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ValidationOtpResponse {

    private UUID operationId;

    private UUID externalClientId;
}

