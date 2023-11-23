package com.github.bloslo.lottery.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class BuyLotteryTicketRequest {
    private UUID lotteryId;
    @Size(min = 2, max = 2, message = "You have to choose two lucky numbers.")
    private List<Integer> luckyNumbers;
    @NotBlank(message = "Participant's email cannot be blank.")
    private String participantEmail;

    public BuyLotteryTicketRequest(UUID lotteryId, List<Integer> luckyNumbers, String participantEmail) {
        this.lotteryId = lotteryId;
        this.luckyNumbers = luckyNumbers;
        this.participantEmail = participantEmail;
    }

    public UUID getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(UUID lotteryId) {
        this.lotteryId = lotteryId;
    }

    public List<Integer> getLuckyNumbers() {
        return luckyNumbers;
    }

    public void setLuckyNumbers(List<Integer> luckyNumbers) {
        this.luckyNumbers = luckyNumbers;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }
}
