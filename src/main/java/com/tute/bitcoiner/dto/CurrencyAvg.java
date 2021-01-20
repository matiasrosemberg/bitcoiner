package com.tute.bitcoiner.dto;

import java.util.Objects;

public class CurrencyAvg {

    private double average;

    private double averageAll;

    private double difference;

    public CurrencyAvg() {
    }

    public CurrencyAvg(double average, double averageAll, double difference) {
        this.average = average;
        this.averageAll = averageAll;
        this.difference = difference;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyAvg that = (CurrencyAvg) o;
        return Double.compare(that.average, average) == 0 && Double.compare(that.averageAll, averageAll) == 0 && Double.compare(that.difference, difference) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(average, averageAll, difference);
    }

    @Override
    public String toString() {
        return "CurrencyAvg{" +
                "average=" + average +
                ", averageAll=" + averageAll +
                ", difference=" + difference +
                '}';
    }
}
