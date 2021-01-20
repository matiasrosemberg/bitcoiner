package com.tute.bitcoiner.service;

import com.tute.bitcoiner.client.CurrencyClient;
import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.common.Utils;
import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.dto.CurrencyAvg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CurrencyServiceTest {

    @Mock
    Utils utils;

    @Mock
    CurrencyClient currencyClient;

    @InjectMocks
    CurrencyService currencyService = new CurrencyService(currencyClient, utils);

    @BeforeEach
    void fillCurrencies(){
        Instant instant = Instant.now();
        ArrayList<Currency> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Currency currency = createCurrencyOf100(new Timestamp(instant));
            instant = instant.plusSeconds(5);
            arrayList.add(currency);
        }
        arrayList.forEach(v -> currencyService.getCurrencies().put(v.getTimestamp().getEpoch(), v));
        DoubleSummaryStatistics stats = arrayList.stream().map(Currency::getDollarsEquivalent).collect(Collectors.summarizingDouble(Double::doubleValue));
        currencyService.setSum(stats.getSum());
        currencyService.setAverage(stats.getAverage());
        currencyService.setCounter(stats.getCount());
    }

    Currency createCurrencyOf100(Timestamp timestamp){
        return new Currency(100, timestamp);
    }

    @Test
    void getCurrencyByTimestamp() {
    }

    @Test
    void getAverage() {
    }

    @Test
    void addNewCurrency() {

    }

    @Test
    void getCurrencyAverage() {

        Instant instant = Instant.now();

        Timestamp t1 = new Timestamp(instant);
        Timestamp t2 = new Timestamp(instant);
        CurrencyAvg currencyAvg = new CurrencyAvg(100,100,0);

        Assertions.assertEquals(currencyAvg,currencyService.getCurrencyAverage(t1,t2));

    }


}