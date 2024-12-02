package Structure.Pilas;

import Model.AsignarPersonal;
import Persistence.SaveAsignarPersonal;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class PilaAsignacionPersonal {
    private Stack<AsignarPersonal> pila;
    public PilaAsignacionPersonal(){
        pila = new Stack();
    }
    //método que agrega datos a la pila
    public void Apilar(AsignarPersonal asig){
        pila.push(asig);
        SaveAsignarPersonal.Guardar(this);
    }
    //método que elimina un operario de la pila
    public void Desapilar(){
        if(pila.isEmpty()){
            JOptionPane.showInputDialog("La pila esta vacía");
        }else{
           pila.pop();
           SaveAsignarPersonal.Guardar(this);
        }
    }
    
    public int Cantidad(){ return pila.size(); }
    
    public AsignarPersonal ObtenerOpe(int pos){
       return pila.get(pos);
    }
    
    //ultimo objeto en la pila
    public AsignarPersonal UltimoObjeto(){
        return pila.peek();
    }
    //primer elemento ingresado a la pila
    public AsignarPersonal PrimerObjeto(){
        return pila.firstElement();
    }
    //metodo que busca un operario en la pila
    public AsignarPersonal BuscarOperario(String codbus){
        
       /*for(Operario ope: pila){
            if(codbus.equalsIgnoreCase(ope.getCod()))
                return ope;
        }   */
        
        for(int i=0;i<pila.size();i++){
            if(codbus.equalsIgnoreCase(pila.get(i).Registro(i).toString()))
                return pila.get(i);
        }
        return null;
    }
    
    //metodo que verifica si la pila esta vacia
    public boolean VerificarVacio(){
        return pila.empty();
    }

    public Stack<AsignarPersonal> getPila() {
        return pila;
    }

    public void setPila(Stack<AsignarPersonal> pila) {
        this.pila = pila;
    }
}
