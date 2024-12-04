
package Structures.Arboles;

import Model.Informe;
import java.io.Serializable;
public class NodoInforme implements Serializable{
    private Informe elemento;
    private NodoInforme Izq;
    private NodoInforme Der;
    public NodoInforme(Informe elem){
        elemento=elem;
        Izq=Der=null;
    }
    public Informe getElemento() {       return elemento;    }
    public void setElemento(Informe elemento) {        this.elemento = elemento; }
    public NodoInforme getIzq() {        return Izq;    }
    public void setIzq(NodoInforme Izq) {        this.Izq = Izq;    }
    public NodoInforme getDer() {        return Der;    }
    public void setDer(NodoInforme Der) {        this.Der = Der;    }
    
}//fin de la clase

