/**
 * This class represents a table in the game Toma 6. The table consists of a list of linked lists of cards.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mesa {
    // The table, represented as a list of linked lists of cards
    private List<LinkedList<Carta>> mesa;
    private final IU iu;
    private Baraja baraja;

    /**
     * Constructs a new table. The table is initialized upon creation.
     */
    public Mesa(){
        iu = new IU();
        crearMesa();
    }

    /**
     * Initializes the table with four empty linked lists of cards.
     */
    public void crearMesa(){
        mesa = new ArrayList<>(4);
        for (int i = 0; i < 4; i++){
            mesa.add(new LinkedList<>());
        }
    }

    /**
     * Inserts a card into the table. If all rows have four cards, the card is inserted into the row with the smallest difference
     * between the card's number and the last card's number in the row. If no such row exists, the card is not inserted and the method
     * returns false or if the row has all the cards is going to return false. If not all rows have four cards, the card is inserted into the first empty row.
     * @param carta the card to be inserted
     * @return true if the card was inserted, false otherwise
     */
    public boolean insertarCartas(Carta carta) {
        boolean insertado = true;
        if (todasLasFilasTienenCuatroCartas()) {
            int index = filaPosibleAOcupar(carta);
            if (index == -1) {
                iu.mostrarMensaje("Carta demasiado pequeña");
                baraja.pushCarta(carta);
                insertado = false;
            } else {
                if (mesa.get(index).size() < 5){
                    mesa.get(index).add(carta);
                } else {
                    iu.mostrarMensaje("Fila ocupada");
                    baraja.pushCarta(carta);
                    insertado = false;
                }
            }
        } else {
            inicializarCartasIniciales(carta);
        }
        return insertado;
    }

    /**
     * Checks if all rows have four cards.
     * @return true if all rows have four cards, false otherwise
     */
    private boolean todasLasFilasTienenCuatroCartas() {
        boolean cartasEnFila = false;
        for (LinkedList<Carta> fila : mesa) {
            cartasEnFila = !fila.isEmpty();
        }
        return cartasEnFila;
    }

    /**
     * Inserts the card into the first empty row.
     * @param carta the card to be inserted
     */
    private void inicializarCartasIniciales(Carta carta) {
        boolean agregada = false;
        for (LinkedList<Carta> fila : mesa) {
            if (fila.isEmpty() && !agregada) {
                fila.add(carta);
                agregada = true;
            }
        }
    }

    /**
     * Finds the row with the smallest difference between the card's number and the last card's number in the row.
     * @param carta the card to be inserted
     * @return the index of the row, or -1 if no such row exists
     */
    private int filaPosibleAOcupar(Carta carta) {
        int indexMejorFila = -1;
        int diferenciaMinima = Integer.MAX_VALUE;

        for (int i = 0; i < mesa.size(); i++) {
            LinkedList<Carta> fila = mesa.get(i);
            if (!fila.isEmpty() && fila.getLast().number() < carta.number()) {
                int diferenciaActual = carta.number() - fila.getLast().number();
                if (diferenciaActual < diferenciaMinima) {
                    diferenciaMinima = diferenciaActual;
                    indexMejorFila = i;
                }
            }
        }
        return indexMejorFila;
    }

    /**
     * Returns a string representation of the table, including all cards in all rows.
     * @return a string representation of the table
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mesa.size(); i++){
            str.append("Fila ").append(i+1).append(": ");
            for (Carta carta : mesa.get(i)){
                str.append(carta.toString()).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}