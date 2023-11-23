package com.github.bloslo.lottery.infrastructure.entity;

import com.github.bloslo.lottery.domain.LotteryTicket;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "lottery_tickets")
public class LotteryTicketEntity {
    @Id
    private UUID id;
    private String number;
    private boolean winner;
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private ParticipantEntity participant;

    @ManyToOne
    @JoinColumn(name = "lottery_id", nullable = false)
    private LotteryEntity lottery;

    public LotteryTicketEntity() {
    }

    public LotteryTicketEntity(UUID id, String number, LotteryEntity lottery, ParticipantEntity participant) {
        this.id = id;
        this.number = number;
        this.lottery = lottery;
        this.participant = participant;
        this.winner = false;
    }

    public LotteryTicketEntity(LotteryTicket lotteryTicket) {
        this.id = lotteryTicket.getId();
        this.number = lotteryTicket.getNumber();
        this.winner = lotteryTicket.isWinner();
        this.lottery = new LotteryEntity(lotteryTicket.getLottery());
        this.participant = new ParticipantEntity(lotteryTicket.getParticipant());
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

    public LotteryTicket toLotteryTicket() {
        return new LotteryTicket(this.id, this.number, this.lottery.toLottery(), this.participant.toParticipant(), this.winner);
    }
}
