package Processes;

import ArrayList.ListaPersonal;
import Model.Incidencias;
import Model.Informe;
import Model.Personal;
import Structures.Arboles.ArbolInforme;
import Structures.Arboles.NodoInforme;
import View.UI_Informe;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import java.util.PriorityQueue;
import java.util.Objects;

public class ProcessInforme {

    // Método para cargar los JComboBox de Incidencias y Personal
    public static void cargarCombos(UI_Informe inf, PriorityQueue<Incidencias> colaIncidencias, ListaPersonal listaPersonal) {
        cargarComboBoxConIncidencias(inf.cbxIncidencias, colaIncidencias);
        cargarComboPersonal(inf.cbxPersonal, listaPersonal);
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
                    // Mostrar el ID y la descripción en el formato deseado
                    String displayText = "" + incidencia.getId();
                    return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        };

        comboBox.setRenderer(renderer);  // Establecer el renderizador para el comboBox
    }

    // Carga el JComboBox de personal con los elementos de la lista, excluyendo "Usuario"
    public static void cargarComboPersonal(JComboBox<Personal> comboBox, ListaPersonal listaPersonal) {
        comboBox.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        listaPersonal.getLista().removeIf(Objects::isNull); // Eliminar elementos null de la lista

        // Recorrer la lista de personal y agregar los usuarios al combo box, excepto los de cargo "Usuario"
        for (Personal personal : listaPersonal.getLista()) {
            if (!"Usuario".equalsIgnoreCase(personal.getCargo())) { // Comparación insensible a mayúsculas
                comboBox.addItem(personal); // Agregar solo el usuario
            }
        }
    }

    // Método para mostrar los datos de un informe en los campos del formulario
    public static void MostrarDatosNodo(NodoInforme actual, UI_Informe inf) {
        // Asignamos los valores de los JComboBox
        // Incidencia
        Object selectedIncidencia = inf.cbxIncidencias.getSelectedItem();
        if (selectedIncidencia instanceof Incidencias) {
            inf.cbxIncidencias.setSelectedItem(actual.getElemento().getIncidencia()); 
        }

        // Acciones Tomadas
        inf.cbxAccionesTomadas.setSelectedItem(actual.getElemento().getAccionesTomadas());

        // Estado
        inf.cbxEstado.setSelectedItem(actual.getElemento().getEstado());

        // Personal
        Object selectedPersonal = inf.cbxPersonal.getSelectedItem();
        if (selectedPersonal instanceof Personal) {
            inf.cbxPersonal.setSelectedItem(actual.getElemento().getPersonal());  
        }

        // Descripción
        inf.atxtDescripcion.setText(actual.getElemento().getDescripcion());

        // Fecha de resolución
        inf.datePick.setText(actual.getElemento().getFechaResFormateada());
    }

    // Método para leer el informe desde los campos del formulario
    public static Informe LeerInforme(UI_Informe Inf) {
        // Obtener los valores desde los JComboBox en lugar de los JTextFields
        // Incidencia
        Object selectedIncidencia = Inf.cbxIncidencias.getSelectedItem();
        Incidencias incidencia = null;
        if (selectedIncidencia instanceof Incidencias) {
            incidencia = (Incidencias) selectedIncidencia;
        }

        // Personal
        Object selectedPersonal = Inf.cbxPersonal.getSelectedItem();
        Personal personal = null;
        if (selectedPersonal instanceof Personal) {
            personal = (Personal) selectedPersonal;
        }

        // Crear el objeto Informe
        Informe informe = new Informe(
                incidencia, // Usando la incidencia seleccionada
                Inf.cbxAccionesTomadas.getSelectedItem().toString(),
                Inf.cbxEstado.getSelectedItem().toString(),
                personal != null ? personal : null,
                Inf.atxtDescripcion.getText(),
                new java.util.Date()
        );

        return informe;
    }

    // Método para limpiar los campos del formulario
    public static void LimpiaCampos(UI_Informe inf) {
        inf.cbxAccionesTomadas.setSelectedIndex(0);
        inf.cbxEstado.setSelectedIndex(0);
        inf.atxtDescripcion.setText("");
        inf.cbxPersonal.setSelectedIndex(0);
        inf.cbxIncidencias.setSelectedIndex(0);  // Limpiar el ComboBox de incidencias también
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

    // Método para mostrar los informes en la tabla desde el árbol binario
    public static void MostrarDatosEnArbol(ArbolInforme arbol, UI_Informe inf) {
        DefaultTableModel modelo = (DefaultTableModel) inf.tblInformes.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de mostrar los nuevos datos

        // Mostrar los datos en orden
        arbol.MostrarEnOrden(arbol.getRaiz(), modelo);
    }
}
