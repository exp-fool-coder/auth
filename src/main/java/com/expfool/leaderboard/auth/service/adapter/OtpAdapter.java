package com.expfool.leaderboard.auth.service.adapter;

import com.expfool.leaderboard.auth.domain.ValidationOtpResponse;

public interface OtpAdapter {

    ValidationOtpResponse validateCode(String operationCode);
}
