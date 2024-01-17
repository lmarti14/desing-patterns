package com.example.demo.desingpatterns.factory;

import com.example.demo.repository.entity.Card;
import com.example.demo.utils.Colors;

public class GreenCardFactory extends CartaFactory {



    @Override
    public String getCard(Card card) {

        return Colors.GREEN + "|" + card.getName() + "|" + Colors.RESET;
    }
}
