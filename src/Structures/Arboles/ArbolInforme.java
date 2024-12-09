package Structures.Arboles;

import Model.Informe;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;

public class ArbolInforme implements Serializable {

    private NodoInforme Raiz;

    public NodoInforme getRaiz() {
        return Raiz;
    }

    public void setRaiz(NodoInforme Raiz) {
        this.Raiz = Raiz;
    }

    public ArbolInforme() {
        Raiz = null;
    }

// Método que agrega un nodo al árbol, asegurando que no se registre más de una vez
    public NodoInforme Agregar(NodoInforme nodo, Informe elem) {
        if (nodo == null) {
            return new NodoInforme(elem);
        }

        // Comprobar si el informe ya existe (comparando el ID de la incidencia)
        if (nodo.getElemento().getIncidencia().getId() == elem.getIncidencia().getId()) {
            // Si ya existe, no agregar y devolver el nodo original (sin modificaciones)
            return nodo;
        }

        // Comparar usando el ID de la incidencia (elem.getIncidencia().getId())
        if (Integer.compare(elem.getIncidencia().getId(), nodo.getElemento().getIncidencia().getId()) > 0) {
            nodo.setDer(Agregar(nodo.getDer(), elem));
        } else {
            nodo.setIzq(Agregar(nodo.getIzq(), elem));
        }
        return nodo;
    }

    // Método que muestra los datos del árbol en un DefaultTableModel
    public void MostrarEnOrden(NodoInforme nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            MostrarEnOrden(nodo.getIzq(), modelo);
            modelo.addRow(nodo.getElemento().getRegistro());
            MostrarEnOrden(nodo.getDer(), modelo);
        }
    }

    // Método que busca un informe por el ID de la incidencia
    public NodoInforme BuscarPorID(int id) {
        NodoInforme aux = Raiz;
        while (aux != null) {
            // Comparar con el ID de la incidencia (aux.getElemento().getIncidencia().getId())
            if (aux.getElemento().getIncidencia().getId() == id) {
                return aux;
            } else if (id > aux.getElemento().getIncidencia().getId()) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return null; // No encontrado
    }

    // Método que busca el mayor elemento en el subárbol izquierdo
    public NodoInforme BuscarMayorIzquierda(NodoInforme nodo) {
        if (nodo != null) {
            while (nodo.getDer() != null) {
                nodo = nodo.getDer();
            }
        }
        return nodo;
    }

    // Método que elimina el mayor elemento del subárbol izquierdo
    public NodoInforme EliminarMayorIzquierda(NodoInforme nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.getDer() != null) {
            nodo.setDer(EliminarMayorIzquierda(nodo.getDer()));
            return nodo;
        }
        return nodo.getIzq();
    }

    // Método que elimina un nodo del árbol
    public NodoInforme Eliminar(NodoInforme nodo, int id) {
        if (nodo == null) {
            return null;
        }

        // Comparar usando el ID de la incidencia (nodo.getElemento().getIncidencia().getId())
        if (id < nodo.getElemento().getIncidencia().getId()) {
            nodo.setIzq(Eliminar(nodo.getIzq(), id));
        } else if (id > nodo.getElemento().getIncidencia().getId()) {
            nodo.setDer(Eliminar(nodo.getDer(), id));
        } else if (nodo.getIzq() != null && nodo.getDer() != null) {
            nodo.setElemento(BuscarMayorIzquierda(nodo.getIzq()).getElemento());
            nodo.setIzq(EliminarMayorIzquierda(nodo.getIzq()));
        } else {
            nodo = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();
        }
        return nodo;
    }
}
