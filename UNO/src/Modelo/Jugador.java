/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Jugador, es la encargada de definir los atributos y metodos del jugador
 *
 * @author Usuario
 * @version 1.0
 */
public class Jugador {

    /**
     * atributo mano hereda de la clase carta, representa la mano del jugador
     * soporta solo 50
     */
    private Carta mano[] = new Carta[50];
    /**
     * atributo cantidad, representa el numero de cartas que tiene
     */
    private int cantidad;

    /**
     * Constructor de la clase Jugador, no tiene parametros, inicializa en la
     * mano del jugador en cero.
     */
    public Jugador() {
        this.cantidad = 0;
    }

    /**
     * Metodo get del atributo cantidad, no tiene parametros
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Metodo get del atributo mano
     *
     * @param posicion hace referencia a la posicion de la carta que se requiere
     */
    public Carta getMano(int posicion) {
        return mano[posicion];
    }

    /**
     * Metodo JugarCarta
     *
     * @param posicion
     * @return
     */
    public Carta JugarCarta(int posicion) {
        Carta cartaJugada = mano[posicion];
        for (int i = posicion; i < cantidad; i++) {
            mano[i] = mano[i + 1];
        }
        mano[cantidad] = new Carta(-1, "null");
        cantidad--;
        return cartaJugada;
    }

    /**
     * Metodo TomarCarta
     *
     * @param valor
     */
    public void TomarCarta(Carta valor) {
        mano[cantidad] = valor;
        cantidad++;
    }
}
