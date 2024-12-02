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

    public PriorityQueue<Incidencias> getCola() {
        return cola;
    }
}
