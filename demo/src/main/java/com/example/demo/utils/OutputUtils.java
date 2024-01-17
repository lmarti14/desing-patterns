package com.example.demo.utils;

import com.example.demo.repository.entity.Card;
import com.example.demo.repository.entity.Game;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.Round;
import com.example.demo.service.interfaces.CardService;
import com.example.demo.service.interfaces.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class OutputUtils {

    @Autowired
    private final Scanner scanner;

    @Autowired
    private final CardService cardService;

    @Autowired
    private final PlayerService playerService;

    public static void start() {
        System.out.println("\n\n------------- Welcome to the BLACK JACK GAME -------------\n\n");
        System.out.println("Instructions:");
        System.out.println("(1) The Game consists of five (5) games, whoever wins the highest # of games wins.");
        System.out.println("(2) To win a round the sum of your cards must be <= 21 and greater than the sum of your opponent's cards.");
    }

    public void showCards(Game game, Player player){

        List<Card> cards = player.getCards();

        if(!player.getMachine()){
            System.out.println("Player Cards' " + player.getName() + ":");
            for (Card c: cards) {
                System.out.print(cardService.getFormatCard(c.getId()) + " ");
            }
            System.out.print("   Total Points: " + playerService.points(player) + "\n\n");
        }

        if (player.getMachine() && game.getActive()){
            System.out.println("Player Cards' " + player.getName() + ":");
            for (Card c: cards) {
                String cartaForm = cardService.getFormatCard(c.getId());
                String cardMod = cartaForm.replace(c.getName(), " ?? ");
                System.out.print(cardMod + " ");
            }
            System.out.print("   Total Points: ?? \n\n");
        }

        if(!game.getActive()){
            System.out.println("\nPlayer Cards' " + player.getName() + ":");
            for (Card c: cards) {
                System.out.print(cardService.getFormatCard(c.getId()) + " ");
            }
            System.out.print("   Total Points: " + playerService.points(player));
        }

    }

    public void newGame(){
        System.out.println("\n\n-----  New Game -----");
    }
    public void gameWon(){
        String message = """
                \nBrilliant!!! You won this Game.
            """;
        System.out.println(message);
    }

    public void lostGame(){
        String message = """
                \nSorry you lost the round :(
            """;
        System.out.println(message);
    }

    public void tiedGame(){
        String message = """
                \nTie game, the point goes to the machine
            """;
        System.out.println(message);
    }

    public void waitMachine(){
        try {
            System.out.println(ConsValues.MACHINE_NAME + "'s turn ...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting");
        }
    }

    public void gameOver(Round round, Player player, Player machine){


        String gameOver1 = """
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⠀⠀⠀⢀⣴⣿⡶⠀⣾⣿⣿⡿⠟⠛⠁
            ⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⠀⠀⠀⠀⣶⣶⣦⠀⠀⠀⠀⣼⣿⣿⡇⠀⣠⣿⣿⣿⠇⣸⣿⣿⣧⣤⠀⠀⠀
            ⠀⠀⢀⣴⣾⣿⡿⠿⠿⠿⠇⠀⠀⣸⣿⣿⣿⡆⠀⠀⢰⣿⣿⣿⣷⣼⣿⣿⣿⡿⢀⣿⣿⡿⠟⠛⠁⠀⠀
            ⠀⣴⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⢠⣿⣿⣹⣿⣿⣿⣿⣿⣿⡏⢻⣿⣿⢿⣿⣿⠃⣼⣿⣯⣤⣴⣶⣿⡤⠀
            ⣼⣿⠏⠀⣀⣠⣤⣶⣾⣷⠄⣰⣿⣿⡿⠿⠻⣿⣯⣸⣿⡿⠀⠀⠀⠁⣾⣿⡏⢠⣿⣿⠿⠛⠋⠉⠀⠀⠀
            ⣿⣿⠲⢿⣿⣿⣿⣿⡿⠋⢰⣿⣿⠋⠀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⠙⠛⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀
            ⠹⢿⣷⣶⣿⣿⠿⠋⠀⠀⠈⠙⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣶⣦⣤⡀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⡇⢰⣶⣶⣾⡿⠷⣿⣿⣿⡟⠛⣉⣿⣿⣿⠆
            ⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⡎⣿⣿⣦⠀⠀⠀⢀⣤⣾⠟⢀⣿⣿⡟⣁⠀⠀⣸⣿⣿⣤⣾⣿⡿⠛⠁⠀
            ⠀⠀⠀⠀⣠⣾⣿⡿⠛⠉⢿⣦⠘⣿⣿⡆⠀⢠⣾⣿⠋⠀⣼⣿⣿⣿⠿⠷⢠⣿⣿⣿⠿⢻⣿⣧⠀⠀⠀
            ⠀⠀⠀⣴⣿⣿⠋⠀⠀⠀⢸⣿⣇⢹⣿⣷⣰⣿⣿⠃⠀⢠⣿⣿⢃⣀⣤⣤⣾⣿⡟⠀⠀⠀⢻⣿⣆⠀⠀
            ⠀⠀⠀⣿⣿⡇⠀⠀⢀⣴⣿⣿⡟⠀⣿⣿⣿⣿⠃⠀⠀⣾⣿⣿⡿⠿⠛⢛⣿⡟⠀⠀⠀⠀⠀⠻⠿⠀⠀
            ⠀⠀⠀⠹⣿⣿⣶⣾⣿⣿⣿⠟⠁⠀⠸⢿⣿⠇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠈⠙⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            """;

        System.out.println(gameOver1);
        System.out.println("\n\n----------------------------------------------------------------------------\n");
        System.out.println("Total games won by " + player + ": " );
        System.out.println("Total games won by " + machine + ": ");
        System.out.println("\n\n----------------------------------------------------------------------------\n");

    }
}
