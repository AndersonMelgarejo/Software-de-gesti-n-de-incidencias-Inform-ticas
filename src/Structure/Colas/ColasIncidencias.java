package Structure.Colas;

import Model.Incidencias;
import java.io.Serializable;
import java.util.PriorityQueue;

public class ColasIncidencias implements Serializable{
    private PriorityQueue<Incidencias> cola;
    
    public ColasIncidencias() {
        // Inicializamos la cola con un comparador personalizado
        this.cola = new PriorityQueue<>(new ComparadorIncidencia());        
    }

    public void Encolar(Incidencias incidencia) {
        cola.add(incidencia);
    }

    public Incidencias Desencolar() {
        return cola.poll();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }
    public Incidencias VerPrimero(){ 
        return cola.peek(); 
    }
    public Incidencias VerUltimo() {
        Incidencias ultimo = null;
        for (Incidencias incidencia : cola) {
            ultimo = incidencia;  // El último elemento en la iteración será el último de la cola
        }
        return ultimo;
    }
    public PriorityQueue<Incidencias> getCola() {
        return cola;
    }
    
    public Incidencias buscarIncidenciaPorId(int idBuscado) {
        for (Incidencias incidencia : cola) {
            if (incidencia.getId() == idBuscado) {
                return incidencia; // Si coincide el id, devuelve la incidencia
            }
        }
        return null; // No se encontró
    }
}
