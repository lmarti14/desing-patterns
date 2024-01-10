package org.example.data.singleton;

import org.example.data.Partida;
import org.example.jugador.Jugador;
import org.example.observe.Observador;
import org.example.state.Finaliza;
import org.example.state.Inicia;
import org.example.state.State;

import java.util.ArrayList;
import java.util.List;


public final class Ronda implements Observador {

    private final int MAX_PARTIDAS = 5;

    private static Ronda ronda;

    private List<Partida> partidas;

    private State state;

    private int numeroDePartidas;

    private Ronda(){
        this.partidas = new ArrayList<>();
        this.state = new Inicia();
        this.numeroDePartidas = 0;
    }

    public static Ronda getInstance(){
        if (ronda == null){
            return new Ronda();
        }
        return ronda;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartida(Partida partida) {
        this.partidas.add(partida);
    }

    public State getState() {
        return state;
    }

    public void setState() {
        this.state = new Finaliza();
    }

    public long getPartidasGanadas(Jugador jugador){
        return getPartidas().stream()
            .filter(partida -> partida.getGanador().equals(jugador))
            .count();
    }

    @Override
    public void actualizar(int jugado) {
        this.numeroDePartidas += jugado;
        if(MAX_PARTIDAS == this.numeroDePartidas){
            this.state = new Finaliza();
        }
    }
}
