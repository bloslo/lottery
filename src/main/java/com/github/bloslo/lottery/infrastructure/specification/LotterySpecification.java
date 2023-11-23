package com.github.bloslo.lottery.infrastructure.specification;

import com.github.bloslo.lottery.infrastructure.entity.LotteryEntity;
import com.github.bloslo.lottery.infrastructure.entity.LotteryEntity_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class LotterySpecification {
    public static Specification<LotteryEntity> byEndDateAfter(LocalDate date) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(LotteryEntity_.endDateTime), date));
    }

    public static Specification<LotteryEntity> withEndDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(LotteryEntity_.endDateTime), date);
    }
}
