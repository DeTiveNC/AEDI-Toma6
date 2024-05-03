/**
 * This class represents the game Toma 6, with its rules (defined in the document Primera entrega).
 * A modular implementation is recommended.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;

import java.util.*;

/**
 * This class represents the game Toma 6.
 * It manages the game flow, including player turns, card distribution, and game termination.
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
    //players ranking
    private int[] ranking;

    /**
     * Constructs a new game with the given user interface.
     *
     * @param iu the user interface for the game
     */
    public Juego(IU iu) {
        this.iu = iu;
        this.jugadores = new LinkedList<>();
        this.baraja = new Baraja();
        this.mesa = new Mesa();
    }

    /**
     * Starts the game.
     * It manages the game flow, including player turns, card distribution, and game termination.
     */
    public void jugar() {
        boolean acabada;
        insertarJugadores();
        do {
            baraja.barajar(); //barajamos la baraja que hemos creado
            repartirCartas();
            mostrarInformacionJugadores();
            colocarCartasInicialesMesa();
            mostrarInformacionMesa();
            for (int i = 0; i < 10; i++) {
                iu.mostrarMensaje("Ronda " + (i + 1) + ":");
                ronda();
            }
            iu.mostrarMensaje("Fin de la ronda");
            acabada = finalizacionPartida();
        } while (!acabada);
        iu.mostrarMensaje("Partida Acabada");
    }

    /**
     * Inserts players into the game.
     * It asks for player names through the user interface and adds them to the game.
     */
    private void insertarJugadores() {
        Collection<String> nombres = iu.pedirNombresJugadores();
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }
        this.ranking = new int [jugadores.size()];
    }

    /**
     * Distributes 10 cards to each player.
     * It takes cards from the deck and gives them to the players.
     */
    private void repartirCartas() {
        iu.mostrarMensaje("Creando la mesa de juego");
        for (int i = 0; i < 10; i++) {
            for (Jugador j : jugadores) {
                j.anadirCarta(baraja.getCarta());
            }
        }
        iu.mostrarMensaje("Las cartas han sido repartidas");
    }

    /**
     * Displays information about the players in the game.
     * It uses the user interface to show the players' information.
     */
    private void mostrarInformacionJugadores() {
        iu.mostrarJugadores(jugadores);
    }

    /**
     * Places the initial cards on the table.
     * It takes cards from the deck and places them on the table.
     */
    private void colocarCartasInicialesMesa() {
        for (int i = 0; i < 4; i++) {
            mesa.inicializarCartasIniciales(baraja.getCarta(),i);
        }
        iu.mostrarMensaje("Cartas iniciales estan puestas en la mesa");
    }

    /**
     * Displays information about the table in the game.
     * It uses the user interface to show the table's information.
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
        // The cards chosen by the players in a round
        List<Map.Entry<Jugador, Carta>> cartasEscogidas = iu.cartasEscogidasOrden(jugadores);

        // Utilizamos una lambda para comparar los valores de las cartas directamente
        cartasEscogidas.sort(Comparator.comparingInt(jugadorAnterior -> jugadorAnterior.getValue().number()));

        // Now cartasOrdenadas has the cards ordered by the value of the Card
        for (Map.Entry<Jugador, Carta> entrada : cartasEscogidas) {
            Carta carta = entrada.getValue();
            iu.mostrarMensaje(entrada.getKey() + "pone carta: " + entrada.getValue());
            List<Carta> resultado = mesa.insertarCartas(carta);
            if (resultado != null) {
                if (resultado.isEmpty()) {
                    iu.mostrarMensaje(entrada.getKey() + " necesitas indicar fila a poner la carta");
                    resultado = insertarFilaEspecifica(carta);
                    iu.mostrarMesa(mesa);
                }
                entrada.getKey().comerCartas(resultado);
            }
            iu.mostrarMesa(mesa);
        }
        iu.mostrarMesa(mesa);
    }

    /**
     * Inserts a card into a specific row on the table.
     * @param carta the card to be inserted
     * @return the list of cards that were replaced by the new card
     */
    private List<Carta> insertarFilaEspecifica(Carta carta) {
        int index = iu.obtenerFilaMesa(mesa);
        return mesa.modificacionFila(index, carta);
    }

    /**
     * Determines if the game is over. The game is over if any player has 66 or more bulls.
     * @return true if the game is over, false otherwise
     */
    private boolean finalizacionPartida() {
        boolean terminado = false;

        for (int i = 0; i < jugadores.size(); i++) {
            ranking[i] = jugadores.get(i).getContadorBueyes(this.baraja);
            if (ranking[i] >= 66) {
                terminado = true;
            }
            iu.mostrarMensaje("El jugador " + jugadores.get(i).getNombre() + " tiene " + ranking[i] + " bueyes");
        }
        if (terminado) {
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
                iu.mostrarMensaje("Ganador/es: ");
                iu.mostrarMensaje(jugadores.get(k).getNombre());
            }
        } else {
            vaciarMesa();
        }
        return terminado;
    }

    /**
     * Empties the table.
     * It removes all cards from the table.
     */
    private void vaciarMesa() {
        mesa.vaciarMesa(this.baraja);
    }

}