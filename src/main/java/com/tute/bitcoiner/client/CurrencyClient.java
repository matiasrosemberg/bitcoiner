package com.tute.bitcoiner.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyClient {

    private final WebClient webClient;

    @Autowired
    public CurrencyClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getCurrentCurrency() {
        return webClient
                .get()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .bodyToMono(String.class);
    }

}
