/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.LinkedList;


public class Juego {
    
    private final IU iu;
    private LinkedList<Jugador> jugadores;
    private Baraja baraja;
    
    public Juego(IU iu){
        this.baraja = new Baraja();
        this.iu = iu;
        this.jugadores = new LinkedList<>();
    }
        
    public void jugar(){
        insertarJugadores();
        repartirCartas();
    }
    
    // Insertamos los jugadores
    public void insertarJugadores() {
        int num_jugadores;
        String nombre;
        do{
            num_jugadores = iu.leeNum("Cuantos jugadores van a jugar? ");    
        }while(num_jugadores > 10 || num_jugadores < 2); 
        for (int i = 1; i <= num_jugadores; i++){
            nombre = iu.leeString("Dame tu nombre jugador " + i + " : ");
            jugadores.add(new Jugador(nombre));
        }
    }
    
    // repartimos 10 cartas a cada jugador
    public void repartirCartas() {
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < jugadores.size(); j++){
                jugadores.get(j).getMano().add(baraja.getCarta());            
            }
        }
        System.out.println("Las cartas han sido repartidas");
        mostrarInformacion();
    }
    
    public void mostrarInformacion() {
        for (Jugador j : jugadores) {
           iu.mostrarJugador(j);
        }
        
    }
   
}
