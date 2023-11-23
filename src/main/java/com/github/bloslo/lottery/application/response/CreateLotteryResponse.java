package com.github.bloslo.lottery.application.response;

import java.util.UUID;

public class CreateLotteryResponse {
    private UUID id;

    public CreateLotteryResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
