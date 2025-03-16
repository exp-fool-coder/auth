package com.expfool.leaderboard.auth.external;

import com.expfool.leaderboard.auth.domain.ValidationEncodedOtpRequest;
import com.expfool.leaderboard.auth.domain.ValidationOtpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "otp", url = "${teamspace.auth.feign.otp-url}")
public interface OtpProviderClient {

    @GetMapping(value = "/otp-service/encoded/validation")
    ValidationOtpResponse validateCode(@RequestBody ValidationEncodedOtpRequest request);
}
