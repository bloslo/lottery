package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.infrastructure.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaParticipantRepository extends JpaRepository<ParticipantEntity, UUID>, JpaSpecificationExecutor<ParticipantEntity> {
}
