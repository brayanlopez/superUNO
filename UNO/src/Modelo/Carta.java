/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase carta, es la que se encarga de definir los atributos y metodos de las
 * cartas del juego
 *
 * @author Usuario
 * @version 1.0
 */
public class Carta {

    final int salto = 10;
    final int reversa = 11;
    final int tomaDos = 12;
    final int comodin = 13;
    final int tomaCuatro = 14;
    int numeroCarta;
    String color;

    /**
     * Constructor de la clase carta
     *
     * @param inValue Numero de la carta
     * @param inColor Color de la carta
     */
    public Carta(int inValue, String inColor) {
        this.numeroCarta = inValue;
        this.color = inColor;
    }

    /**
     * Metodo get del atributo numeroCarta, no tiene parametros
     */
    public int getNumeroCarta() {
        return numeroCarta;
    }

    /**
     * Metodo get del atributo color, no tiene parametros
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo toString, no tiene parametros,
     * @return los atributos de la carta que se juega
     */
    public String toString() {
        if (numeroCarta < 10) {
            return color + " " + numeroCarta;
        }
        switch (numeroCarta) {
            case salto:
                return color + " salto";
            case reversa:
                return color + " reversa";
            case tomaDos:
                return color + " toma 2";
            case comodin:
                return color + " comodin";
            case tomaCuatro:
                return color + " comodin toma 4";
            default:
                return "null";
        }
    }
}
