package com.github.bloslo.lottery.domain.repository;

import com.github.bloslo.lottery.domain.Lottery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LotteryRepository {
    Lottery findById(UUID id);
    List<Lottery> findAllWithEndDateAfter(LocalDate date);
    List<Lottery> findAllLotteriesWithEndDate(LocalDate date);
    void save(Lottery lottery);
}
