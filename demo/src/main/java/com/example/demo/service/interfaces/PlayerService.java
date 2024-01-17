package com.example.demo.service.interfaces;


import com.example.demo.repository.entity.Player;
import org.springframework.stereotype.Service;



@Service
public interface PlayerService {

    Player getPlayer(int id);

    Player createPlayer(Player player);

    void updatePlayer(Player player);

    int points(Player player);

}
