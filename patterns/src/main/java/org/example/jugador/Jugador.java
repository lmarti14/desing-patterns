package org.example.jugador;

public class Jugador {

    public final static String NOMBRE_MAQUINA = "Maquina";
    private String nombre;
    private boolean maquina;

    public Jugador(String nombre, boolean maquina){
        this.nombre = nombre;
        this.maquina = maquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isMaquina() {
        return maquina;
    }

    public void setMaquina(boolean maquina) {
        this.maquina = maquina;
    }
}
