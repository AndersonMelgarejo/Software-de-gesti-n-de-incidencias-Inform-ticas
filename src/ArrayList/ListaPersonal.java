/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArrayList;
import java.io.Serializable;
import java.util.ArrayList;
import Model.Personal;
/**
 *
 * @author Renzo
 */
public class ListaPersonal implements Serializable{
    private ArrayList<Personal> lista;
    
    public ListaPersonal(){
        lista= new ArrayList();
    }
    public void Agregar(Personal e){
        lista.add(e);        
    }
    public void Actualizar(int pos,Personal actual){
        lista.set(pos, actual);
    }
    public Personal Recuperar(int pos){
        return lista.get(pos);
    }
    public void Eliminar(int pos){
        lista.remove(pos);
    }
    public int Cantidad(){return lista.size(); }

    public ArrayList<Personal> getLista() {        return lista;    }
    public void setLista(ArrayList<Personal> lista) {        this.lista = lista;    }
    
    
}
