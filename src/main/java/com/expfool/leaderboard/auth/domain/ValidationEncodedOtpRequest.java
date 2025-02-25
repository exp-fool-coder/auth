package com.expfool.leaderboard.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationEncodedOtpRequest {

    private String operationCode;
}
