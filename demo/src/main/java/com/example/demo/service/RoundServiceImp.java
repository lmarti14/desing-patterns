package com.example.demo.service;

import com.example.demo.repository.RoundRepo;
import com.example.demo.repository.entity.Round;
import com.example.demo.service.interfaces.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoundServiceImp implements RoundService {

    private final RoundRepo repo;

    @Override
    public Round getRound(int id) throws ChangeSetPersister.NotFoundException {
        return repo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Round createRound(Round round) {
        return repo.save(round);
    }

    @Override
    public void updateRound(Round round) {
        repo.save(round);
    }

}
