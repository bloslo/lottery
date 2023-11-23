package com.github.bloslo.lottery.infrastructure.entity;

import com.github.bloslo.lottery.domain.Lottery;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lottery")
public class LotteryEntity {

    @Id
    private UUID id;
    private LocalDate startDateTime;
    private LocalDate endDateTime;
    private String name;
    @OneToMany(mappedBy = "lottery")
    private Set<LotteryTicketEntity> tickets;

    public LotteryEntity(UUID id, LocalDate startDateTime, LocalDate endDateTime, String name) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
    }

    public LotteryEntity() {
    }

    public LotteryEntity(Lottery lottery) {
        this.id = lottery.getId();
        this.startDateTime = lottery.getStartDateTime();
        this.endDateTime = lottery.getEndDateTime();
        this.name = lottery.getName();
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

    public Set<LotteryTicketEntity> getTickets() {
        return tickets;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDate endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTickets(Set<LotteryTicketEntity> tickets) {
        this.tickets = tickets;
    }

    public Lottery toLottery() {
        return new Lottery(this.id, this.startDateTime, this.endDateTime, this.name);
    }
}
