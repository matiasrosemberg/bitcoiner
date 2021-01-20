package com.tute.bitcoiner.controller;

import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.dto.CurrencyAvg;
import com.tute.bitcoiner.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController()
public class CurrencyOperationsController {

    CurrencyService currencyService;

    @Autowired
    public CurrencyOperationsController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public Currency getCurrency(@RequestParam(value = "timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") final LocalDateTime dateTime) {
        return currencyService.getCurrencyByTimestamp(new Timestamp(dateTime.toInstant(ZoneOffset.UTC)));
    }

    @GetMapping("/average")
    public CurrencyAvg getCurrencyAverage(@RequestParam(value = "timestamp1") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") final LocalDateTime dateTime1, @RequestParam(value = "timestamp2") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") final LocalDateTime dateTime2) {
        return currencyService.getCurrencyAverage(new Timestamp(dateTime1.toInstant(ZoneOffset.UTC)), new Timestamp(dateTime2.toInstant(ZoneOffset.UTC)));
    }
}