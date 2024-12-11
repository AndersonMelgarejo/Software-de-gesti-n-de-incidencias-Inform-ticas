package Processes;

import Controller.LoginController;
import Model.Departamento;
import Model.Personal;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import View.UI_Departamentos;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YUFFRY
 */
public class ProcessDepartamento {
    public static Departamento leerDepa(UI_Departamentos vista){
        
        if(vista.txtNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }        
        if(vista.cbPabellon.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(vista, "Selecione un pabellon", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(vista.spPiso.getValue().equals(0)){
            JOptionPane.showMessageDialog(vista, "Selecione un piso", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String texto = vista.txtSalon.getText();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            try {
                int numero = Integer.parseInt(texto); // Intenta convertir el texto a número               
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        if(vista.datePick.getText().isEmpty()){
            JOptionPane.showMessageDialog(vista, "Selecione una fecha", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        Departamento depa=new Departamento();
        depa.setUser(LoginController.usuario);
        depa.setNombre(vista.txtNombre.getText());
        depa.setPabellon(vista.cbPabellon.getSelectedItem().toString());
        depa.setPiso(vista.spPiso.getValue().toString());
        depa.setSalon(vista.txtSalon.getText());
        LocalDate fechaSeleccionada = vista.datePick.getDate();
        Date fecha = java.util.Date.from(fechaSeleccionada.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        depa.setFecha(fecha);  
        depa.generarAmbiente();
        return depa;
    }
    
    public static void limpiar(UI_Departamentos vista){        
        vista.txtNombre.setText("");
        vista.cbPabellon.setSelectedIndex(0);
        vista.spPiso.setValue(0);
        vista.txtSalon.setText("");
        vista.datePick.setText("");
    }
    public static void MostrarDepas(UI_Departamentos fd,ListaDoble lista){
        String[] titulos={"ID","Usuario","Nombre","Pabellon","Piso",
                           "Salon","Fecha Registro","Ambiente"};
        DefaultTableModel dm =new DefaultTableModel(null,titulos);
        fd.tblDepas.setModel(dm);
        int num=0;
        Nodo aux=lista.ini;
        while(aux!=null){
            num++;
            dm.addRow(aux.depa.Registro(num));
            aux=aux.sig;
        }//fin while
    }//fin de mostrar
    public static void llenar(UI_Departamentos vista,Departamento depa){
        vista.txtNombre.setText(depa.getNombre());
        vista.cbPabellon.setSelectedItem(depa.getPabellon());
        vista.spPiso.setValue(Integer.parseInt(depa.getPiso()));
        vista.txtSalon.setText(depa.getSalon());
        vista.datePick.setText(depa.getFechaResFormateada());
    }
}