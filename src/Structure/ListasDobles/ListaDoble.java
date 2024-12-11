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

    public static ListaDoble PorNombre(ListaDoble a){
        for(Nodo i=a.ini.sig;i!=null;i=i.sig){
            Departamento value=i.depa;
            Nodo j=i;
            while(j!=a.ini && j.ant.depa.getNombre().
                    compareTo(value.getNombre())>0)
            {
                j.depa = j.ant.depa;
                j=j.ant;
            }
            j.depa = value;
        }
        return a;
    }
    
    public static ListaDoble PorPabellon(ListaDoble a) {
        
        if (a.ini == null || a.ini.sig == null) {
            return a; // La lista está vacía o tiene un solo elemento.
        }

        boolean swapped;
        do {
            swapped = false;
            Nodo current = a.ini; // Inicia desde el primer nodo.

            while (current.sig != null) { // Itera mientras haya un siguiente nodo.
                if (current.depa.getPabellon().compareTo(current.sig.depa.getPabellon()) > 0) {
                    // Intercambiar los datos de los nodos si están desordenados.
                    Departamento temp = current.depa;
                    current.depa = current.sig.depa;
                    current.sig.depa = temp;

                    swapped = true; // Indica que se realizó un intercambio.
                }
                current = current.sig; // Avanza al siguiente nodo.
            }
        } while (swapped); // Repite mientras haya intercambios.

        return a;
    }

    
    public static ListaDoble PorUsuario(ListaDoble a) {
        if (a.ini == null || a.ini.sig == null) {
            return a; // La lista está vacía o tiene un solo elemento.
        }

        for (Nodo i = a.ini; i.sig != null; i = i.sig) { // Recorre hasta el penúltimo nodo.
            Nodo min = i; // Asume que el nodo actual contiene el menor elemento.

            // Encuentra el menor elemento en la sublista no ordenada.
            for (Nodo j = i.sig; j != null; j = j.sig) {
                if (j.depa.getUser().compareTo(min.depa.getUser()) < 0) {
                    min = j; // Actualiza el nodo con el menor elemento.
                }
            }

            // Intercambia los datos del nodo actual con el menor encontrado.
            if (min != i) {
                Departamento temp = i.depa;
                i.depa = min.depa;
                min.depa = temp;
            }
        }

        return a;
    }

    
}