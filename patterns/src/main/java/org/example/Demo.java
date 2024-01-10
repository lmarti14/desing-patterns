package org.example;

import org.example.cartas.Carta;
import org.example.data.Partida;
import org.example.data.singleton.Ronda;
import org.example.factory.CartaFactory;
import org.example.factory.CartaRojaFactory;
import org.example.factory.CartaVerdeFactory;
import org.example.jugador.Jugador;
import org.example.observe.Observador;
import org.example.prompt.Prompt;

import java.util.List;
import java.util.Scanner;


public class Demo {
    private static final Boolean TERMINAR_PARTIDA = false;


    private static final int MAYOR_PUNTAJE = 21;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ronda ronda = Ronda.getInstance();
        Jugador jugador;
        Jugador maquina;

        Prompt.presentacion();

        // Config
        CartaFactory cartaFactory = elegirInterfazCartas(scanner);

        // Crear Jugadores
        maquina = new Jugador(Jugador.NOMBRE_MAQUINA, true);
        jugador = crearJugador(scanner);

        // Crear Partidas
        Partida partida = iniciarPartida(cartaFactory, maquina, jugador, List.of(ronda));

        while (ronda.getState().accion() == 1) {

            if (partida.getEstadoPartida().accion() == 0) {
                Prompt.mostrarCartas(partida, maquina);
                System.out.println("\n\n----- Nueva Partida -----\n\n");
                Prompt.esperar();
                ronda.setPartida(partida);
                partida = iniciarPartida(cartaFactory, maquina, jugador, List.of(ronda));
            }

            // Turno Jugador
            boolean terminar = jugar(partida, jugador, cartaFactory, ronda, scanner);
            Prompt.mostrarCartas(partida, jugador);

            // Turno Maquina
            if(partida.calcularPuntosValor(maquina) < MAYOR_PUNTAJE){
                jugar(partida, maquina, cartaFactory, ronda, scanner);
                if (partida.getEstadoPartida().accion() == 1){
                    Prompt.mostrarCartas(partida, maquina);
                }
            }

            if(terminar){
                verificarYFinalizarPartida(partida, maquina, jugador, true);
            }else {
                verificarYFinalizarPartida(partida, maquina, jugador, false);
            }
        }


    }

    private static boolean jugar(Partida partida, Jugador jugador, CartaFactory factory, Ronda ronda, Scanner scanner) {
        if (jugador.isMaquina()) {
                System.out.println("\n\nTurno de " + jugador.getNombre());
                System.out.println("...");
                Carta carta = factory.getCarta();
                partida.setCartas(jugador, carta);
                Prompt.esperar();
                return  false;
        } else {

            System.out.println("\n\nTurno de " + jugador.getNombre() + "  Partida: " + (ronda.getPartidas().size() + 1) + "/5");
            System.out.println("Quieres otra carta [y] si [any] no: ");
            String opcion = scanner.nextLine();
            if (opcion.equals("y")) {
                Carta carta = factory.getCarta();
                partida.setCartas(jugador, carta);
                return false;
            } else {
                return true;
            }

        }
    }

    private static Jugador crearJugador(Scanner scanner) {
        System.out.println("\n\nIdentifícate e ingresa un nickname: ");
        scanner.nextLine();
        String nickName = scanner.nextLine();
        return new Jugador(nickName, false);
    }

    private static CartaFactory elegirInterfazCartas(Scanner scanner) {
        System.out.println("\n\n Ahora elige una interfaz para las cartas \n\n[1] Rojo \n[2] Verde");
        int setF = scanner.nextInt();
        return setUpFactory(setF);
    }

    private static CartaFactory setUpFactory(int setF) {
        if (setF == 1) {
            return new CartaRojaFactory();
        } else if (setF == 2) {
            return new CartaVerdeFactory();
        } else {
            return null;
        }
    }

    private static void verificarYFinalizarPartida(Partida partida, Jugador maquina, Jugador jugador, boolean terminar) {
        int puntosMaquina = partida.calcularPuntosValor(maquina);
        int puntosJugador = partida.calcularPuntosValor(jugador);


        if (terminar){
            if (puntosJugador > puntosMaquina && puntosMaquina <= MAYOR_PUNTAJE) {
                Prompt.getGanarPartida();
                partida.setGanador(jugador);

            } else {
                Prompt.getPerderPartida();
                partida.setGanador(maquina);
            }
            partida.finalizarPartida();
            partida.notificarSuscriptores();
        }else{

            if (puntosJugador > MAYOR_PUNTAJE){
                Prompt.getPerderPartida();
                partida.setGanador(maquina);
                partida.finalizarPartida();
                partida.notificarSuscriptores();
            }

            if (puntosMaquina > MAYOR_PUNTAJE){
                Prompt.getGanarPartida();
                partida.setGanador(jugador);
                partida.finalizarPartida();
                partida.notificarSuscriptores();
            }
        }
    }

    public static Partida iniciarPartida(CartaFactory factory, Jugador maquina, Jugador jugador, List<Observador> observadores){
        Partida partida = new Partida(observadores);
        Carta carta1 = factory.getCarta();
        Carta carta2 = factory.getCarta();
        partida.setCartas(maquina, carta1);
        partida.setCartas(jugador, carta2);
        Prompt.mostrarCartas(partida, jugador);
        return partida;
    }
}