
package Structures.ListasEnlazadas;

import Model.Informe;
import java.io.Serializable;


public class Nodo implements Serializable{
    public Informe inf;
    public Nodo sig;
    public Nodo(Informe e){
        inf=e;
        sig=null;
    }
}
