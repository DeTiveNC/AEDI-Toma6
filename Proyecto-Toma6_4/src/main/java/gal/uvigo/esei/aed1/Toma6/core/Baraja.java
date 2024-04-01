
/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.Random;
import pila.EnlazadaPila;



public class Baraja {
    EnlazadaPila<Carta> baraja;
    
    // Instanciamos la baraja 
    public Baraja(){
        Random rm = new Random();
        ArrayList<Carta> cartas = new ArrayList<>(104);
        this.baraja = new EnlazadaPila<>();
        for (int i = 1; i <= 104; i++){
            int n_bueyes = 1;
            if (i % 5 == 0){
                if ( i % 2 == 0) {
                    n_bueyes++;
                }
                n_bueyes++;
            }
            if (i % 11 == 0){
                n_bueyes += 4;
                if (i % 5 == 0) {
                    n_bueyes++;
                }
            }
            cartas.add(new Carta(i, n_bueyes));
        }
        while (!cartas.isEmpty()){
            int randomNumber = rm.nextInt(cartas.size());
            baraja.push(cartas.remove(randomNumber));
        }
    }
    
    // Devuelve la primera carta de la baraja
    public Carta getCarta() {
        return baraja.pop();
    }
    
}
