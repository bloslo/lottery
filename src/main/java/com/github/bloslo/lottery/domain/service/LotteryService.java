package com.github.bloslo.lottery.domain.service;

import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.LotteryTicket;
import com.github.bloslo.lottery.domain.Participant;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LotteryService {
    UUID addParticipant(String name, String email);
    UUID createLottery(LocalDate startDate, LocalDate endDate, String name);
    List<Lottery> getRunningLotteries();
    List<LotteryTicket> getWinningTicketsForDate(LocalDate date);
    Participant registerParticipant(Participant participant);
    LotteryTicket buyLotteryTicket(UUID lotteryId, List<Integer> luckyNumbers, String participantEmail);
    void drawWinners();
    String generateTicketNumber(List<Integer> luckyNumbers);
}
