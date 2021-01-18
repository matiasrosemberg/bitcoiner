package com.tute.bitcoiner.client;

import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.exception.CurrencyException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Component
public class CurrencyClient {

    private final String url = "https://cex.io/api/last_price/BTC/USD";


    private final RestTemplate restTemplate;

    public CurrencyClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        this.restTemplate = new RestTemplate(requestFactory);
    }

    public Currency getCurrentCurrency() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> lastPrice = restTemplate.getForEntity(url, String.class,headers);

            return new Currency(Double.parseDouble(Objects.requireNonNull(lastPrice.getBody())));
        } catch (RestClientException e) {
            throw new CurrencyException("failed to reach bitcoin currency client", e);
        } catch (NullPointerException e) {
            throw new CurrencyException("failed to get last price from entity");
        }
    }

}
