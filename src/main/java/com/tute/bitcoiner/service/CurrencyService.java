package com.tute.bitcoiner.service;

import com.tute.bitcoiner.client.CurrencyClient;
import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.dto.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@EnableScheduling
@Service
public class CurrencyService {

    private final CurrencyClient currencyClient;
    private ConcurrentHashMap<Long, Currency> currencies;
    private Double sum;
    private Long counter;
    private Double average;

    @Autowired
    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
        this.currencies = new ConcurrentHashMap<>();
        this.sum = 0D;
        this.counter = 0L;
        this.average = 0D;
    }

    public Currency getCurrency(Timestamp timestamp) {
        return currencies.get(timestamp.getEpoch());
    }

    public Currency getCurrencyByTimestamp(Timestamp timestamp) {
        return currencies.get(timestamp.getEpoch());
    }

    public Double getAverage() {
        return average;
    }

    @Scheduled(fixedRate = 5000)
    public void addNewCurrency() {
        currencyClient.getCurrentCurrency().doOnNext(v -> {
            currencies.put(v.getTimestamp().getEpoch(), v);
            sum = sum + v.getDollarsEquivalent();
            counter++;
            average = sum / counter;
        }).subscribe();
    }

}
