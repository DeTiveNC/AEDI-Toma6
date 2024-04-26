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
    // The cards chosen by the players in a round
    private List<Map.Entry<Jugador, Carta>> cartasEscogidas;

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
        boolean acabada = false;
        insertarJugadores();
        do {
            crearBaraja();
            repartirCartas();
            mostrarInformacionJugadores();
            crearMesa();
            colocarCartasInicialesMesa();
            mostrarInformacionMesa();
            for (int i = 0; i < 10; i++) {
                iu.mostrarMensaje("Ronda " + (i + 1) + ":");
                ronda();
            }
            acabada = finalizacionPartida();
        } while (acabada != true);
        iu.mostrarMensaje("Partida Acabada");
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
            mesa.inicializarCartasIniciales(baraja.getCarta());
        }
        iu.mostrarMensaje("Cartas iniciales estan puestas en la mesa");
    }

    /**
     * Displays information about the table in the game.
     */
    private void mostrarInformacionMesa() {
        iu.mostrarMesa(mesa);
    }

    /**
     * Executes a round of the game. Each player chooses a card, and the cards
     * are placed on the table in ascending order. If a card cannot be placed on
     * the table, a message is displayed.
     */
    private void ronda() {
        cartasEscogidas = iu.cartasEscogidasOrden(jugadores);

        // Utilizamos una lambda para comparar los valores de las cartas directamente
        cartasEscogidas.sort((jugadorAnterior, jugadorPosterior) -> jugadorAnterior.getValue().number() - jugadorPosterior.getValue().number());

        // Now cartasOrdenadas has the cards ordered by the value of the Card
        for (Map.Entry<Jugador, Carta> entrada : cartasEscogidas) {
            Carta carta = entrada.getValue();
            iu.mostrarMensaje(entrada.getKey() + "pone carta: " + entrada.getValue());
            List<Carta> resultado = mesa.insertarCartas(carta);
            System.out.println(resultado);
            if (resultado != null) {
                if (resultado.isEmpty()) {
                    iu.mostrarMensaje(entrada.getKey() + " necesitas indicar fila a poner la carta");
                    resultado = insertarFilaEspecifica(carta);
                    iu.mostrarMesa(mesa);
                }
                entrada.getKey().comerCartas(resultado);
            }
        }
        iu.mostrarMesa(mesa);
    }

    private List<Carta> insertarFilaEspecifica(Carta carta) {
        int index = iu.obtenerFilaMesa(mesa);
        List<Carta> result = mesa.modificacionFila(index, carta);
        return result;
    }

    private boolean finalizacionPartida() {
        boolean terminado = false;

        int[] ranking = new int[jugadores.size()]; // [23,24,78,12]
        for (int i = 0; i < jugadores.size(); i++) {
            ranking[i] = jugadores.get(i).getContadorBueyes();
            if (ranking[i] >= 66) {
                terminado = true;
            }
        }
        if (terminado == true) {
            int menor = ranking[0];
            for (int j = 1; j < ranking.length; j++) {
                if (menor > ranking[j]) {
                    menor = ranking[j];
                }
            }
            ArrayList<Integer> ganadores = new ArrayList<>();
            for (int k = 0; k < ranking.length; k++) {
                if (menor == ranking[k]) {
                    ganadores.add(k);
                }
            }
            for (Integer k : ganadores) {
                iu.mostrarMensaje("Ganador/es : ");
                iu.mostrarJugador(jugadores.get(k));
            }
        }
        return terminado;
    }
}
