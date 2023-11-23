package com.github.bloslo.lottery.application.request;

import jakarta.validation.constraints.NotBlank;

public class AddParticipantRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    private String email;

    public AddParticipantRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
