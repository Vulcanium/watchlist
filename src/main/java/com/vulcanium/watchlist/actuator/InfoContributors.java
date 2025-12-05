package com.vulcanium.watchlist.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InfoContributors implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app",
                Map.of(
                        "name", "watchlist",
                        "description", "Web service to manage a watchlist of movies",
                        "version", "1.0.0"
                )
        );
    }
}
