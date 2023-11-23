package com.github.bloslo.lottery.domain;

import java.util.UUID;

public class LotteryTicket {
    private UUID id;
    private String number;
    private boolean winner;
    private Lottery lottery;
    private Participant participant;

    public LotteryTicket(UUID id, String number, Lottery lottery, Participant participant, boolean winner) {
        this.id = id;
        this.number = number;
        this.lottery = lottery;
        this.participant = participant;
        this.winner = winner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
