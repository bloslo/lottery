package com.github.bloslo.lottery.application.controllers;

import com.github.bloslo.lottery.application.request.AddParticipantRequest;
import com.github.bloslo.lottery.application.request.BuyLotteryTicketRequest;
import com.github.bloslo.lottery.application.request.CreateLotteryRequest;
import com.github.bloslo.lottery.application.response.*;
import com.github.bloslo.lottery.domain.Lottery;
import com.github.bloslo.lottery.domain.LotteryException;
import com.github.bloslo.lottery.domain.LotteryTicket;
import com.github.bloslo.lottery.domain.service.LotteryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {

    private static final Logger LOG = LoggerFactory.getLogger(LotteryController.class);

    private final LotteryService lotteryService;

    LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping
    GetRunningLotteriesResponse getRunningLotteries() {
        List<Lottery> lotteries = lotteryService.getRunningLotteries();

        return new GetRunningLotteriesResponse(lotteries);
    }

    @PostMapping
    CreateLotteryResponse createLottery(@Valid @RequestBody CreateLotteryRequest createLotteryRequest) {
        UUID id = lotteryService.createLottery(createLotteryRequest.getStartDate(), createLotteryRequest.getEndDate(),
                createLotteryRequest.getName());

        return new CreateLotteryResponse(id);
    }

    @GetMapping("/winners")
    List<WinningTicket> getWinnersForDate(@RequestParam("date")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<LotteryTicket> tickets = lotteryService.getWinningTicketsForDate(date);
        List<WinningTicket> winningTickets = tickets.stream()
                .map(t -> new WinningTicket(t.getNumber(), t.getLottery().getId(), t.getLottery().getName()))
                .collect(Collectors.toList());

        return winningTickets;
    }

    @PostMapping("/ticket")
    BuyLotteryTicketResponse buyLotteryTicket(@Valid @RequestBody BuyLotteryTicketRequest buyLotteryTicketRequest) {
        LotteryTicket ticket = lotteryService.buyLotteryTicket(buyLotteryTicketRequest.getLotteryId(),
                buyLotteryTicketRequest.getLuckyNumbers(), buyLotteryTicketRequest.getParticipantEmail());

        return new BuyLotteryTicketResponse(ticket.getId(), ticket.getNumber(),
                ticket.getLottery().getId(), ticket.getLottery().getName());
    }

    @PostMapping("/participant")
    AddParticipantResponse addParticipant(@Valid @RequestBody AddParticipantRequest request) {
        UUID participantId = lotteryService.addParticipant(request.getName(), request.getEmail());

        return new AddParticipantResponse(participantId);
    }

    @ExceptionHandler({LotteryException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleLotteryException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, List<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return getErrorsMap(errors);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
