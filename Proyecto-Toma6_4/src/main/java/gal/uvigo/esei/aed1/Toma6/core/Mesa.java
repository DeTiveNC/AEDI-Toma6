/**
 * This class represents a table in the game Toma 6. The table consists of a list of linked lists of cards.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mesa {

    // The table, represented as a list of linked lists of cards
    private List<LinkedList<Carta>> mesa;

    /**
     * Constructs a new table. The table is initialized upon creation.
     */
    public Mesa() {
        crearMesa();
    }

    /**
     * Initializes the table with four empty linked lists of cards.
     */
    private void crearMesa() {
        mesa = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            mesa.add(new LinkedList<>());
        }
    }

    /**
     * Inserts a card into the table. If all rows have four cards, the card is
     * inserted into the row with the smallest difference between the card's
     * number and the last card's number in the row. If no such row exists, the
     * card is not inserted and the method returns false or if the row has all
     * the cards is going to return false. If not all rows have four cards, the
     * card is inserted into the first empty row.
     *
     * @param carta the card to be inserted
     * @return true if the card was inserted, false otherwise
     */
    public List<Carta> insertarCartas(Carta carta) {
        List<Carta> cartasComidas = new ArrayList<>();
        int index = filaPosibleAOcupar(carta);
        if (index == -1) { //aqui no puedes colocar carta en ninguna fila pq es demasiado pequeña 
            return cartasComidas;
        } else {
            if (mesa.get(index).size() < 5) {  //menor q 5 se puede insertar
                mesa.get(index).add(carta);
            } else {  // mayor de 5
                cartasComidas.addAll(mesa.get(index));
                mesa.get(index).clear();
                mesa.get(index).add(carta);
                return cartasComidas;
            }
        }
        return null;
    }

    /**
     * Inserts the card into the first empty row.
     *
     * @param carta the card to be inserted
     * @param index
     */
    public void inicializarCartasIniciales(Carta carta, int index) {
        mesa.get(index).add(carta);
    }

    /**
     * Finds the row with the smallest difference between the card's number and
     * the last card's number in the row.
     *
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
     * Returns the size of the table.
     *
     * @return the size of the table
     */
    public int tamaño(){
        return this.mesa.size();
    }

    /**
     * Modifies a specific row in the table by inserting a card and returning the cards that were replaced.
     *
     * @param index the index of the row to be modified
     * @param carta the card to be inserted
     * @return the list of cards that were replaced by the new card
     */
    public List<Carta> modificacionFila(int index, Carta carta) {
        List<Carta> filaEscogida = this.mesa.get(index);
        List<Carta> clon = new ArrayList<>(filaEscogida);
        filaEscogida.clear();
        filaEscogida.add(carta);
        return clon;
    }
    
    /**
     * Returns a string representation of the table, including all cards in all
     * rows.
     *
     * @return a string representation of the table
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mesa.size(); i++) {
            str.append("Fila ").append(i + 1).append(": ");
            for (Carta carta : mesa.get(i)) {
                str.append(carta.toString()).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public void vaciarMesa(Baraja baraja) {
        for (LinkedList<Carta> fila : mesa) {
            while(!fila.isEmpty()) {
                baraja.darCarta(fila.remove());
            }
        }
    }
    
}
