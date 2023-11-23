package com.github.bloslo.lottery.domain.service;

import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.LotteryException;
import com.github.bloslo.lottery.domain.LotteryTicket;
import com.github.bloslo.lottery.domain.Participant;
import com.github.bloslo.lottery.domain.repository.LotteryRepository;
import com.github.bloslo.lottery.domain.repository.LotteryTicketRepository;
import com.github.bloslo.lottery.domain.repository.ParticipantRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LotteryServiceImpl implements LotteryService {
    private LotteryRepository lotteryRepository;
    private LotteryTicketRepository lotteryTicketRepository;
    private ParticipantRepository participantRepository;

    public LotteryServiceImpl(LotteryRepository lotteryRepository, LotteryTicketRepository lotteryTicketRepository,
                              ParticipantRepository participantRepository) {
        this.lotteryRepository = lotteryRepository;
        this.lotteryTicketRepository = lotteryTicketRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public UUID addParticipant(String name, String email) {
        UUID participantId = UUID.randomUUID();
        participantRepository.save(new Participant(participantId, email, name));
        return participantId;
    }

    @Override
    public UUID createLottery(LocalDate startDate, LocalDate endDate, String name) {
        if (endDate.isBefore(startDate)) {
            throw new LotteryException("The Lottery end date cannot be before the start date.");
        }

        UUID lotteryId = UUID.randomUUID();
        Lottery lottery = new Lottery(lotteryId, startDate, endDate, name);
        lotteryRepository.save(lottery);

        return lotteryId;
    }

    @Override
    public List<Lottery> getRunningLotteries() {
        return lotteryRepository.findAllWithEndDateAfter(LocalDate.now().minusDays(1));
    }

    @Override
    public List<LotteryTicket> getWinningTicketsForDate(LocalDate date) {
        return lotteryTicketRepository.findAllWinningTicketsForDate(date);
    }

    @Override
    public Participant registerParticipant(Participant participant) {
        return null;
    }

    @Override
    public LotteryTicket buyLotteryTicket(UUID lotteryId, List<Integer> luckyNumbers, String email) {
        Participant participant = participantRepository.findByEmail(email);
        if (participant == null) {
            throw new LotteryException("No participant found with the given email");
        }

        Lottery lottery = lotteryRepository.findById(lotteryId);
        if (lottery == null) {
            throw new LotteryException("Lottery not found.");
        }

        boolean numbersInRange = luckyNumbers.stream().allMatch(n -> n >= 1 && n <= 45);
        if (!numbersInRange) {
            throw new LotteryException("The lucky numbers must be between 1 and 45");
        }

        String ticketNumber;
        LotteryTicket ticket;
        do {
            ticketNumber = this.generateTicketNumber(luckyNumbers);
            ticket = lotteryTicketRepository.findTicketByNumberAndLottery(lottery, ticketNumber);
        } while (ticket != null);

        UUID ticketId = UUID.randomUUID();
        LotteryTicket lotteryTicket = new LotteryTicket(ticketId, ticketNumber, lottery,  participant, false);
        lotteryTicketRepository.save(lotteryTicket);
        return lotteryTicket;
    }

    @Override
    public void drawWinners() {
        List<Lottery> lotteries = this.lotteryRepository.findAllLotteriesWithEndDate(LocalDate.now().minusDays(1));
        for (Lottery lottery : lotteries) {
            long ticketsCount = lotteryTicketRepository.countByLottery(lottery);
            SecureRandom random = new SecureRandom();
            int idx = (int) (random.nextInt() * ticketsCount);
            LotteryTicket ticket = lotteryTicketRepository.findTicketForLottery(lottery, idx);

            if (ticket != null) {
                // Mark it as a winner.
                ticket.setWinner(true);
                this.lotteryTicketRepository.save(ticket);
            }
        }
    }

    @Override
    public String generateTicketNumber(List<Integer> luckyNumbers) {
        SecureRandom random = new SecureRandom();
        List<Integer> numbers = random.ints(4, 1, 46)
                .boxed()
                .collect(Collectors.toList());
        numbers.addAll(luckyNumbers);
        String ticketNumber = numbers.stream().map(String::valueOf).collect(Collectors.joining("-"));

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder fullTicketNumber = new StringBuilder();
        fullTicketNumber.append(ticketNumber);
        for (int i = 0; i < 3; i++) {
            fullTicketNumber.insert(0, alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return fullTicketNumber.toString();
    }
}
