package com.example.demo.service;

import com.example.demo.repository.PlayerRepo;
import com.example.demo.repository.entity.Card;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.interfaces.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerServiceImp implements PlayerService {

    @Autowired
    private final PlayerRepo repo;

    @Override
    public Player getPlayer(int id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Player createPlayer(Player player) {
        return repo.save(player);
    }

    @Override
    public void updatePlayer(Player player) {
        repo.save(player);
    }

    @Override
    public int points(Player player) {
        long p = player.getCards().stream()
            .mapToInt(Card::getPoints).sum();
        return (int) p;
    }
}
