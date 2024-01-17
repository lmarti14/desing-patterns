package com.example.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
    private final static List<String> cartasPoker = Arrays.asList(
        "❤️ 2", "❤️ 3", "❤️ 4", "❤️ 5", "❤️ 6", "❤️ 7", "❤️ 8", "❤️ 9", "❤️ 10", "❤️ J", "❤️ Q", "❤️ K", "❤️ A",
        "♦️ 2", "♦️ 3", "♦️ 4", "♦️ 5", "♦️ 6", "♦️ 7", "♦️ 8", "♦️ 9", "♦️ 10", "♦️ J", "♦️ Q", "♦️ K", "♦️ A",
        "♠️ 2", "♠️ 3", "♠️ 4", "♠️ 5", "♠️ 6", "♠️ 7", "♠️ 8", "♠️ 9", "♠️ 10", "♠️ J", "♠️ Q", "♠️ K", "♠️ A",
        "♣️ 2", "♣️ 3", "♣️ 4", "♣️ 5", "♣️ 6", "♣️ 7", "♣️ 8", "♣️ 9", "♣️ 10", "♣️ J", "♣️ Q", "♣️ K", " ♣️A"
    );

    public static int getOne(){
        Random ramInt = new Random();
        int num = ramInt.nextInt(0, cartasPoker.size() - 1);
        System.out.println("Deck # " + num );
        return num;
    }
}
