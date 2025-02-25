package com.expfool.leaderboard.auth.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(value = BaseProperties.PROPERTIES)
public class BaseProperties {

    public static final String PROPERTIES = "leaderboard.auth";
}
