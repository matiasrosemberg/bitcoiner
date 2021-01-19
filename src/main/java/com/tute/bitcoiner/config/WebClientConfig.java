package com.tute.bitcoiner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String url = "https://cex.io/api/last_price/BTC/USD";

    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}