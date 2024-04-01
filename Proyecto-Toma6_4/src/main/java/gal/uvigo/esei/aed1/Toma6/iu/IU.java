/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Carta;
import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class IU {

    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Lee un número de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El numero como entero
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
     * Lee un texto de teclado
     *
     * @param msg El mensaje a utilizar
     * @return El texto como String
     */
    public String leeString(String msg) {
        String toret;
        System.out.print(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    public void mostrarJugador(Jugador jugador) {
    StringBuilder str = new StringBuilder();
    str.append("Jugador: ").append(jugador.getNombre()).append("\n");
    str.append("Cartas: ");
    
    List<Carta> mano = jugador.getMano();
    if (!mano.isEmpty()) {
        str.append("\n");
        for (Carta carta : mano) {
            str.append("\t").append(carta.toString());
        }
        str.append("\n");
    } else {
        str.append("Ninguna\n");
    }
    
    System.out.println(str.toString());
}


    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador j : jugadores){
            System.out.println(j);
        }
    }

}