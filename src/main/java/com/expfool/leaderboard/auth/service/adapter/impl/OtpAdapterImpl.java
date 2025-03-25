package com.expfool.leaderboard.auth.service.adapter.impl;

import com.expfool.leaderboard.auth.domain.ValidationEncodedOtpRequest;
import com.expfool.leaderboard.auth.domain.ValidationOtpResponse;
import com.expfool.leaderboard.auth.external.OtpProviderClient;
import com.expfool.leaderboard.auth.service.adapter.OtpAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpAdapterImpl implements OtpAdapter {

    private final OtpProviderClient otpProviderClient;

    @Override
    public ValidationOtpResponse validateCode(String operationCode) {
        log.info("Send validation request to otp service");
        var request = new ValidationEncodedOtpRequest(operationCode);
        var result = otpProviderClient.validateCode(request);
        log.info("Successful validation otp");
        return result;
    }
}
