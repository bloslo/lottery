package com.github.bloslo.lottery.application.response;

import java.util.UUID;

public class AddParticipantResponse {
    private UUID participantId;

    public AddParticipantResponse(UUID participantId) {
        this.participantId = participantId;
    }

    public UUID getParticipantId() {
        return participantId;
    }
}
