package com.example.demo.service.interfaces;

import com.example.demo.repository.entity.Game;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.Round;

public interface GameService {

    Game getGame(int id);
    Game saveGame(Game game);

    void play(Round round, Game game, Player player);
}
