/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structure.ListasDobles;

import Model.Departamento;
import java.io.Serializable;

/**
 *
 * @author YUFFRY
 */
public class Nodo implements Serializable{
    public Departamento depa;
    public Nodo ant;
    public Nodo sig;
    public Nodo(Departamento d){
        depa=d;
        ant=sig=null;
    }//fin constructor
}
