package Structure.ListasDobles;

import Model.Departamento;
import java.io.Serializable;

/**
 *
 * @author 
 */
public class ListaDoble implements Serializable{
    public Nodo ini;
    public Nodo fin;
    private int size;
    public ListaDoble(){
        ini=fin=null;
    }//fin constructor
    // Método que devuelve la cantidad de nodos en la lista
public int contarNodos() {
    int contador = 0;
    Nodo actual = ini;
    while (actual != null) {
        contador++;
        actual = actual.sig;
    }
    return contador;
}

    public Departamento obtenerNodo(int pos) {
    if (pos < 0 || pos >= size) { // Validar rango basado en size
        return null;
    }
    Nodo actual = ini;
    for (int i = 0; i < pos; i++) {
        actual = actual.sig;
    }
    return actual.depa;
}

    //método que inserta un Nodo al final de la lista
    public void InsertarAlFinal(Departamento depa) {
    Nodo nuevo = new Nodo(depa);
    if (ini == null) {
        ini = fin = nuevo;
    } else {
        nuevo.ant = fin;
        fin.sig = nuevo;
        fin = nuevo;
    }
    size++; // Incrementar tamaño
}


    public Nodo BuscarAmbiente(String ambiente){
        Nodo encontrado=ini;
        while(encontrado!=null){
            if(encontrado.depa.getAmbiente().equalsIgnoreCase(ambiente))
                return encontrado;
            encontrado=encontrado.sig;
        }//fin
        return null; // indicará que el codigo no existe;
    }
    //método que elimina un nodo
    public void EliminarNodo(Nodo actual) {
    if (actual != null) {
        if (actual == ini) {
            ini = actual.sig;
            if (actual.sig != null) actual.sig.ant = null;
        } else if (actual.sig != null) {
            actual.ant.sig = actual.sig;
            actual.sig.ant = actual.ant;
        } else {
            actual.ant.sig = null;
            fin = actual.ant;
        }
        actual = null;
        size--; // Decrementar tamaño
    }
}

    public void actualizarNodo(int pos, Departamento nuevo) {
    if (pos < 0 || pos >= size) { // Validar rango basado en size
        return;
    }
    Nodo actual = ini;
    for (int i = 0; i < pos; i++) {
        actual = actual.sig;
    }
    actual.depa = nuevo;
}

}