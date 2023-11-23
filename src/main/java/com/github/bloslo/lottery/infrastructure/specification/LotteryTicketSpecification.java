package com.github.bloslo.lottery.infrastructure.specification;

import com.github.bloslo.lottery.infrastructure.entity.LotteryTicketEntity;
import com.github.bloslo.lottery.infrastructure.entity.LotteryTicketEntity_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class LotteryTicketSpecification {

    public static Specification<LotteryTicketEntity> byLotteryId(UUID lotteryId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lottery").get("id"), lotteryId));
    }

    public static Specification<LotteryTicketEntity> isWinningForDate(LocalDate date) {
        return (((root, query, criteriaBuilder) ->
            criteriaBuilder.and(criteriaBuilder.equal(root.get("lottery").get("endDateTime"), date),
                    criteriaBuilder.isTrue(root.get(LotteryTicketEntity_.winner)))
        ));
    }

    public static Specification<LotteryTicketEntity> byLotteryIdAndNumber(UUID lotteryId, String number) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .and(criteriaBuilder.equal(root.get("lottery").get("id"), lotteryId),
                        criteriaBuilder.equal(root.get(LotteryTicketEntity_.number), number));
    }
}
