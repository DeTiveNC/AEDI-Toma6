/**
 * This class represents the deck of the game Toma 6, in total 104 cards, numbered from 1 to 104 with the number of oxen
 * corresponding to the value of the same (check conditions in the game statement).
 * Structure: an appropriate ADT will be used
 * Functionality: shuffle the cards, return the card located on top of the deck of cards
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents a deck of cards for the game Toma 6.
 */
public class Baraja {
    // The deck of cards
    private Stack<Carta> baraja;

    /**
     * Constructs a new deck of cards. The deck is shuffled upon creation.
     */
    public Baraja(){
        this.baraja = crearBaraja();
    }

    /**
     * Creates a new deck of cards and shuffles it.
     * @return the shuffled deck of cards
     */
    private Stack<Carta> crearBaraja() {
        Random rm = new Random();
        ArrayList<Carta> cartas = new ArrayList<>(104);
        this.baraja = new Stack<>();
        // Populate the deck with 104 cards, each with a unique number and a number of oxen
        for (int i = 1; i <= 104; i++){
            int n_bueyes = getNBueyes(i);
            // Add the card to the deck
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
     * Calculates the number of oxen for a given card number.
     * @param i the card number
     * @return the number of oxen for the card
     */
    private int getNBueyes(int i) {
        int n_bueyes = 1;
        // If the card number is divisible by 5, increment the number of oxen
        if (i % 5 == 0){
            // If the card number is even, increment the number of oxen
            if ( i % 2 == 0) {
                n_bueyes++;
            }
            n_bueyes++;
        }
        // If the card number is divisible by 11, increment the number of oxen by 4
        if (i % 11 == 0){
            n_bueyes += 4;
            // If the card number is divisible by 5, increment the number of oxen
            if (i % 5 == 0) {
                n_bueyes++;
            }
        }
        return n_bueyes;
    }

    /**
     * Returns the top card from the deck.
     * @return the top card from the deck
     */
    public Carta getCarta() {
        return baraja.pop();
    }
}