/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structure.Colas;

import Model.Incidencias;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Renzo
 */
public class ComparadorTipoIncidencia implements Serializable, Comparator<Incidencias> {
    @Override
    public int compare(Incidencias o1, Incidencias o2) {
        return o1.getTipoincidencia().getNombre().compareToIgnoreCase(o2.getTipoincidencia().getNombre());
        // Compara los nombres de tipo de incidencia en orden alfabético (ignorando mayúsculas)
    }
}