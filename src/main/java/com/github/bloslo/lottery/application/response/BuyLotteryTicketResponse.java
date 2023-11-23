package com.github.bloslo.lottery.application.response;

import java.util.UUID;

public class BuyLotteryTicketResponse {
    private UUID ticketId;
    private String number;
    private UUID lotteryId;
    private String lotteryName;

    public BuyLotteryTicketResponse(UUID ticketId, String number, UUID lotteryId, String lotteryName) {
        this.ticketId = ticketId;
        this.number = number;
        this.lotteryId = lotteryId;
        this.lotteryName = lotteryName;
    }

    public UUID getTicketId() {
        return ticketId;
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
