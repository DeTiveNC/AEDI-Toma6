/**
 * This class represents a player in the game Toma 6. Each player is identified by their name and the cards in their hand.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player in the game Toma 6.
 */
public class Jugador {
    // The name of the player
    private final String nombre;
    // The cards in the player's hand
    private List<Carta> mano;

    /**
     * Constructs a new player with the given name. The player's hand is initially empty.
     * @param nombre the name of the player
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Adds a card to the player's hand and sorts the hand if it's not empty.
     * @param carta the card to be added to the player's hand
     */
    public void anadirCarta(Carta carta) {
        this.mano.add(carta);
        ordenarCartas();
    }

    /**
     * Sorts the cards in the player's hand in ascending order.
     */
    private void ordenarCartas() {
        int n = mano.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (mano.get(j).number() < mano.get(minIndex).number()) {
                    minIndex = j;
                }
            }
            // Swap the cards at positions i and minIndex
            Carta temp = mano.get(minIndex);
            mano.set(minIndex, mano.get(i));
            mano.set(i, temp);
        }
    }

    /**
     * Returns the size of cards in the player's hand.
     * @return the size of cards in the player's hand
     */
    public int tamañoMano(){
        return this.mano.size();
    }

    /**
     * Eliminates a card from the list mano in a certain position
     * @param index
     * @return 
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
