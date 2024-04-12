/**
 * This class represents a card in the game Toma 6. Each card has a number and a corresponding number of oxen.
 */
package gal.uvigo.esei.aed1.Toma6.core;

/**
 * A record representing a card in the game Toma 6.
 * Each card has a unique number and a corresponding number of oxen.
 */
public record Carta(int number, int num_bueyes) {
    /**
     * Constructs a new card with the given number and number of oxen.
     *
     * @param number     the number of the card
     * @param num_bueyes the number of oxen on the card
     */
    public Carta {
    }

    /**
     * Returns the number of the card.
     *
     * @return the number of the card
     */
    @Override
    public int number() {
        return number;
    }

    /**
     * Returns the number of oxen on the card.
     *
     * @return the number of oxen on the card
     */
    @Override
    public int num_bueyes() {
        return num_bueyes;
    }

    /**
     * Returns a string representation of the card, including its number and number of oxen.
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return "[" + number + "," + num_bueyes + "]";
    }
}