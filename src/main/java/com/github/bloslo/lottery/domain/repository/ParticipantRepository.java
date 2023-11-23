package com.github.bloslo.lottery.domain.repository;

import com.github.bloslo.lottery.domain.Participant;

public interface ParticipantRepository {
    Participant findByEmail(String email);
    void save(Participant participant);
}
