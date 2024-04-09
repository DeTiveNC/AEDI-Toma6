/*
 * This class represents a card in the game Toma 6. Each card has a number and a corresponding number of oxen.
 */
package gal.uvigo.esei.aed1.Toma6.core;

public class Carta {
    private final int number; // The number of the card
    private final int num_bueyes; // The number of oxen on the card

    /**
     * Constructs a new card with the given number and number of oxen.
     * @param number the number of the card
     * @param num_bueyes the number of oxen on the card
     */
    public Carta(int number, int num_bueyes) {
        this.number = number;
        this.num_bueyes = num_bueyes;
    }

    /**
     * Returns the number of the card.
     * @return the number of the card
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the number of oxen on the card.
     * @return the number of oxen on the card
     */
    public int getNum_bueyes() {
        return num_bueyes;
    }

    /**
     * Returns a string representation of the card, including its number and number of oxen.
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return "[" +  number + "," + num_bueyes  + "]";
    }
}