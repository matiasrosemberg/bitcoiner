package com.tute.bitcoiner.controllers;

import com.tute.bitcoiner.common.Timestamp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class Peron {

    Timestamp timestamp = new Timestamp();

    @GetMapping("/currency")
    public Coin getCurrency(@RequestParam(value = "timestamp",defaultValue = "") Instant instant){
        return null;
    }

}
