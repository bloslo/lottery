package com.github.bloslo.lottery.domain.repository;

import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.LotteryTicket;

import java.time.LocalDate;
import java.util.List;

public interface LotteryTicketRepository {
    long countByLottery(Lottery lottery);
    LotteryTicket findTicketForLottery(Lottery lottery, int pageNumber);
    List<LotteryTicket> findAllWinningTicketsForDate(LocalDate date);
    LotteryTicket findTicketByNumberAndLottery(Lottery lottery, String number);
    void save(LotteryTicket lotteryTicket);
}
