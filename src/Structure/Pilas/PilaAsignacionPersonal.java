package Structure.Pilas;

import Model.AsignarPersonal;
import Persistence.SaveAsignarPersonal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class PilaAsignacionPersonal implements Serializable{
    private Stack<AsignarPersonal> pila;
    public PilaAsignacionPersonal(){
        pila = new Stack();
    }
    //método que agrega datos a la pila
    public void Apilar(AsignarPersonal asig){
        pila.push(asig);
        SaveAsignarPersonal.Guardar(this);
    }
    //método que elimina un operario de la pila
    public void Desapilar(){
        if(pila.isEmpty()){
            JOptionPane.showInputDialog("La pila esta vacía");
        }else{
           pila.pop();
           SaveAsignarPersonal.Guardar(this);
        }
    }
    
    public int Cantidad(){ return pila.size(); }
    
    public AsignarPersonal ObtenerOpe(int pos){
       return pila.get(pos);
    }
    
    //ultimo objeto en la pila
    public AsignarPersonal UltimoObjeto(){
        return pila.peek();
    }
    //primer elemento ingresado a la pila
    public AsignarPersonal PrimerObjeto(){
        return pila.firstElement();
    }
    //metodo que busca un operario en la pila
    public AsignarPersonal BuscarOperario(String codbus) {
        if (codbus == null || codbus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un código válido.");
            return null; // Si el código de búsqueda es nulo o vacío, no hacemos nada
        }
    
        for (int i = 0; i < pila.size(); i++) {
            // Convertimos el ID de la incidencia a String para hacer la comparación
            String incidenciaId = String.valueOf(pila.get(i).getIncidencia().getId());
        
            // Compara el ID de la incidencia con el valor de 'codbus'
            if (codbus.equalsIgnoreCase(incidenciaId)) {
                return pila.get(i); // Devuelve el objeto AsignarPersonal que contiene la incidencia con ese ID
            }
        }
    
        // Si no se encuentra, muestra un mensaje de advertencia y retorna null
        JOptionPane.showMessageDialog(null, "Operario no encontrado con el código: " + codbus);
        return null;
    }

    //metodo que verifica si la pila esta vacia
    public boolean VerificarVacio(){
        return pila.empty();
    }

    public boolean eliminarAsignacionPorId(int idBuscado) {
    Stack<AsignarPersonal> temporal = new Stack<>();
    boolean eliminado = false;

    while (!VerificarVacio()) {
        AsignarPersonal actual = UltimoObjeto();
        Desapilar();

        if (actual.getIncidencia().getId() == idBuscado) {
            eliminado = true;  // Elimina el elemento encontrado
        } else {
            temporal.push(actual);  // Guarda los elementos restantes
        }
    }

    // Reconstruir la pila original
    while (!temporal.isEmpty()) {
        Apilar(temporal.pop());
    }

    return eliminado;
}

    public void ordenarPorEstado() {
    // Crear una lista temporal para ordenar
    List<AsignarPersonal> listaTemporal = new ArrayList<>(this.pila);

    // Ordenar la lista alfabéticamente por estado
    listaTemporal.sort(Comparator.comparing(AsignarPersonal::getEstado));

    // Vaciar la pila original
    this.pila.clear();

    // Volver a apilar los elementos ordenados
    for (AsignarPersonal asignacion : listaTemporal) {
        this.Apilar(asignacion);
    }
}

public void ordenarPorId() {
    // Paso 1: Volcar los elementos de la pila en una lista
    List<AsignarPersonal> lista = new ArrayList<>();
    while (!VerificarVacio()) {
        lista.add(UltimoObjeto());
        Desapilar();
    }

    // Paso 2: Ordenar la lista utilizando el método de burbuja
    int n = lista.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (lista.get(j).getIncidencia().getId() > lista.get(j + 1).getIncidencia().getId()) {
                // Intercambiar elementos
                AsignarPersonal temp = lista.get(j);
                lista.set(j, lista.get(j + 1));
                lista.set(j + 1, temp);
            }
        }
    }

    // Paso 3: Reconstruir la pila con los elementos ordenados
    for (int i = lista.size() - 1; i >= 0; i--) {
        Apilar(lista.get(i));
    }
}
public void ordenarPorPersonal() {
    // Crear una lista temporal para ordenar
    List<AsignarPersonal> listaTemporal = new ArrayList<>(this.pila);

    // Ordenar la lista alfabéticamente por el nombre del personal
    listaTemporal.sort(Comparator.comparing(asignacion -> asignacion.getPersonal().getNombre()));

    // Vaciar la pila original
    this.pila.clear();

    // Volver a apilar los elementos ordenados
    for (AsignarPersonal asignacion : listaTemporal) {
        this.Apilar(asignacion);
    }
}


    public Stack<AsignarPersonal> getPila() {
        return pila;
    }

    public void setPila(Stack<AsignarPersonal> pila) {
        this.pila = pila;
    }
}
