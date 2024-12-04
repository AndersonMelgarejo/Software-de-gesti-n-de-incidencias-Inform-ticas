package Structure.Pilas;

import Model.AsignarPersonal;
import Persistence.SaveAsignarPersonal;
import java.io.Serializable;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class PilaAsignacionPersonal implements Serializable{
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
    public AsignarPersonal BuscarOperario(String codbus) {
        if (codbus == null || codbus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un código válido.");
            return null; // Si el código de búsqueda es nulo o vacío, no hacemos nada
        }
    
        for (int i = 0; i < pila.size(); i++) {
            // Convertimos el ID de la incidencia a String para hacer la comparación
            String incidenciaId = String.valueOf(pila.get(i).getIncidencia().getId());
        
            // Compara el ID de la incidencia con el valor de 'codbus'
            if (codbus.equalsIgnoreCase(incidenciaId)) {
                return pila.get(i); // Devuelve el objeto AsignarPersonal que contiene la incidencia con ese ID
            }
        }
    
        // Si no se encuentra, muestra un mensaje de advertencia y retorna null
        JOptionPane.showMessageDialog(null, "Operario no encontrado con el código: " + codbus);
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
