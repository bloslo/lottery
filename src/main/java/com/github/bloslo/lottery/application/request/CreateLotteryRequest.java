package com.github.bloslo.lottery.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateLotteryRequest {
    @NotNull(message = "Start date must be specified.")
    private LocalDate startDate;
    @NotNull(message = "End date must be specified.")
    private LocalDate endDate;
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 5, max = 48, message = "The lottery name must be between 5 and 48 characters long.")
    private String name;

    public CreateLotteryRequest(LocalDate startDate, LocalDate endDate, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
