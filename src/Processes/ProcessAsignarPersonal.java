/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import ArrayList.ListaPersonal;
import Model.AsignarPersonal;
import Model.Incidencias;
import Model.Personal;
import Structure.Pilas.PilaAsignacionPersonal;
import View.UI_Asignacion;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.PriorityQueue;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Renzo
 */
public class ProcessAsignarPersonal {
    public static AsignarPersonal leer(UI_Asignacion vista) {
        AsignarPersonal asignar = new AsignarPersonal();
        LocalDate fechaSeleccionada = vista.datePick.getDate();
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "La fecha seleccionada no puede ser nula", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        asignar.setAsignador(Controller.LoginController.usuario);

        Object selectedItem = vista.cbxIncidencias.getSelectedItem();
        if (selectedItem instanceof Incidencias) {
            asignar.setIncidencia((Incidencias) selectedItem);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una incidencia válida", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Object sel = vista.cbxPersonal.getSelectedItem();
        if (sel instanceof Personal) {
            asignar.setPersonal((Personal) sel);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un personal válido", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Convertir LocalDate a java.util.Date
        Date fecha = java.util.Date
                .from(fechaSeleccionada.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Asignar la fecha al objeto asignar
        asignar.setFecha(fecha);
        asignar.setHora(asignar.getFechaActual());

        String estado = vista.cbxEstado.getSelectedItem().toString();
        if (estado == null || estado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El estado no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        asignar.setEstado(estado); // Estado de la incidencia

        String descripcion = vista.atxtDescripcion.getText();
        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        asignar.setDescripcion(descripcion); // Descripción de la asignación

        // Modificar el estado si es necesario
        asignar.segunEstado();

        return asignar;
    }

    public static void cargarComboBoxConIncidencias(JComboBox<Incidencias> comboBox, PriorityQueue<Incidencias> cola) {
        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        cola.forEach(comboBox::addItem);
    }

    public static void cargarComboPersonal(JComboBox<Personal> comboBox, ListaPersonal listaPersonal) {

        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        listaPersonal.getLista().removeIf(Objects::isNull); // Eliminar elementos null de la lista

        // Recorrer la lista de personal y agregar los usuarios al combo box, excepto
        // los de cargo "Usuario"
        for (Personal personal : listaPersonal.getLista()) {
            if (!"Cliente".equalsIgnoreCase(personal.getCargo())) { // Comparación insensible a mayúsculas
                comboBox.addItem(personal); // Agregar solo el usuario
            }
        }
    }

    public static void MostrarInf(UI_Asignacion inf, PilaAsignacionPersonal pila) {

        String titulos[] = { "ID", "Hora registrada", "Asignador", "Personal", "Fecha solución", "Estado",
                "Descripcion" };

        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        inf.tblInformes.setModel(mt);

        for (int i = 0; i < pila.getPila().size(); i++) {
            mt.addRow(pila.getPila().get(i).Registro(i + 1));
        }

    }

    public static void seleccionarIncidencia(UI_Asignacion vista, Incidencias incidencia) {
        for (int i = 0; i < vista.cbxIncidencias.getItemCount(); i++) {
            Incidencias item = vista.cbxIncidencias.getItemAt(i);
            if (item.getId() == incidencia.getId()) {
                vista.cbxIncidencias.setSelectedItem(item);
                break;
            }
        }
    }

    public static void Llenar(UI_Asignacion vista, AsignarPersonal asi) {
        seleccionarIncidencia(vista, asi.getIncidencia());
        System.out.println(asi.getIncidencia());
        vista.cbxPersonal.setSelectedItem(asi.getPersonal());
        vista.datePick.setText(asi.getFechaFormat());
        vista.cbxEstado.setSelectedItem(asi.getEstado());
        vista.atxtDescripcion.setText(asi.getDescripcion());
    }
}
