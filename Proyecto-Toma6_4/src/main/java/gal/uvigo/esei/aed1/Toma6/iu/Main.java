
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Juego;

public class Main {
    /**
     * Método principal de la aplicación
     * @param args
     */
    public static void main(String[] args) {              
        IU iu = new IU();
        Juego toma6 = new Juego(iu);
        toma6.jugar();
    }   
}