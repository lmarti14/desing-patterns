package org.example.prompt;

import org.example.cartas.Carta;
import org.example.data.Partida;
import org.example.data.singleton.Ronda;
import org.example.jugador.Jugador;
import java.util.List;

public class Prompt {


    public static void presentacion() {
        System.out.println("\n\n------------- Bienvenido al juego de Black Jack -------------\n\n");
        System.out.println("Instrucciones:");
        System.out.println("(1) El Juego consiste en cinco (5) partidas, el que gane el mayor # de partidas gana.");
        System.out.println("(2) Para ganar una ronda la suma de tus cartas debe ser <= 21 y mayor a la suma de las cartas de tu rival.");
    }

    public static void mostrarCartas(Partida partida, Jugador jugador){

        List<Carta> cartas = partida.getCartas().get(jugador);
        if(!jugador.isMaquina()){
            System.out.println("Cartas del Judador " + jugador.getNombre() + ":");
            for (Carta c: cartas) {
                System.out.print(c.getCartaFormat() + " ");
            }
            System.out.print("   Total Puntos: " + partida.calcularPuntosValor(jugador));
        }

        if (jugador.isMaquina() && partida.getEstadoPartida().accion() == 1){
            System.out.println("Cartas del Judador " + jugador.getNombre() + ":");
            for (Carta c: cartas) {
                String cartaForm = c.getCartaFormat();
                String cartaModificada = cartaForm.replace(c.getCarta(), " ?? ");
                System.out.print(cartaModificada + " ");
            }
            System.out.print("   Total Puntos: ??");
        }

        if(partida.getEstadoPartida().accion() == 0){
            System.out.println("\nCartas del Judador " + jugador.getNombre() + ":");
            for (Carta c: cartas) {
                System.out.print(c.getCartaFormat() + " ");
            }
            System.out.print("   Total Puntos: " + partida.calcularPuntosValor(jugador));
        }

    }

    public static void getGanarPartida(){
        String mensaje = """
                
                \nGenial!!! Ganaste esta Partida.
                
            """;
        System.out.println(mensaje);
    }

    public static void getPerderPartida(){
        String mensaje = """
               
                \nLo siento perdiste la partida :(
            
            """;
        System.out.println(mensaje);
    }

    public static void getEmpatarPartida(){
        String mensaje = """
               
                \nJuego Empate, el punto lo obtiene la maquina
            
            """;
        System.out.println(mensaje);
    }

    public static void esperar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrumpido mientras esperaba");
        }
    }

    public static void finDelJuego(Ronda ronda, Jugador maquina, Jugador jugador){
        long partidasGanadaMaquina = ronda.getPartidasGanadas(maquina);
        long partidasGanadaJugador = ronda.getPartidasGanadas(jugador);



        String gameOver = """
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⠀⠀⠀⢀⣴⣿⡶⠀⣾⣿⣿⡿⠟⠛⠁
            ⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⠀⠀⠀⠀⣶⣶⣦⠀⠀⠀⠀⣼⣿⣿⡇⠀⣠⣿⣿⣿⠇⣸⣿⣿⣧⣤⠀⠀⠀
            ⠀⠀⢀⣴⣾⣿⡿⠿⠿⠿⠇⠀⠀⣸⣿⣿⣿⡆⠀⠀⢰⣿⣿⣿⣷⣼⣿⣿⣿⡿⢀⣿⣿⡿⠟⠛⠁⠀⠀
            ⠀⣴⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⢠⣿⣿⣹⣿⣿⣿⣿⣿⣿⡏⢻⣿⣿⢿⣿⣿⠃⣼⣿⣯⣤⣴⣶⣿⡤⠀
            ⣼⣿⠏⠀⣀⣠⣤⣶⣾⣷⠄⣰⣿⣿⡿⠿⠻⣿⣯⣸⣿⡿⠀⠀⠀⠁⣾⣿⡏⢠⣿⣿⠿⠛⠋⠉⠀⠀⠀
            ⣿⣿⠲⢿⣿⣿⣿⣿⡿⠋⢰⣿⣿⠋⠀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⠙⠛⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀
            ⠹⢿⣷⣶⣿⣿⠿⠋⠀⠀⠈⠙⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣶⣦⣤⡀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⡇⢰⣶⣶⣾⡿⠷⣿⣿⣿⡟⠛⣉⣿⣿⣿⠆
            ⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⡎⣿⣿⣦⠀⠀⠀⢀⣤⣾⠟⢀⣿⣿⡟⣁⠀⠀⣸⣿⣿⣤⣾⣿⡿⠛⠁⠀
            ⠀⠀⠀⠀⣠⣾⣿⡿⠛⠉⢿⣦⠘⣿⣿⡆⠀⢠⣾⣿⠋⠀⣼⣿⣿⣿⠿⠷⢠⣿⣿⣿⠿⢻⣿⣧⠀⠀⠀
            ⠀⠀⠀⣴⣿⣿⠋⠀⠀⠀⢸⣿⣇⢹⣿⣷⣰⣿⣿⠃⠀⢠⣿⣿⢃⣀⣤⣤⣾⣿⡟⠀⠀⠀⢻⣿⣆⠀⠀
            ⠀⠀⠀⣿⣿⡇⠀⠀⢀⣴⣿⣿⡟⠀⣿⣿⣿⣿⠃⠀⠀⣾⣿⣿⡿⠿⠛⢛⣿⡟⠀⠀⠀⠀⠀⠻⠿⠀⠀
            ⠀⠀⠀⠹⣿⣿⣶⣾⣿⣿⣿⠟⠁⠀⠸⢿⣿⠇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠈⠙⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            """;

        System.out.println(gameOver);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Total Partidos Ganador " + jugador.getNombre() + ": " + partidasGanadaJugador);
        System.out.println("Total Partidos Ganador " + maquina.getNombre() + ": " + partidasGanadaMaquina);
        System.out.println("----------------------------------------------------------------------------");

    }

}


