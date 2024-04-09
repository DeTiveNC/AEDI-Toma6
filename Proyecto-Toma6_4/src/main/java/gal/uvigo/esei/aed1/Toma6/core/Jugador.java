/*
 * This class represents a player in the game Toma 6. Each player is identified by their name and the cards in their hand.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.Collections;
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
    
    public void añadirCarta(Carta carta) {
        if (mano.isEmpty()) {
         this.mano.add(carta);   
        }
        else {
            this.mano.add(carta);
            ordenarCartas();
        }
    }
    
    public void ordenarCartas() {
    int n = mano.size();
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (mano.get(j).getNumber() < mano.get(minIndex).getNumber()) {
                minIndex = j;
            }
        }
        // Intercambiar las cartas en las posiciones i y minIndex
        Carta temp = mano.get(minIndex);
        mano.set(minIndex, mano.get(i));
        mano.set(i, temp);
    }
}


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