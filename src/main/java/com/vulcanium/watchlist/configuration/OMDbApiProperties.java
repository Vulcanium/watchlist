package com.vulcanium.watchlist.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.vulcanium.watchlist")
public class OMDbApiProperties {

    private String apiUrl;
}
