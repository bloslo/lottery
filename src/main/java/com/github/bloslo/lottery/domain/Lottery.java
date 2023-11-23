package com.github.bloslo.lottery.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Lottery {

    private UUID id;
    private LocalDate startDateTime;
    private LocalDate endDateTime;
    private String name;

    public Lottery(UUID id, LocalDate startDateTime, LocalDate endDateTime, String name) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    public String getName() {
        return name;
    }
}
