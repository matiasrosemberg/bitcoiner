package com.tute.bitcoiner.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class CurrencyService {



    @Scheduled(fixedRate = 5000)
    public void addNewCurrency(){

    }

}
