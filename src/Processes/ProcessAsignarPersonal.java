/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import Model.AsignarPersonal;
import Model.Departamento;
import Model.Incidencias;
import Structure.Colas.ColasIncidencias;
import View.UI_Asignacion;
import java.util.PriorityQueue;
import javax.swing.JComboBox;

/**
 *
 * @author Renzo
 */
public class ProcessAsignarPersonal {
    public static AsignarPersonal leer(UI_Asignacion vista){
        AsignarPersonal asignar =new AsignarPersonal();
        
        
        Object selectedItem = vista.cbxIncidencias.getSelectedItem();
        if (selectedItem instanceof Incidencias) {
            asignar.setIncidencia((Incidencias) selectedItem);
        } else {
            System.out.println("Selected item is not a Departamento.");
            // Handle the error appropriately
        }
        
        
        return asignar;
    }
    
    public static void cargarComboBoxConIncidencias(JComboBox<String> comboBox, PriorityQueue<Incidencias> cola) {
    comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
    int id = 1; // Generar un ID incremental si no existe un campo 'id' en Incidencias

    for (Incidencias incidencia : cola) {
        comboBox.addItem(
            String.format("ID: %d - %s - %s - %s", 
                          id, 
                          incidencia.getArea(), 
                          incidencia.getTipoincidencia().getNombre(), 
                          incidencia.getFechaFormat())
        );
        id++;
    }
}

}

    

