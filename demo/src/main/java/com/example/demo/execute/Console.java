package com.example.demo.execute;

import com.example.demo.repository.entity.Card;
import com.example.demo.repository.entity.Game;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.Round;
import com.example.demo.service.interfaces.CardService;
import com.example.demo.service.interfaces.GameService;
import com.example.demo.service.interfaces.PlayerService;
import com.example.demo.service.interfaces.RoundService;
import com.example.demo.utils.ConsValues;
import com.example.demo.utils.Deck;
import com.example.demo.utils.InputUtils;
import com.example.demo.utils.OutputUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class Console {

    private final OutputUtils outputUtils;

    private final InputUtils inputUtils;

    private final RoundService roundService;

    private final GameService gameService;

    private final PlayerService playerService;

    private final CardService cardService;


    public void execute()  {

        Round round = createRound();

        List<Player> players = createPlayers();

        Game game = createAndSaveGame(round, players);

        outputUtils.showCards(game, players.get(0));
        outputUtils.showCards(game, players.get(1));

        while (round.getActive()) {

            if (!game.getActive()) {
                handleEndOfGame(round, game, players);
            }

            gameService.play(round, game, players.get(0));
            gameService.play(round, game, players.get(1));

        }

        outputUtils.gameOver(round, players.get(0), players.get(1));
    }

    private Round createRound() {
        Round round = new Round();
        round.setActive(true);
        return roundService.createRound(round);
    }

    private List<Player> createPlayers() {
        Player human = createPlayer(inputUtils.getName(), false);
        Player machine = createPlayer(ConsValues.MACHINE_NAME, true);
        List<Player> players = new ArrayList<>();
        players.add(human);
        players.add(machine);
        return players;
    }

    private Player createPlayer(String name, boolean isMachine) {
        Player player = new Player();
        player.setName(name);
        player.setMachine(isMachine);
        Card card = cardService.getCard(Deck.getOne());
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        player.setCards(cards);
        return playerService.createPlayer(player);
    }

    private Game createAndSaveGame(Round round, List<Player> players) {
        Game game = new Game();
        game.setPlayers(players);
        game.setActive(true);
        game.setRound(round);
        return gameService.saveGame(game);
    }

    private void handleEndOfGame(Round round, Game game, List<Player> players) {
        outputUtils.showCards(game, players.get(1));
        outputUtils.newGame();

        List<Game> games = new ArrayList<>();
        games.add(game);
        round.setGames(games);
        roundService.updateRound(round);

        Game newGame = createAndSaveGame(round, players);
    }
}
