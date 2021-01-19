package com.tute.bitcoiner.client;

import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.dto.LastPrice;
import com.tute.bitcoiner.exception.CurrencyException;
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

    public Mono<Currency> getCurrentCurrency() {
        try {
            return webClient
                    .get()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LastPrice.class).map(v -> new Currency(Double.parseDouble(v.getLprice())));
        } catch (Exception e) {
            throw new CurrencyException("failed to get last price from entity");
        }
    }

}
