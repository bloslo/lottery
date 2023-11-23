package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.infrastructure.entity.LotteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaLotteryRepository extends JpaRepository<LotteryEntity, UUID>, JpaSpecificationExecutor<LotteryEntity> {
}
