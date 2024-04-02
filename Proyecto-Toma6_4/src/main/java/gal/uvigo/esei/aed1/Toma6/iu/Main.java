/**
 * This package contains the user interface classes for the game Toma 6.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Juego;

/**
 * This is the main class that runs the game Toma 6.
 */
public class Main {
    /**
     * The main method of the application.
     * It creates a new instance of the user interface and the game, and then starts the game.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Create a new instance of the user interface
        IU iu = new IU();
        // Create a new instance of the game with the user interface
        Juego toma6 = new Juego(iu);
        // Start the game
        toma6.jugar();
    }
}