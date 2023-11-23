package com.github.bloslo.lottery.domain;

import java.util.UUID;

public class Participant {
    private UUID id;
    private String email;
    private String name;

    public Participant(UUID id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
