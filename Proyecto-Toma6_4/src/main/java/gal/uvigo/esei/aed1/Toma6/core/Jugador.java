/**
 * This class represents a player in the game Toma 6. Each player is identified by their name and the cards in their hand.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * This class represents a player in the game Toma 6.
 */
public class Jugador {
    // The name of the player
    private final String nombre;
    // The cards in the player's hand
    private List<Carta> mano;
    private Stack<Carta> monto;
    private int contadorBueyes;

    /**
     * Constructs a new player with the given name. The player's hand is initially empty.
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
     * @return the name of the player
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Returns the total number of bulls (bueyes) in the player's stack (monto).
     * It iterates over the stack, popping each card and adding its number of bulls to the counter.
     * @return the total number of bulls in the player's stack
     */
    public int getContadorBueyes(){
        while (!monto.isEmpty()) contadorBueyes += monto.pop().num_bueyes();
        return contadorBueyes;
    }

    /**
     * Adds a card to the player's hand (mano) and sorts the hand in ascending order based on the card number.
     * @param carta the card to be added to the player's hand
     */
    public void anadirCarta(Carta carta) {
        this.mano.add(carta);
        this.mano.sort(Comparator.comparingInt(Carta::number));
    }

    /**
     * Returns the size of cards in the player's hand.
     * @return the size of cards in the player's hand
     */
    public int tamañoMano(){
        return this.mano.size();
    }

    /**
     * Adds a list of cards to the player's stack (monto).
     * @param cartasAComer the list of cards to be added to the player's stack
     */
    public void comerCartas(List<Carta> cartasAComer){
        for (Carta carta: cartasAComer) {
            monto.push(carta);
        }
    }

    /**
     * Removes a card from the player's hand (mano) at a certain position.
     * @param index the position of the card to be removed (1-based index)
     * @return the removed card
     */
    public Carta eliminarCartaporPosicion(int index){
        return this.mano.remove(index - 1);
    }

    /**
     * Returns a string representation of the player, including their name and the cards in their hand.
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