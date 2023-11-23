package com.github.bloslo.lottery.infrastructure.repository;

import com.github.bloslo.lottery.domain.Participant;
import com.github.bloslo.lottery.domain.repository.ParticipantRepository;
import com.github.bloslo.lottery.infrastructure.entity.ParticipantEntity;
import com.github.bloslo.lottery.infrastructure.specification.ParticipantSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private JpaParticipantRepository participantRepository;

    @Autowired
    public ParticipantRepositoryImpl(JpaParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Participant findByEmail(String email) {
        List<ParticipantEntity> result = this.participantRepository.findAll(ParticipantSpecification.byEmail(email));
        if (!result.isEmpty()) {
            return result.get(0).toParticipant();
        }

        return null;
    }

    @Override
    public void save(Participant participant) {
        this.participantRepository.save(new ParticipantEntity(participant));
    }
}
