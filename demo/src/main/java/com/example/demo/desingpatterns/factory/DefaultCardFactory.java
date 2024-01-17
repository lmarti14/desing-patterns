package com.example.demo.desingpatterns.factory;

import com.example.demo.repository.entity.Card;
import com.example.demo.utils.Colors;

public class DefaultCardFactory extends CartaFactory{
    @Override
    public String getCard(Card card) {
        return Colors.WHITE + "|" + card.getName() + "|" + Colors.RESET;
    }
}
