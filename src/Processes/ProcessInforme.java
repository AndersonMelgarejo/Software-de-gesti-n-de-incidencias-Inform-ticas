package Processes;

import Model.AsignarPersonal;
import Model.Incidencias;
import Model.Informe;
import Model.Personal;
import Structure.Pilas.PilaAsignacionPersonal;
import Structures.Arboles.ArbolInforme;
import Structures.Arboles.NodoInforme;
import View.UI_Asignacion;
import View.UI_Informe;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class ProcessInforme {

    // Carga el JComboBox de incidencias con los elementos de la cola
    public static void cargarComboBoxConIncidencias(JComboBox<Incidencias> comboBox, PriorityQueue<Incidencias> cola) {
        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        cola.forEach(comboBox::addItem);
    }

    public static void seleccionarIncidencia(UI_Informe vista, Incidencias incidencia) {
        for (int i = 0; i < vista.cbxIncidencias.getItemCount(); i++) {
            Incidencias item = vista.cbxIncidencias.getItemAt(i);
            if (item.getId() == incidencia.getId()) {
                vista.cbxIncidencias.setSelectedItem(item);
                break;
            }
        }
    }

    // Método para mostrar los datos de un informe en los campos del formulario
    public static void MostrarDatosNodo(NodoInforme actual, UI_Informe inf) {
        seleccionarIncidencia(inf, actual.getElemento().getIncidencia());
        inf.cbxAccionesTomadas.setSelectedItem(actual.getElemento().getAccionesTomadas());
        inf.cbxEstado.setSelectedItem(actual.getElemento().getEstado());
        inf.atxtDescripcion.setText(actual.getElemento().getDescripcion());
        inf.datePick.setText(actual.getElemento().getFechaResFormateada());
    }

    // Método para leer el informe desde los campos del formulario
    public static Informe LeerInforme(UI_Informe Inf, PilaAsignacionPersonal pila) {
        // Get the selected incidence
        Incidencias incidencia = (Incidencias) Inf.cbxIncidencias.getSelectedItem();

        if (incidencia == null) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna incidencia", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Find the AsignarPersonal with the matching incidence in the stack
        AsignarPersonal asignacion = pila.getPila().stream()
                .filter(a -> a.getIncidencia().getId() == incidencia.getId())
                .findFirst()
                .orElse(null);

        if (asignacion == null) {
            JOptionPane.showMessageDialog(null, "No está aún un empleado registrado en esta incidencia", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String accionesTomadas = Inf.cbxAccionesTomadas.getSelectedItem() != null
                ? Inf.cbxAccionesTomadas.getSelectedItem().toString()
                : "";
        String estado = Inf.cbxEstado.getSelectedItem() != null ? Inf.cbxEstado.getSelectedItem().toString() : "";
        String descripcion = Inf.atxtDescripcion.getText().trim();

        if (accionesTomadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Las acciones tomadas no pueden estar vacías", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (estado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El estado no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        LocalDate fecha = Inf.datePick.getDate();

        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "La fecha seleccionada no puede ser nula", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // convertir LocalDate a java.util.Date
        Date fechaRes = java.util.Date
                .from(fecha.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

        return new Informe(
                incidencia,
                asignacion,
                accionesTomadas,
                estado,
                descripcion,
                fechaRes);
    }

    // Método para limpiar los campos del formulario
    public static void LimpiaCampos(UI_Informe inf) {
        inf.cbxAccionesTomadas.setSelectedIndex(0);
        inf.cbxEstado.setSelectedIndex(0);
        inf.atxtDescripcion.setText("");
        inf.cbxIncidencias.setSelectedIndex(0);
    }

    // Método para limpiar la tabla de informes
    public static void LimpiarTabla(DefaultTableModel modtabla) {
        modtabla.setRowCount(0);
    }

    // Método para buscar un informe por ID en el árbol
    public static Informe BuscarInformePorID(ArbolInforme arbol, int id) {
        NodoInforme nodoEncontrado = arbol.BuscarPorID(id);
        return nodoEncontrado != null ? nodoEncontrado.getElemento() : null;
    }

    // Métodos para ordenar los informes usando Bubble Sort
    public static void ordenarPorID(ArbolInforme arbol, UI_Informe inf) {
        List<Informe> informes = obtenerInformesEnLista(arbol.getRaiz());
        bubbleSort(informes, (a, b) -> Integer.compare(a.getIncidencia().getId(), b.getIncidencia().getId()));
        actualizarTabla(inf, informes);
    }

    public static void ordenarPorEstado(ArbolInforme arbol, UI_Informe inf) {
        List<Informe> informes = obtenerInformesEnLista(arbol.getRaiz());
        bubbleSort(informes, (a, b) -> a.getEstado().compareToIgnoreCase(b.getEstado()));
        actualizarTabla(inf, informes);
    }

    public static void ordenarPorFecha(ArbolInforme arbol, UI_Informe inf) {
        List<Informe> informes = obtenerInformesEnLista(arbol.getRaiz());
        bubbleSort(informes, (a, b) -> a.getFechaRes().compareTo(b.getFechaRes()));
        actualizarTabla(inf, informes);
    }

    private static List<Informe> obtenerInformesEnLista(NodoInforme nodo) {
        List<Informe> informes = new ArrayList<>();
        if (nodo != null) {
            informes.addAll(obtenerInformesEnLista(nodo.getIzq()));
            informes.add(nodo.getElemento());
            informes.addAll(obtenerInformesEnLista(nodo.getDer()));
        }
        return informes;
    }

    private static void bubbleSort(List<Informe> informes, java.util.Comparator<Informe> comparator) {
        int n = informes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(informes.get(j), informes.get(j + 1)) > 0) {
                    Informe temp = informes.get(j);
                    informes.set(j, informes.get(j + 1));
                    informes.set(j + 1, temp);
                }
            }
        }
    }

    private static void actualizarTabla(UI_Informe inf, List<Informe> informes) {
        DefaultTableModel modelo = (DefaultTableModel) inf.tblInformes.getModel();
        modelo.setRowCount(0);
        for (Informe informe : informes) {
            modelo.addRow(informe.getRegistro());
        }
    }
}
