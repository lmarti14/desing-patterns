package org.example.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mazo {

    private final static List<String> cartasPoker = Arrays.asList(
        "❤️ 2", "❤️ 3", "❤️ 4", "❤️ 5", "❤️ 6", "❤️ 7", "❤️ 8", "❤️ 9", "❤️ 10", "❤️ J", "❤️ Q", "❤️ K", "❤️ A",
        "♦️ 2", "♦️ 3", "♦️ 4", "♦️ 5", "♦️ 6", "♦️ 7", "♦️ 8", "♦️ 9", "♦️ 10", "♦️ J", "♦️ Q", "♦️ K", "♦️ A",
        "♠️ 2", "♠️ 3", "♠️ 4", "♠️ 5", "♠️ 6", "♠️ 7", "♠️ 8", "♠️ 9", "♠️ 10", "♠️ J", "♠️ Q", "♠️ K", "♠️ A",
        "♣️ 2", "♣️ 3", "♣️ 4", "♣️ 5", "♣️ 6", "♣️ 7", "♣️ 8", "♣️ 9", "♣️ 10", "♣️ J", "♣️ Q", "♣️ K", " ♣️A"
    );

    public static String getOne(){
        Random ramInt = new Random();
        int ramValue = ramInt.nextInt(0, cartasPoker.size() - 1);
        return cartasPoker.get(ramValue);
    }


}
