package com.github.bloslo.lottery.infrastructure.scheduling;

import com.github.bloslo.lottery.domain.service.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LotteryScheduler {
    private final static Logger LOG = LoggerFactory.getLogger(LotteryScheduler.class);

    private LotteryService lotteryService;

    @Autowired
    public LotteryScheduler(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Amsterdam")
    public void drawWinners() {
        LOG.info("Drawing the winners for all lotteries with end date {}", LocalDate.now().minusDays(1));
        lotteryService.drawWinners();
    }
}
