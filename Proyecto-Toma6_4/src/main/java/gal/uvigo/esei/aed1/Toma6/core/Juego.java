/**
 * This class represents the game Toma 6, with its rules (defined in the document Primera entrega).
 * A modular implementation is recommended.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
        jugando();
    }

    /**
     * Creates a new deck of cards for the game.
     */
    public void crearBaraja() {
        this.baraja = new Baraja();
    }

    /**
     * Inserts players into the game.
     */
    public void insertarJugadores() {
        Collection<String> nombres = iu.pedirNombresJugadores();
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }
    }

    /**
     * Distributes 10 cards to each player.
     */
    public void repartirCartas() {
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
    public void mostrarInformacionJugadores() {
        iu.mostrarJugadores(jugadores);
    }

    /**
     * Creates a new table for the game.
     */
    public void crearMesa() {
        System.out.println("Creando la mesa de juego");
        mesa = new Mesa();
    }

    /**
     * Places the initial cards on the table.
     */
    public void colocarCartasInicialesMesa() {
        for (int i = 0; i < 4; i++) {
            mesa.insertarCartas(baraja.getCarta());
        }
    }

    /**
     * Displays information about the table in the game.
     */
    public void mostrarInformacionMesa() {
        iu.mostrarMesa(mesa);
    }
    
    public void jugando(){
        for (Jugador jugador : jugadores) {
            while(!jugador.getMano().isEmpty()){
                seleccionarCartaMano();
            }
        }

    }

    public void seleccionarCartaMano() {
        List<Carta> cartasSeleccionadas = new ArrayList<>();

        // Cada jugador selecciona una carta y la muestra
        for (Jugador jugador : jugadores) {
            int pos;
            System.out.println("\nCartas de "+jugador.getNombre()+"\n"+jugador.getMano());
            do {
                pos = iu.leeNum("\nJugador " + jugador.getNombre() + ", introduce la posición de la carta a seleccionar: ");

            } while (pos <= 0 || pos > jugador.getMano().size());

            Carta cartaSeleccionada = jugador.getMano().get(pos - 1);
            jugador.getMano().remove(pos-1);
            System.out.println("Jugador " + jugador.getNombre() + " ha seleccionado la carta: " + cartaSeleccionada);
            //Vamos a insertarla en orden
            insertarEnOrden(cartasSeleccionadas, cartaSeleccionada);
        }

        // Coloca las cartas en la mesa en el orden determinado
        for (Carta carta : cartasSeleccionadas) {
            mesa.insertarCartas(carta);

        }
        System.out.println("Estado de la mesa después de colocar la carta: ");
        System.out.println(mesa);

    }

    public void insertarEnOrden(List<Carta> lista, Carta carta) {
        if (lista.isEmpty()) {
        lista.add(carta);
    } else {
        int indice = 0;
        while (indice < lista.size() && lista.get(indice).number() < carta.number()) {
            indice++;
        }
        lista.add(indice, carta);
    }
    }

}
