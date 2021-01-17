package com.tute.bitcoiner.client;

import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.exception.CurrencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyClient {

    RestTemplate restTemplate = new RestTemplate();

    private final String url = "https://cex.io/api/last_price/BTC/USD";

    public Currency getCurrentCurrency() {
        try {
            ResponseEntity<Currency> forEntity = restTemplate.getForEntity(url, Currency.class);
            return forEntity.getBody();
        }catch (RestClientException e) {
            throw new CurrencyException("failed to reach bitcoin currency service",e);
        }
    }

}
