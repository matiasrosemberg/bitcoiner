package com.tute.bitcoiner.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Timestamp {

    private final Instant instant;

    public Timestamp() {
        this.instant = Instant.now();
    }

    public Timestamp(Instant instant) {
        this.instant = instant;
    }

    public Timestamp(LocalDateTime localDateTime) {
        this.instant = localDateTime.atZone(ZoneId.of("AGT")).toInstant();
    }

    public Long getEpoch() {
        return instant.toEpochMilli();
    }
}
