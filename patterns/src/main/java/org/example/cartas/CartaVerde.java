package org.example.cartas;

import org.example.utils.Color;
import org.example.utils.Mazo;

public class CartaVerde implements Carta {

    private String valorCarta;

    public CartaVerde (){
        this.valorCarta = Mazo.getOne();
    }

    @Override
    public String getCartaFormat() {
        return Color.GREEN + "|" + valorCarta + "|" + Color.RESET;
    }
    @Override
    public String getCarta() {
        return this.valorCarta;
    }
}
