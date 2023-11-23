package com.github.bloslo.lottery.application.response;

import java.util.UUID;

public class WinningTicket {
    private String number;
    private UUID lotteryId;
    private String lotteryName;

    public WinningTicket(String number, UUID lotteryId, String lotteryName) {
        this.number = number;
        this.lotteryId = lotteryId;
        this.lotteryName = lotteryName;
    }

    public String getNumber() {
        return number;
    }

    public UUID getLotteryId() {
        return lotteryId;
    }

    public String getLotteryName() {
        return lotteryName;
    }
}
