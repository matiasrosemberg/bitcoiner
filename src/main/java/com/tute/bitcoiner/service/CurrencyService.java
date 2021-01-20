package com.tute.bitcoiner.service;

import com.tute.bitcoiner.client.CurrencyClient;
import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.common.Utils;
import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.dto.CurrencyAvg;
import com.tute.bitcoiner.exception.CurrencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableScheduling
@Service
public class CurrencyService {

    private CurrencyClient currencyClient;
    private TreeMap<Long, Currency> currencies;
    private Utils utils;
    private Double sum;
    private Long counter;
    private Double average;

    @Autowired
    public CurrencyService(CurrencyClient currencyClient, Utils utils) {
        this.currencyClient = currencyClient;
        this.currencies = new TreeMap<>();
        this.sum = 0D;
        this.counter = 0L;
        this.average = 0D;
        this.utils = utils;
    }

    private Currency getCurrency(Timestamp timestamp) {
        return currencies.floorEntry(timestamp.getEpoch()).getValue();
    }

    public Currency getCurrencyByTimestamp(Timestamp timestamp) {
        return getCurrency(timestamp);
    }

    public Double getAverage() {
        return average;
    }

    public TreeMap<Long, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(TreeMap<Long, Currency> currencies) {
        this.currencies = currencies;
    }

    public CurrencyClient getCurrencyClient() {
        return currencyClient;
    }

    public Utils getUtils() {
        return utils;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public void setCurrencyClient(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Scheduled(fixedRate = 5000)
    public void addNewCurrency() {
        currencyClient
                .getCurrentCurrency()
                .map(utils::fromJson)
                .map(x -> new Currency(Double.parseDouble(x.get("lprice").asText())))
                .doOnNext(v -> {
                    currencies.put(v.getTimestamp().getEpoch(), v);
                    sum = sum + v.getDollarsEquivalent();
                    counter++;
                    average = sum / counter;
                })
                .doOnError(t -> {
                    throw new CurrencyException("Failed to get last timestamp currency");
                })
                .subscribe();
    }

    public CurrencyAvg getCurrencyAverage(Timestamp t1, Timestamp t2) {
        CurrencyAvg currencyAvg = new CurrencyAvg();
        Double c1 = currencies.floorEntry(t1.getEpoch()).getValue().getDollarsEquivalent();
        Double c2 = currencies.floorEntry(t2.getEpoch()).getValue().getDollarsEquivalent();
        DoubleSummaryStatistics stats = Stream.of(c1, c2).collect(Collectors.summarizingDouble(Double::doubleValue));
        currencyAvg.setAverage(stats.getAverage());
        currencyAvg.setAverageAll(getAverage());
        currencyAvg.setDifference(100 - (getAverage() * 100 / stats.getAverage()));
        return currencyAvg;
    }
}
