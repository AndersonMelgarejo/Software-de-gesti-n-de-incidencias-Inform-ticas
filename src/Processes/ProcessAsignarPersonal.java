/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import ArrayList.ListaPersonal;
import Model.AsignarPersonal;
import Model.Departamento;
import Model.Incidencias;
import Model.Personal;
import Structure.Colas.ColasIncidencias;
import Structure.Pilas.PilaAsignacionPersonal;
import Styles.DatePickerCustom;
import View.UI_Asignacion;
import View.UI_Informe;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.PriorityQueue;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Renzo
 */
public class ProcessAsignarPersonal {
    public static AsignarPersonal leer(UI_Asignacion vista){
        AsignarPersonal asignar =new AsignarPersonal();
        DatePickerCustom datePickerCustom = new DatePickerCustom();
        LocalDate fechaSeleccionada = vista.datePick.getDate(); 
        asignar.setAsignador(Controller.LoginController.usuario);
        
        
        
        Object selectedItem = vista.cbxIncidencias.getSelectedItem();
        if (selectedItem instanceof Incidencias) {
            asignar.setIncidencia((Incidencias) selectedItem);
        }
        
        Object sel= vista.cbxPersonal.getSelectedItem();
        if(sel instanceof Personal){
            asignar.setPersonal((Personal)sel);
        }
         // Convertir LocalDate a java.util.Date
            Date fecha = java.util.Date.from(fechaSeleccionada.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

            // Asignar la fecha al objeto asignar
            asignar.setFecha(fecha);        
        asignar.setHora(LocalTime.now());
        
        asignar.setEstado(vista.cbxEstado.getSelectedItem().toString()); // Estado de la incidencia
        asignar.setDescripcion(vista.atxtDescripcion.getText()); // Descripción de la asignación

        // Modificar el estado si es necesario
        asignar.segunEstado();
        
        return asignar;
    }
    
    public static void cargarComboBoxConIncidencias(JComboBox<Incidencias> comboBox, PriorityQueue<Incidencias> cola) {
        comboBox.removeAllItems();  // Limpiar el combo box antes de agregar los elementos

        // Asignar el id a cada incidencia de la cola
        int id = 1;
        for (Incidencias incidencia : cola) {
            incidencia.setId(id);  // Asignar el id dinámicamente
            comboBox.addItem(incidencia);  // Agregar el objeto completo a la lista
            id++;
        }

        // Crear un renderizador para mostrar correctamente el ID y la descripción en el JComboBox
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Incidencias) {
                    Incidencias incidencia = (Incidencias) value;
                    // Mostrar el ID y la descripción en el formato deseado
                    String displayText = "" + incidencia.getId();
                    return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        };

        comboBox.setRenderer(renderer);  // Establecer el renderizador para el comboBox
    }

    public static void cargarComboPersonal(JComboBox<Personal> comboBox, ListaPersonal listaPersonal){
       
        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        listaPersonal.getLista().removeIf(Objects::isNull); // Eliminar elementos null de la lista

        // Recorrer la lista de personal y agregar los usuarios al combo box, excepto los de cargo "Usuario"
        for (Personal personal : listaPersonal.getLista()) {
            if (!"Cliente".equalsIgnoreCase(personal.getCargo())) { // Comparación insensible a mayúsculas
                comboBox.addItem(personal); // Agregar solo el usuario
            }
        } 
    }
    
    public static void MostrarInf(UI_Asignacion inf, PilaAsignacionPersonal pila) {

        String titulos[] = {"ID","Hora registrada", "Asignador", "Personal", "Fecha solución","Estado","Descripcion"};

        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        inf.tblInformes.setModel(mt);

        for(int i=0;i<pila.getPila().size();i++){
            mt.addRow(pila.getPila().get(i).Registro(i+1));
        }

    }
    public static void Llenar(UI_Asignacion vista,AsignarPersonal asi){
        vista.cbxIncidencias.setSelectedItem(asi.getIncidencia());
        vista.cbxPersonal.setSelectedItem(asi.getPersonal());
        vista.datePick.setText(asi.getFechaFormat());
        vista.cbxEstado.setSelectedItem(asi.getEstado());
        vista.atxtDescripcion.setText(asi.getDescripcion());
    }
}
