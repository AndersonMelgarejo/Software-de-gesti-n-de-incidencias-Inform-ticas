/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import Controller.LoginController;
import Model.Departamento;
import Model.Incidencias;
import Model.TipoIncidencia;
import Structure.Colas.ColasIncidencias;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import Structures.Arreglo_TipoIncidencias;
import View.UI_Incidencias;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Renzo
 */
public class ProcessIncidencias {
    public static Incidencias leer(UI_Incidencias vista){
        Incidencias inci=new Incidencias();
        inci.setUser(LoginController.usuario);
        Object selectedItem = vista.cbxDepar.getSelectedItem();
    if (selectedItem instanceof Departamento) {
        inci.setDepartamento((Departamento) selectedItem);
    } else {
        System.out.println("Selected item is not a Departamento.");
        // Handle the error appropriately
    }
        inci.setArea(vista.txtArea.getText());
        inci.setDescripcion(vista.txtAinci.getText());
        inci.setFecha(new java.util.Date());
        
        Object sel = vista.cbxTipoInci.getSelectedItem();
    if (selectedItem instanceof TipoIncidencia) {
        inci.setTipoincidencia((TipoIncidencia) sel);
    } else {
        System.out.println("Selected item is not a Departamento.");
        // Handle the error appropriately
    }
                
        return inci;
    }
    public static void limpiar(UI_Incidencias vista){
        vista.txtArea.setText("");
        vista.txtAinci.setText("");
        
    }
    public static void cargarComboBox(UI_Incidencias vista, ListaDoble lista) {
        // Limpiar el comboBox por si ya tiene elementos
        vista.cbxDepar.removeAllItems();

        // Recorrer la lista doblemente enlazada
        Nodo actual = lista.ini; // Nodo inicial de la lista
        while (actual != null) {
            // Agregar el nombre del departamento al comboBox
            vista.cbxDepar.addItem(actual.depa.getNombre());
            actual = actual.sig; // Avanzar al siguiente nodo
        }
    }
    public static void cargarComboBox(UI_Incidencias vista, Arreglo_TipoIncidencias arreglo) {
        // Limpiar el comboBox por si ya tiene elementos
        vista.cbxTipoInci.removeAllItems();
        
        // Recorrer el arreglo de TipoIncidencia y agregar los nombres al comboBox
        for (int i = 0; i < arreglo.cantidad(); i++) {
            TipoIncidencia tipoIncidencia = arreglo.obtener(i);
            vista.cbxTipoInci.addItem(tipoIncidencia.getNombre());
        }
    }
    public static void mostrarInci(UI_Incidencias vista,ColasIncidencias cola){
         String[] titulos={"ID","Usuario","Departamento","Area incidencia","Fecha","Tipo incidencia",
                           "Prioridad"};
        DefaultTableModel dm =new DefaultTableModel(null,titulos);
        vista.tblIncidencia.setModel(dm);
        int num=0;
        for(Incidencias inc: cola.getCola()){
            num++;
            dm.addRow(inc.Registro(num));
        }
    }
    public static int obtenerNivelPrioridad(TipoIncidencia tipoIncidencia) {
    if (tipoIncidencia == null || tipoIncidencia.getNivel() == null) {
        return 0; // Nivel por defecto si no hay información
    }
    switch (tipoIncidencia.getNivel().toLowerCase()) {
        case "baja":
            return 1;
        case "media":
            return 2;
        case "alta":
            return 3;
        default:
            return 0; // Nivel por defecto si no se reconoce
    }
}

}
