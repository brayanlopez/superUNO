/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import Modelo.*;
import Vista.Juego;

/**
 * Clase Uno, es la encargada de manejar los datos de la vista y el modelo
 *
 * @author Usuario
 * @version 1.0
 */
public class Uno {

    final static int saltar = 10; //Así que no tengo que recordar el número de estas cosas.
    final static int reverse = 11;
    final static int draw2 = 12;
    final static int wild = 13;
    final static int wilddraw4 = 14;

    public static void main(String[] args) throws FileNotFoundException {

        boolean gameOver = false;
        Maso draw = new Maso(); //Crea la pila de cartas
        draw.Barajar();         //Mezcla la baraja

        Scanner input = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "UNO\n El objetivo de UNO es deshacerse de todas las cartas que se “roban” inicialmente, diciendo la palabra “UNO”\n"
                + "cuando queda la última carta en la mano.");
        String numberPlayers = JOptionPane.showInputDialog("Por favor ingrese el numero de jugadores: ");

        /*Juego vista = new Juego();
        vista.setVisible(true);*/
        int numPlayers = Integer.parseInt(numberPlayers);   //Ingreso del numero de jugadores
        Jugador[] players = new Jugador[numPlayers];        //Se crea lista de jugadores
        for (int i = 0; i < numPlayers; i++) {              //Crea cada jugador
            players[i] = new Jugador();
        }
        for (int i = 0; i < 7; i++) {                       //Reparte las cartas a cada jugador
            for (int j = 0; j < numPlayers; j++) {
                players[j].TomarCarta(draw.CartaSiguiente());
            }
        }
        int t = 0, i, choice; 		//indica el turno a cada jugador //usado  para la lectura
        boolean clockwise = true;	//indica la direccion del juego
        int nextPlayerDraw = 0;		//Puede valer de 2 a 4, dependiendo en que el siguiente jugador tiene que tirar despues de  2 o 4 son jugadas
        boolean nextPlayerSkip = false;
        do {
            draw.descartar(draw.CartaSiguiente());
        } while ((draw.getCartaSuperior().getColor().equalsIgnoreCase("black")) || (draw.getCartaSuperior().getNumeroCarta() > 9));

        while (!gameOver) {
            System.out.println("------------------------------------");
            System.out.println("Jugador " + (t + 1) + " en turno...");
            System.out.println();
            System.out.println("Carta de hasta arriba....    " + draw.getCartaSuperior());
            System.out.println();
            System.out.println("Selecciona la carta por numero: ");

            for (i = 0; i < players[t].getCantidad(); i++) {
                System.out.println(i + ") " + players[t].getMano(i) + " ");
            }

            System.out.println(i + ") Robar carta");
            choice = input.nextInt();
            if (choice == i) {
                players[t].TomarCarta(draw.CartaSiguiente());
            }
            if ((players[t].getMano(choice).getColor().equalsIgnoreCase("black"))) {
                draw.descartar(players[t].JugarCarta(choice));
                while (draw.getCartaSuperior().getColor().equalsIgnoreCase("black")) {
                    System.out.println();
                    System.out.println("Select a color");
                    System.out.println("1) rojo");
                    System.out.println("2) azul");
                    System.out.println("3) verde");
                    System.out.println("4) amarillo");
                    int wildColor = input.nextInt();
                    switch (wildColor) {
                        case 1:
                            draw.wildSetColor("rojo");
                            break;
                        case 2:
                            draw.wildSetColor("azul");
                            break;
                        case 3:
                            draw.wildSetColor("verde");
                            break;
                        case 4:
                            draw.wildSetColor("amarillo");
                            break;
                        default:
                            draw.wildSetColor("negro");
                    }
                }
                if (draw.getCartaSuperior().getNumeroCarta() == wilddraw4) {
                    nextPlayerDraw = 4;
                    nextPlayerSkip = true;
                }
            } else if ((players[t].getMano(choice).getColor().equalsIgnoreCase(draw.getCartaSuperior().getColor())) || (players[t].getMano(choice).getNumeroCarta() == draw.getCartaSuperior().getNumeroCarta())) {
                System.out.println("Jugador jugo... " + players[t].getMano(choice));
                System.out.println("\n\n\n\n\n\n\n");
                draw.descartar(players[t].JugarCarta(choice));
                if (draw.getCartaSuperior().getNumeroCarta() == saltar) {
                    nextPlayerSkip = true;
                }
                if (draw.getCartaSuperior().getNumeroCarta() == reverse) {
                    clockwise = !clockwise;
                }
                if (draw.getCartaSuperior().getNumeroCarta() == draw2) {
                    nextPlayerDraw = 2;
                    nextPlayerSkip = true;
                }
            }

            if (players[t].getCantidad() == 0) {
                System.out.println("********************************");
                System.out.println("*        Jugador " + (t + 1) + " gana!        *");
                System.out.println("********************************");
                gameOver = true;
            }

            if (clockwise) {
                t++;
            } else {
                t--;
            }
            if (t == numPlayers) {
                t = 0;
            }
            if (t == -1) {
                t = numPlayers - 1;
            }
            for (i = 0; i < nextPlayerDraw; i++) {
                players[t].TomarCarta(draw.CartaSiguiente());
                System.out.println("Jugador " + (t + 1) + " Toma una carta");
            }
            System.out.println();

            nextPlayerDraw = 0;

            if (nextPlayerSkip) {
                if (clockwise) {
                    System.out.println("Jugador " + (t + 1) + " fue saltado");
                    t = t + 1;
                } else {
                    System.out.println("Jugador " + (t + 1) + " fue escapado");
                    t = t - 1;
                }
            }
            System.out.println();
            nextPlayerSkip = false;
            if (t == numPlayers) {
                t = 0;
            }
            if (t == -1) {
                t = numPlayers - 1;
            }
        }
    }
}
