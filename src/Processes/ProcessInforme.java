package Processes;

import Model.AsignarPersonal;
import Model.Incidencias;
import Model.Informe;
import Model.Personal;
import Structure.Pilas.PilaAsignacionPersonal;
import Structures.Arboles.ArbolInforme;
import Structures.Arboles.NodoInforme;
import View.UI_Informe;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class ProcessInforme {

    // Método para cargar los JComboBox de Incidencias 
    public static void cargarCombos(UI_Informe inf, PriorityQueue<Incidencias> colaIncidencias) {
        cargarComboBoxConIncidencias(inf.cbxIncidencias, colaIncidencias);
    }

    // Carga el JComboBox de incidencias con los elementos de la cola
    public static void cargarComboBoxConIncidencias(JComboBox<Incidencias> comboBox, PriorityQueue<Incidencias> cola) {
        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos

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
                    String displayText = "" + incidencia.getId();
                    return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        };

        comboBox.setRenderer(renderer);  // Establecer el renderizador para el comboBox
    }

    // Método para mostrar los datos de un informe en los campos del formulario
    public static void MostrarDatosNodo(NodoInforme actual, UI_Informe inf) {
        inf.cbxIncidencias.setSelectedItem(actual.getElemento().getIncidencia());
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
            System.out.println("No se seleccionó ninguna incidencia");
            return null;
        }

        // Find the AsignarPersonal with the matching incidence in the stack
        AsignarPersonal asignacion = pila.getPila().stream()
                .filter(a -> a.getIncidencia().getId() == incidencia.getId())
                .findFirst()
                .orElse(null);

        if (asignacion == null) {
            JOptionPane.showMessageDialog(null, "No está aún un empleado registrado en esta incidencia",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Informe(
                incidencia,
                asignacion,
                Inf.cbxAccionesTomadas.getSelectedItem().toString(),
                Inf.cbxEstado.getSelectedItem().toString(),
                Inf.atxtDescripcion.getText(),
                new java.util.Date() // Fecha de resolución actual
        );
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
