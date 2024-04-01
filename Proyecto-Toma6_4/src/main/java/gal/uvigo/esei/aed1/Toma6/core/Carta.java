/*
 * Representa una carta, formada por un número y la cantidad de bueyes correspondiente
 */
package gal.uvigo.esei.aed1.Toma6.core;

public class Carta {
    private final int number;
    private final int num_bueyes;

    public Carta(int number, int num_bueyes) {
        this.number = number;
        this.num_bueyes = num_bueyes;
    }

    public int getNumber() {
        return number;
    }

    public int getNum_bueyes() {
        return num_bueyes;
    }

    @Override
    public String toString() {
        return "CARTA: " +  number + ", numero de bueyes " + num_bueyes  + "\n";
    } 
}


