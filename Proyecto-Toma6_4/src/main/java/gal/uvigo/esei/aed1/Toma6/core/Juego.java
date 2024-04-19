/**
 * This class represents the game Toma 6, with its rules (defined in the document Primera entrega).
 * A modular implementation is recommended.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;

import java.util.*;

/**
 * This class represents the game Toma 6.
 */
public class Juego {

    // The user interface for the game
    private final IU iu;
    // The players in the game
    private final LinkedList<Jugador> jugadores;
    // The deck of cards for the game
    private Baraja baraja;
    // The table for the game
    private Mesa mesa;
    private HashMap<Jugador, Carta> cartasEscogidas;

    /**
     * Constructs a new game with the given user interface.
     *
     * @param iu the user interface for the game
     */
    public Juego(IU iu) {
        this.iu = iu;
        this.jugadores = new LinkedList<>();
    }

    /**
     * Starts the game.
     */
    public void jugar() {
        crearBaraja();
        insertarJugadores();
        repartirCartas();
        mostrarInformacionJugadores();
        crearMesa();
        colocarCartasInicialesMesa();
        mostrarInformacionMesa();
        ordenJugadores();
    }

    /**
     * Creates a new deck of cards for the game.
     */
    private void crearBaraja() {
        this.baraja = new Baraja();
    }

    /**
     * Inserts players into the game.
     */
    private void insertarJugadores() {
        Collection<String> nombres = iu.pedirNombresJugadores();
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }
    }

    /**
     * Distributes 10 cards to each player.
     */
    private void repartirCartas() {
        for (int i = 0; i < 10; i++) {
            for (Jugador j : jugadores) {
                j.anadirCarta(baraja.getCarta());
            }
        }
        iu.mostrarMensaje("Las cartas han sido repartidas");
    }

    /**
     * Displays information about the players in the game.
     */
    private void mostrarInformacionJugadores() {
        iu.mostrarJugadores(jugadores);
    }

    /**
     * Creates a new table for the game.
     */
    private void crearMesa() {
        iu.mostrarMensaje("Creando la mesa de juego");
        mesa = new Mesa();
    }

    /**
     * Places the initial cards on the table.
     */
    private void colocarCartasInicialesMesa() {
        for (int i = 0; i < 4; i++) {
            mesa.insertarCartas(baraja.getCarta());
        }
        iu.mostrarMensaje("Cartas iniciales estan puestas en la mesa");
    }

    /**
     * Displays information about the table in the game.
     */
    private void mostrarInformacionMesa() {
        iu.mostrarMesa(mesa);
    }
    
    private void ordenJugadores(){
        cartasEscogidas = iu.cartasEscogidasOrden(jugadores);
    }
}
