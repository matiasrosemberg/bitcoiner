package com.tute.bitcoiner.dto;

import java.time.Instant;

public class Currency {

    private double dollarsEquivalent;
    private Instant instant;

    public Currency(double dollarsEquivalent, Instant instant) {
        this.dollarsEquivalent = dollarsEquivalent;
        this.instant = instant;
    }

    public Currency(double dollarsEquivalent) {
        this.dollarsEquivalent = dollarsEquivalent;
        this.instant = Instant.now();
    }

    public double getDollarsEquivalent() {
        return dollarsEquivalent;
    }

    public void setDollarsEquivalent(double dollarsEquivalent) {
        this.dollarsEquivalent = dollarsEquivalent;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
