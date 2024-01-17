package com.example.demo.desingpatterns.factory;

import com.example.demo.repository.entity.Card;

public abstract class CartaFactory {

    public abstract String getCard(Card card);
}