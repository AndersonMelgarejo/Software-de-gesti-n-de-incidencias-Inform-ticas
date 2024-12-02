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
public class ComparadorIncidencia implements Serializable,Comparator<Incidencias>{
    @Override
    public int compare(Incidencias o1, Incidencias o2) {
       if(o1.segunNivel()>o2.segunNivel())
           return 1;
       else if(o1.segunNivel()<o2.segunNivel())
           return -1;
       else return 0;
    } 
}
