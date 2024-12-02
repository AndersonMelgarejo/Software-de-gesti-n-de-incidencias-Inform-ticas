package Processes;

import Controller.LoginController;
import Model.Departamento;
import Model.Personal;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import View.UI_Departamentos;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YUFFRY
 */
public class ProcessDepartamento {
    public static Departamento leerDepa(UI_Departamentos vista){
        Departamento depa=new Departamento();
        depa.setUser(LoginController.usuario);
        depa.setNombre(vista.txtNombre.getText());
        depa.setPabellon(vista.cbPabellon.getSelectedItem().toString());
        depa.setPiso(vista.spPiso.getValue().toString());
        depa.setSalon(vista.txtSalon.getText());
        depa.setFecha(new java.util.Date());
        depa.generarAmbiente();
        return depa;
    }
    
    public static void limpiar(UI_Departamentos vista){
        vista.txtNombre.setText("");
        vista.cbPabellon.setSelectedItem(0);
        vista.spPiso.setValue(0);
        vista.txtSalon.setText("");
        vista.datePick.setText("");
    }
    public static void MostrarDepas(UI_Departamentos vista,ListaDoble lista){
        String[] titulos={"ID","Usuario","Nombre","Pabellon","Piso","Salon",
                           "Fecha","Ambiente"};
        DefaultTableModel dm =new DefaultTableModel(null,titulos);
        vista.tblDepas.setModel(dm);
        int num=0;
        Nodo aux=lista.ini;
        while(aux!=null){
            num++;
            dm.addRow(aux.depa.Registro(num));
            aux=aux.sig;
        }//fin while
    }
    public static void llenar(UI_Departamentos vista,Departamento depa){
        vista.txtNombre.setText(depa.getNombre());
        vista.cbPabellon.setSelectedItem(depa.getPabellon());
        vista.spPiso.setValue(Integer.parseInt(depa.getPiso()));
        vista.txtSalon.setText(depa.getSalon());
        vista.datePick.setText(depa.getFechaResFormateada());
    }
}