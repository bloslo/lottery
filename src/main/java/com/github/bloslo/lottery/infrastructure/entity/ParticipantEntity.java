package com.github.bloslo.lottery.infrastructure.entity;

import com.github.bloslo.lottery.domain.Participant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "participant")
public class ParticipantEntity {
    @Id
    private UUID id;
    private String email;
    private String name;
    @OneToMany(mappedBy = "participant")
    private Set<LotteryTicketEntity> lotteryTickets;

    public ParticipantEntity() {
    }

    public ParticipantEntity(UUID id, String email, String name, Set<LotteryTicketEntity> lotteryTickets) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lotteryTickets = lotteryTickets;
    }

    public ParticipantEntity(Participant participant) {
        this.id = participant.getId();
        this.email = participant.getEmail();
        this.name = participant.getName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LotteryTicketEntity> getLotteryTickets() {
        return lotteryTickets;
    }

    public void setLotteryTickets(Set<LotteryTicketEntity> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public Participant toParticipant() {
        return new Participant(this.id, this.email, this.name);
    }
}
