package org.example.factory;

import org.example.cartas.Carta;
import org.example.cartas.CartaVerde;

public class CartaVerdeFactory extends CartaFactory{

    @Override
    public Carta getCarta() {
        return new CartaVerde();
    }
}
