package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.infrastructure.entity.LotteryTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaLotteryTicketRepository extends JpaRepository<LotteryTicketEntity, UUID>, JpaSpecificationExecutor<LotteryTicketEntity> {
}
