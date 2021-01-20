package com.tute.bitcoiner.dto;

public class CurrencyAvg {

    private double average;

    private double averageAll;

    private double difference;

    public CurrencyAvg() {
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getAverageAll() {
        return averageAll;
    }

    public void setAverageAll(double averageAll) {
        this.averageAll = averageAll;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }
}
