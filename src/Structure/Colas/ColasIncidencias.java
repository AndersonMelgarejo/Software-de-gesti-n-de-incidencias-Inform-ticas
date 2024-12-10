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
        try {
            cola.add(incidencia);
        } catch (Exception e) {
        }        
    }

    public Incidencias Desencolar() {
        return cola.poll();
    }

    public boolean eliminarPorId(int id) {
        Incidencias incidencia = buscarIncidenciaPorId(id);
        if (incidencia != null) {
            cola.remove(incidencia); // Eliminar incidencia de la cola
            return true; // Indica que la eliminación fue exitosa
        }
        return false; // Indica que no se encontró la incidencia
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
    
    public void ordenarPorId() {
        PriorityQueue<Incidencias> nuevaCola = new PriorityQueue<>(new ComparadorIdIncidencias());
        nuevaCola.addAll(this.cola); // Copiar todas las incidencias a la nueva cola
        this.cola = nuevaCola; // Reemplazar la cola actual con la nueva
    }    
    
    public void reordenarPorNivel() {
        PriorityQueue<Incidencias> nuevaCola = new PriorityQueue<>(new ComparadorIncidencia());
        nuevaCola.addAll(cola); // Copiar todos los elementos de la cola existente
        this.cola = nuevaCola; // Reemplazar la cola vieja con la nueva
    }

    public void reordenarPorTipoIncidencia() {
        PriorityQueue<Incidencias> nuevaCola = new PriorityQueue<>(new ComparadorTipoIncidencia());
        nuevaCola.addAll(cola); // Copiar todos los elementos de la cola existente
        this.cola = nuevaCola; // Reemplazar la cola vieja con la nueva
    }

}
