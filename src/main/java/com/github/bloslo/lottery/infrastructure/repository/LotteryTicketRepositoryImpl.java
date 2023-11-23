package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.LotteryTicket;
import com.github.bloslo.lottery.domain.repository.LotteryTicketRepository;
import com.github.bloslo.lottery.infrastructure.entity.LotteryTicketEntity;
import com.github.bloslo.lottery.infrastructure.specification.LotteryTicketSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LotteryTicketRepositoryImpl implements LotteryTicketRepository {

    private JpaLotteryTicketRepository jpaLotteryTicketRepository;

    @Autowired
    public LotteryTicketRepositoryImpl(JpaLotteryTicketRepository jpaLotteryTicketRepository) {
        this.jpaLotteryTicketRepository = jpaLotteryTicketRepository;
    }

    @Override
    public long countByLottery(Lottery lottery) {
        return jpaLotteryTicketRepository.count(LotteryTicketSpecification.byLotteryId(lottery.getId()));
    }

    @Override
    public LotteryTicket findTicketForLottery(Lottery lottery, int pageNumber) {
        Page<LotteryTicketEntity> ticketEntityPage = jpaLotteryTicketRepository.findAll(
                LotteryTicketSpecification.byLotteryId(lottery.getId()), PageRequest.of(pageNumber, 1));
        LotteryTicket lotteryTicket = null;
        if (ticketEntityPage.hasContent()) {
            LotteryTicketEntity ticketEntity = ticketEntityPage.getContent().get(0);
            lotteryTicket = ticketEntity.toLotteryTicket();
        }
        return lotteryTicket;
    }

    @Override
    public List<LotteryTicket> findAllWinningTicketsForDate(LocalDate date) {
        return jpaLotteryTicketRepository.findAll(
                LotteryTicketSpecification.isWinningForDate(date))
                .stream()
                .map(LotteryTicketEntity::toLotteryTicket)
                .collect(Collectors.toList());
    }

    @Override
    public LotteryTicket findTicketByNumberAndLottery(Lottery lottery, String number) {
        List<LotteryTicketEntity> ticketEntities = jpaLotteryTicketRepository.findAll(
                LotteryTicketSpecification.byLotteryIdAndNumber(lottery.getId(), number));
        if (!ticketEntities.isEmpty()) {
            return ticketEntities.get(0).toLotteryTicket();
        }

        return null;
    }

    @Override
    public void save(LotteryTicket lotteryTicket) {
        jpaLotteryTicketRepository.save(new LotteryTicketEntity(lotteryTicket));
    }
}
