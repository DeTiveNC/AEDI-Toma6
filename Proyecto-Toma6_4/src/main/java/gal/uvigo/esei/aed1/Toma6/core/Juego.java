/**
 * This class represents the game Toma 6, with its rules (defined in the document Primera entrega).
 * A modular implementation is recommended.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents the game Toma 6.
 */
public class Juego {

    private final IU iu; // The user interface for the game
    private LinkedList<Jugador> jugadores; // The players in the game
    private Baraja baraja; // The deck of cards for the game

    /**
     * Constructs a new game with the given user interface.
     * @param iu the user interface for the game
     */
    public Juego(IU iu){
        this.baraja = new Baraja();
        this.iu = iu;
        this.jugadores = new LinkedList<>();
    }

    /**
     * Starts the game.
     */
    public void jugar(){
        insertarJugadores();
        repartirCartas();
    }

    /**
     * Inserts players into the game.
     */
    public void insertarJugadores() {
        Collection<String> nombres = iu.pedirNombresJugadores();
        for (int i = 0; i < nombres.size(); i++){
            jugadores.add(new Jugador(nombres.toArray()[i].toString()));
        }
    }

    /**
     * Distributes 10 cards to each player.
     */
    public void repartirCartas() {
        for (int i = 0; i < 10; i ++) {
            for (Jugador j : jugadores) {
                j.getMano().add(baraja.getCarta());
            }
        }
        iu.mostrarMensaje("Las cartas han sido repartidas");
        mostrarInformacion();
    }

    /**
     * Displays information about the game.
     */
    public void mostrarInformacion() {
        for (Jugador j : jugadores) {
            iu.mostrarJugador(j);
        }
    }
}