package com.tute.bitcoiner.dto;

import com.tute.bitcoiner.common.Timestamp;

public class Currency {

    private double dollarsEquivalent;
    private Timestamp timestamp;

    public Currency(double dollarsEquivalent, Timestamp timestamp) {
        this.dollarsEquivalent = dollarsEquivalent;
        this.timestamp = timestamp;
    }

    public Currency(double dollarsEquivalent) {
        this.dollarsEquivalent = dollarsEquivalent;
        this.timestamp = new Timestamp();
    }

    public double getDollarsEquivalent() {
        return dollarsEquivalent;
    }

    public void setDollarsEquivalent(double dollarsEquivalent) {
        this.dollarsEquivalent = dollarsEquivalent;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
