package com.github.bloslo.lottery.infrastructure.specification;

import com.github.bloslo.lottery.infrastructure.entity.ParticipantEntity;
import com.github.bloslo.lottery.infrastructure.entity.ParticipantEntity_;
import org.springframework.data.jpa.domain.Specification;

public class ParticipantSpecification {
    public static Specification<ParticipantEntity> byEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ParticipantEntity_.email), email);
    }
}
