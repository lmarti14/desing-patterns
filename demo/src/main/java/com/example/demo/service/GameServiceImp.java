package com.example.demo.service;

import com.example.demo.repository.GameRepo;
import com.example.demo.repository.entity.Card;
import com.example.demo.repository.entity.Game;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.Round;
import com.example.demo.service.interfaces.CardService;
import com.example.demo.service.interfaces.GameService;
import com.example.demo.service.interfaces.PlayerService;
import com.example.demo.utils.ConsValues;
import com.example.demo.utils.Deck;
import com.example.demo.utils.InputUtils;
import com.example.demo.utils.OutputUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService {

    @Autowired
    private final GameRepo repo;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final CardService cardService;

    @Autowired
    private final OutputUtils outputUtils;

    @Autowired
    private final InputUtils inputUtils;

    @Override
    public Game getGame(int id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Game saveGame(Game game) {
        return repo.save(game);
    }

    @Override
    public void play(Round round, Game game, Player player) {

        if (!player.getMachine()){
            if (endGame()) {
                verifyPoints(game);
                game.setActive(false);
            }
        } else outputUtils.waitMachine();

        if(game.getActive()){
            Card card = cardService.getCard(Deck.getOne());
            List<Card> cards = player.getCards();
            cards.add(card);
            playerService.updatePlayer(player);

            outputUtils.showCards(game, player);
        }

        if (verifyPoints(player)) {
            defaultWinner(player);
            game.setActive(false);

        }
    }

    private boolean verifyPoints(Player player) {
        return playerService.points(player) > ConsValues.SCORE;
    }

    private void verifyPoints(Game game){

        Player human = game.getPlayers().stream()
            .filter(player -> !player.getMachine())
            .findFirst()
            .orElseThrow();

        Player machine = game.getPlayers().stream()
            .filter(Player::getMachine)
            .findFirst()
            .orElseThrow();

        if (playerService.points(human) > playerService.points(machine)){
            outputUtils.gameWon();
        } else {
            outputUtils.lostGame();
        }
    }

    private void defaultWinner(Player player){
        if (player.getMachine()) {
            outputUtils.gameWon();
        } else {
            outputUtils.lostGame();
        }
    }

    private boolean endGame(){
        return !inputUtils.getAnotherCar().equals("y");
    }

    private Player oppositePlayer(Game game, Player player) {
        List<Player> players = game.getPlayers();
        return players.stream()
            .filter(player1 -> !player1.equals(player))
            .findFirst()
            .orElseThrow();
    }
}
