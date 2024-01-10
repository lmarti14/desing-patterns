package org.example.data;

import org.example.cartas.Carta;
import org.example.jugador.Jugador;
import org.example.observe.Observador;
import org.example.state.Finaliza;
import org.example.state.Inicia;
import org.example.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Partida {

    private final Map<Jugador, List<Carta>> cartas;

    private List<Observador> suscriptores = new ArrayList<>();

    private Integer jugado;

    private Jugador ganador;

    private State estadoPartida;

    public Partida(List<Observador> observadors) {
        this.cartas = new HashMap<>();
        this.suscriptores = observadors;
        this.jugado = 0;
        this.estadoPartida = new Inicia();
    }

    public void agregarSuscriptor(Observador observador) {
        suscriptores.add(observador);
    }

    public void eliminarSuscriptor(Observador observador) {
        suscriptores.remove(observador);
    }

    public void publicarJuego(int juego) {
        this.jugado = 1;
        notificarSuscriptores();
    }

    public void notificarSuscriptores() {
        this.jugado++;
        for (Observador observador : suscriptores) {
            observador.actualizar(jugado);
        }
    }

    public int calcularPuntosValor(Jugador jugador) {
        int totalPuntos = 0;
        List<Carta> listaDePuntos = cartas.get(jugador);

        for (Carta carta : listaDePuntos) {
            String valor = carta.getCarta().substring(2);
            totalPuntos += obtenerValorCarta(valor);
        }
        return totalPuntos;
    }

    private int obtenerValorCarta(String valor) {
        valor = valor.trim().replaceAll("\\p{C}", "");
        int valorNumerico = Character.digit(valor.charAt(0), 10);
        return switch (valor) {
            case "J", "Q", "K", "A", "10" -> 10;
            default -> valorNumerico;
        };
    }

    public List<Carta> getPuntos(Jugador jugador) {
        return cartas.get(jugador);
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public State getEstadoPartida() {
        return estadoPartida;
    }

    public void finalizarPartida() {
        this.estadoPartida = new Finaliza();
    }

    public Map<Jugador, List<Carta>> getCartas() {
        return cartas;
    }

    public void setCartas(Jugador jugador, Carta carta){
        if (this.cartas.get(jugador) == null){
            cartas.put(jugador, new ArrayList<>());
        }
        cartas.get(jugador).add(carta);
    }
}
