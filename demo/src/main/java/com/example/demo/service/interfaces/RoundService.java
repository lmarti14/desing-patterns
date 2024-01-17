package com.example.demo.service.interfaces;

import com.example.demo.repository.entity.Round;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface RoundService {

    Round getRound(int id) throws ChangeSetPersister.NotFoundException;

    Round createRound(Round round);

    void updateRound(Round round);
}
