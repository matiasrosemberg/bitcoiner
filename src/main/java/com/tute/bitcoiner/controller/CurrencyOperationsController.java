package com.tute.bitcoiner.controller;

import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.dto.Currency;
import com.tute.bitcoiner.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class CurrencyOperationsController {

    CurrencyService currencyService;

    @Autowired
    public CurrencyOperationsController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public Currency getCurrency(@RequestParam(value = "timestamp") Instant instant) {
        return currencyService.getCurrencyByTimestamp(new Timestamp(instant));
    }
}