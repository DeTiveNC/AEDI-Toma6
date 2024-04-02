/*
 * This class represents a player in the game Toma 6. Each player is identified by their name and the cards in their hand.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player in the game Toma 6.
 */
public class Jugador {
    private final String nombre; // The name of the player
    private List<Carta> mano; // The cards in the player's hand

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
     * Returns the cards in the player's hand.
     * @return the cards in the player's hand
     */
    public List<Carta> getMano() {
        return mano;
    }
}