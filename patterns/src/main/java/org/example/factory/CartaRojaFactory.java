package org.example.factory;

import org.example.cartas.Carta;
import org.example.cartas.CartaRoja;

public class CartaRojaFactory extends CartaFactory{

    @Override
    public Carta getCarta() {
        return new CartaRoja();
    }
}
