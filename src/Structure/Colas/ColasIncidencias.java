/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structure.Colas;

import Model.Incidencias;
import java.util.PriorityQueue;

/**
 *
 * @author Renzo
 */
public class ColasIncidencias {
    private PriorityQueue<Incidencias> cola;
    
    
    public ColasIncidencias(){
        cola = new PriorityQueue(20,new ComparadorIncidencia());
    }
    public void Encolar(Incidencias sol){ 
        cola.add(sol);
    }
    public void Desencolar(){ 
        cola.poll(); 
    }
    public Incidencias VerPrimero(){ 
        return cola.peek(); 
    }
    public PriorityQueue<Incidencias> getCola() {
        return cola;
    }
    public void setCola(PriorityQueue<Incidencias> cola) {
        this.cola = cola;
    }
}
