/*
 * This class represents the deck of the game Toma 6, in total 104 cards, numbered from 1 to 104 with the number of oxen
 * corresponding to the value of the same (check conditions in the game statement).
 * Structure: an appropriate ADT will be used
 * Functionality: shuffle the cards, return the card located on top of the deck of cards
 */
package gal.uvigo.esei.aed1.Toma6.core;


import pila.EnlazadaPila;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents a deck of cards for the game Toma 6.
 */
public class Baraja {
    Stack<Carta> baraja;

    /**
     * Constructs a new deck of cards. The deck is shuffled upon creation.
     */
    public Baraja(){
        this.baraja = crearBaraja();
    }

    private Stack<Carta> crearBaraja() {
        Random rm = new Random();
        ArrayList<Carta> cartas = new ArrayList<>(104);
        this.baraja = new Stack<>();
        // Populate the deck with 104 cards, each with a unique number and a number of oxen
        for (int i = 1; i <= 104; i++){
            int n_bueyes = 1;
            if (i % 5 == 0){
                if ( i % 2 == 0) {
                    n_bueyes++;
                }
                n_bueyes++;
            }
            if (i % 11 == 0){
                n_bueyes += 4;
                if (i % 5 == 0) {
                    n_bueyes++;
                }
            }
            cartas.add(new Carta(i, n_bueyes));
        }
        // Shuffle the deck
        while (!cartas.isEmpty()){
            int randomNumber = rm.nextInt(cartas.size());
            baraja.push(cartas.remove(randomNumber));
        }
        return baraja;
    }

    /**
     * Returns the top card from the deck.
     * @return the top card from the deck
     */
    public Carta getCarta() {
        return baraja.pop();
    }

}