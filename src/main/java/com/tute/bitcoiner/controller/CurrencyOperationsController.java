package com.tute.bitcoiner.controller;

import com.tute.bitcoiner.common.Timestamp;
import com.tute.bitcoiner.dto.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class CurrencyOperationsController {

    Timestamp timestamp = new Timestamp();

    @GetMapping("/currency")
    public Currency getCurrency(@RequestParam(value = "timestamp") Instant instant) {
        return null;
    }
}