package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.repository.LotteryRepository;
import com.github.bloslo.lottery.infrastructure.entity.LotteryEntity;
import com.github.bloslo.lottery.infrastructure.specification.LotterySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LotteryRepositoryImpl implements LotteryRepository {

    private JpaLotteryRepository jpaLotteryRepository;

    @Autowired
    public LotteryRepositoryImpl(JpaLotteryRepository jpaLotteryRepository) {
        this.jpaLotteryRepository = jpaLotteryRepository;
    }

    @Override
    public Lottery findById(UUID id) {
        Optional<LotteryEntity> result = jpaLotteryRepository.findById(id);
        if (result.isPresent()) {
            return result.get().toLottery();
        }

        return null;
    }

    @Override
    public List<Lottery> findAllWithEndDateAfter(LocalDate date) {
        return jpaLotteryRepository.findAll(LotterySpecification.byEndDateAfter(date))
                .stream()
                .map(LotteryEntity::toLottery)
                .collect(Collectors.toList());
    }

    @Override
    public List<Lottery> findAllLotteriesWithEndDate(LocalDate date) {
        return jpaLotteryRepository.findAll(LotterySpecification.withEndDate(date))
                .stream()
                .map(LotteryEntity::toLottery)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Lottery lottery) {
        jpaLotteryRepository.save(new LotteryEntity((lottery)));
    }
}
