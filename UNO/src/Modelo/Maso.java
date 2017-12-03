/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Clase Maso, encargada de definir los atributos y metodos del maso.
 *
 * @author Usuario
 * @version 1.0
 */
public class Maso {

    /**
     * Atributo masoActual, crea el mazo de 108 cartas
     */
    private Carta masoActual[] = new Carta[108];
    /**
     * Atributo cartasDisponibles, LLeva cuenta de cuantas cartas estan
     * disponibles en el mazo
     */

    private int cartasDisponibles;
    /**
     * Atributo pilaDescartada, hace referencia a las cartas que han sido
     * jugadas
     */

    private Carta pilaDescartada[] = new Carta[108];
    /**
     * Atributo cartasJugadas lleva cuenta de cuantas cartas se han jugado
     */

    private int cartasJugadas;

    /**
     * Constructor de la clase Maso, crea el maso del juego de 108 cartas, no
     * tiene parametros
     */
    public Maso() throws FileNotFoundException {
        int numeroCarta = 0;  // Numero de carta actual
        String[] colores = {"amarillo", "verde", "azul", "rojo"};
        for (String color : colores) {
            masoActual[numeroCarta++] = new Carta(0, color);
            for (int i = 1; i <= 12; i++) {
                masoActual[numeroCarta++] = new Carta(i, color);
                masoActual[numeroCarta++] = new Carta(i, color);
            }
        }
        for (int i = 1; i <= 4; i++) {
            masoActual[numeroCarta++] = new Carta(13, "negro");
            masoActual[numeroCarta++] = new Carta(14, "negro");
        }
        cartasDisponibles = numeroCarta; // pone el numero de cartas igual al generado 
    }

    /**
     * Metodo CartaSiguiente, no tiene parametros
     *
     * @return nextCard
     */
    public Carta CartaSiguiente() {
        Carta siguiente = masoActual[0];
        for (int i = 0; i < cartasDisponibles - 2; i++) {
            masoActual[i] = masoActual[i + 1];
        }
        masoActual[cartasDisponibles - 1] = new Carta(-1, "null");
        cartasDisponibles--; //actualiza el numero de cartas disponibles
        if (cartasDisponibles == 0) {
            for (int i = 0; i < cartasJugadas - 1; i++) {
                masoActual[i] = pilaDescartada[i];
                cartasDisponibles++; //Actualiza el numero de cartas

            }
            cartasJugadas = 0; //Actualiza el monton
            Barajar();
        }
        return siguiente;
    }

    /**
     * Metodo Barajar
     */
    public void Barajar() {
        for (int i = cartasDisponibles - 1; i > 0; i--) {
            int j = (int) (new Random().nextInt(cartasDisponibles));
            Carta t = masoActual[j];
            masoActual[j] = masoActual[i];
            masoActual[i] = t;
        }
    }

    /**
     * Metodo wildSetColor se activa solo cuando el comodin es tirado
     *
     * @param color
     */
    public void wildSetColor(String color) {
        pilaDescartada[cartasJugadas - 1].color = color;
    }

    /**
     * Metodo descartar
     *
     * @param descarte
     */
    public void descartar(Carta descarte) {
        pilaDescartada[cartasJugadas] = descarte;
        cartasJugadas++;
    }

    /**
     * Metodo getCartaSuperior
     *
     * @return discardPile
     */
    public Carta getCartaSuperior() {
        return pilaDescartada[cartasJugadas - 1];
    }
}
