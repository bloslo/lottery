package com.github.bloslo.lottery.infrastructure.configuration;

import com.github.bloslo.lottery.LotteryApplication;
import com.github.bloslo.lottery.domain.repository.LotteryRepository;
import com.github.bloslo.lottery.domain.repository.LotteryTicketRepository;
import com.github.bloslo.lottery.domain.repository.ParticipantRepository;
import com.github.bloslo.lottery.domain.service.LotteryService;
import com.github.bloslo.lottery.domain.service.LotteryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = LotteryApplication.class)
public class BeanConfiguration {

    @Bean
    LotteryService lotteryService(LotteryRepository lotteryRepository, LotteryTicketRepository lotteryTicketRepository,
                                  ParticipantRepository participantRepository) {
        return new LotteryServiceImpl(lotteryRepository, lotteryTicketRepository, participantRepository);
    }
}
