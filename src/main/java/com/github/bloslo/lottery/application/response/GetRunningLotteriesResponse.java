package com.github.bloslo.lottery.application.response;

import com.github.bloslo.lottery.domain.Lottery;

import java.util.List;

public class GetRunningLotteriesResponse {
    private List<Lottery> lotteries;

    public GetRunningLotteriesResponse(List<Lottery> lotteries) {
        this.lotteries = lotteries;
    }

    public List<Lottery> getLotteries() {
        return lotteries;
    }
}
