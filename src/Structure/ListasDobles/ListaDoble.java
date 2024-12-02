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
    public ListaDoble(){
        ini=fin=null;
    }//fin constructor
    //método que inserta un Nodo al final de la lista
    public void InsertarAlFinal(Departamento depa){
        Nodo nuevo =  new Nodo(depa);
        if(ini==null){
            ini=fin=nuevo;
        }else{
            nuevo.ant=fin;
            fin.sig=nuevo;
        }
        fin=nuevo;
        fin.sig=null;
    }//fin insertar

    
    //método que elimina un nodo
    public void EliminarNodo(Nodo actual){
        if(actual!=null){
            if(actual==ini){
                ini=actual.sig;
                if(actual.sig!=null) actual.sig.ant=null;
            }
            else if(actual.sig!=null){
                    actual.ant.sig=actual.sig;
                    actual.sig.ant=actual.ant;
                  }else{
                      actual.ant.sig=null;
                      fin=actual.ant;
                  } 
            actual=null;
        }//inf
    }//fin eliminar
}
