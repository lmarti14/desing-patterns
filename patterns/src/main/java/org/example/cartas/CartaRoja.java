package org.example.cartas;

import org.example.utils.Color;
import org.example.utils.Mazo;

public class CartaRoja implements Carta {

    private String valorCarta;

    public CartaRoja (){
        this.valorCarta = Mazo.getOne();
    }

    @Override
    public String getCartaFormat() {
        return Color.RED + "|" + valorCarta + "|" + Color.RESET;
    }

    @Override
    public String getCarta() {
        return this.valorCarta;
    }
}
