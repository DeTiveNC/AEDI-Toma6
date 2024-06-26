/**
 * This class represents a player in the game Toma 6. Each player is identified by their name and the cards in their hand.
 * The player's hand is represented by a list of cards, and the player's stack (or "monto") is represented by a stack of cards.
 * The player also keeps track of the total number of bulls (or "bueyes") in their stack.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jugador {
    private final String nombre;
    private List<Carta> mano;
    private Stack<Carta> monto;
    private int contadorBueyes;

    /**
     * Constructs a new player with the given name. The player's hand and stack are initially empty.
     *
     * @param nombre the name of the player
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.monto = new Stack<>();
        this.contadorBueyes = 0;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Returns the total number of bulls (bueyes) in the player's stack (monto).
     * It iterates over the stack, popping each card and adding its number of bulls to the counter.
     *
     * @param baraja the deck of cards
     * @return the total number of bulls in the player's stack
     */
    public int getContadorBueyes(Baraja baraja) {
        while (!monto.isEmpty()) {
            Carta carta = monto.pop();
            contadorBueyes += carta.num_bueyes();
            baraja.darCarta(carta);
        }
        return contadorBueyes;
    }

    /**
     * Adds a card to the player's hand (mano) and sorts the hand in ascending order based on the card number.
     *
     * @param carta the card to be added to the player's hand
     */
    public void anadirCarta(Carta carta) {
        int posicion = 0;
        if (this.mano.isEmpty()) {
            this.mano.add(carta);
        } else {
            while (posicion < this.mano.size() && this.mano.get(posicion).number() < carta.number()) {
                posicion++;
            }
            this.mano.add(posicion, carta);
        }
    }

    /**
     * Returns the size of cards in the player's hand.
     *
     * @return the size of cards in the player's hand
     */
    public int tamañoMano() {
        return this.mano.size();
    }

    /**
     * Adds a list of cards to the player's stack (monto).
     *
     * @param cartasAComer the list of cards to be added to the player's stack
     */
    public void comerCartas(List<Carta> cartasAComer) {
        for (Carta carta : cartasAComer) {
            monto.push(carta);
        }
    }

    /**
     * Removes a card from the player's hand (mano) at the carta that you passed.
     *
     * @param index the object card to be removed
     * @return true when delete it
     */
    public boolean eliminarCartaObjeto(Carta index) {
        return this.mano.remove(index);
    }

    /**
     * Get a card from the player's hand (mano) at a certain position.
     *
     * @param index the position of the card to be picked (1-based index)
     * @return the card on that position
     */
    public Carta obtenerCartaPorIndex(int index) {
        return this.mano.get(index - 1);
    }

    /**
     * Returns a string representation of the player, including their name and the cards in their hand.
     *
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Jugador: ").append(nombre).append("\n");
        str.append("Cartas: ");
        if (!mano.isEmpty()) {
            str.append("\n");
            for (Carta carta : mano) {
                str.append("\t").append(carta.toString());
            }
            str.append("\n");
        } else {
            str.append("Ninguna\n");
        }
        return str.toString();
    }
}