/**
 * This class represents the user interface for the game Toma 6. It provides methods for reading input from the user and displaying messages.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import gal.uvigo.esei.aed1.Toma6.core.Mesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class IU {

    // Scanner object for reading input from the user
    private final Scanner teclado;

    /**
     * Constructs a new user interface.
     */
    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Reads an integer from the user.
     * @param msg the message to display to the user
     * @return the integer entered by the user
     */
    public int leeNum(String msg) {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Reads a string from the user.
     * @param msg the message to display to the user
     * @return the string entered by the user
     */
    public String leeString(String msg) {
        String toret;
        System.out.print(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Displays a message to the user.
     * @param msg the message to display
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Asks the user for the names of the players and stores them in a collection.
     * @return a collection of player names
     */
    public Collection<String> pedirNombresJugadores() {
        int numJugadores;
        String nombreJugador;
        do {
            numJugadores = leeNum("Introduce el número de jugadores: ");
        } while (numJugadores < 2 || numJugadores > 10);
        Collection<String> nombres = new ArrayList<>(numJugadores);
        for (int i = 0; i < numJugadores; i++) {
            do {
                nombreJugador = leeString("Introduce el nombre del jugador " + (i + 1) + ": ");
            } while (nombreJugador.isEmpty() || nombres.contains(nombreJugador));
            nombres.add(nombreJugador);
        }
        return nombres;
    }

    /**
     * Displays information about a player to the user.
     * @param jugador the player whose information is to be displayed
     */
    public void mostrarJugador(Jugador jugador) {
        mostrarMensaje(jugador.toString());
    }

    /**
     * Displays information about a collection of players to the user.
     * @param jugadores the players whose information is to be displayed
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador j : jugadores){
            mostrarJugador(j);
        }
    }

    /**
     * Displays information about the game table to the user.
     * @param mesa the game table whose information is to be displayed
     */
    public void mostrarMesa(Mesa mesa){
        mostrarMensaje(mesa.toString());
    }

}