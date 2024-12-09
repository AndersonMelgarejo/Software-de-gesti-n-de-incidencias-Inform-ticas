/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import ArrayList.ListaPersonal;
import Controller.LoginController;
import Model.Departamento;
import Model.Incidencias;
import Model.Personal;
import Model.TipoIncidencia;
import Persistence.SaveIncidencias;
import Structure.Colas.ColasIncidencias;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import Structures.Arreglo_TipoIncidencias;
import View.UI_Incidencias;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Renzo
 */
public class ProcessIncidencias {
    public static Incidencias leer(UI_Incidencias vista) {

    
        Incidencias inci = new Incidencias();
        inci.setId(getIdActual());
        inci.setUser(LoginController.usuario);
        inci.setFecha(inci.getFechaActual());
        
        Object selectedItem = vista.cbxDepar.getSelectedItem();
        if (selectedItem instanceof Departamento) {
            inci.setDepartamento((Departamento) selectedItem);
        } else {
            System.out.println("Selected item is not a Departamento.");
            // Handle the error appropriately
        }
        
        inci.setArea(vista.txtArea.getText());
        inci.setDescripcion(vista.txtAinci.getText());
        
        LocalDate fechaSeleccionada = vista.datePickerCustom1.getDate();
        Date fecha = java.util.Date.from(fechaSeleccionada.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        inci.setFechaincidencia(fecha);        

        TipoIncidencia sel = (TipoIncidencia) vista.cbxTipoInci.getSelectedItem();
        if (sel instanceof TipoIncidencia) {
            inci.setTipoincidencia((TipoIncidencia) sel);
        } else {
            System.out.println(sel.getNombre());
            System.out.println("Selected item is not a incidencia.");
            // Handle the error appropriately
        }
        
        Object obj= vista.cbxClientes.getSelectedItem();
        if(obj instanceof Personal){
            inci.setPersonal((Personal)obj);
        }
        
        return inci;
    }

    public static void limpiar(UI_Incidencias vista) {
        vista.txtArea.setText("");
        vista.txtAinci.setText("");

    }
    
    public static void llenar(UI_Incidencias vista, Incidencias incidencia){
        vista.txtAinci.setText(incidencia.getDescripcion());
        vista.cbxDepar.setSelectedItem(incidencia.getDepartamento());
        vista.txtArea.setText(incidencia.getArea());        
    }

    public static void cargarComboBoxDepas(UI_Incidencias vista, ListaDoble lista) {
        // Limpiar el comboBox por si ya tiene elementos
        vista.cbxDepar.removeAllItems();

        // Recorrer la lista doblemente enlazada
        Nodo actual = lista.ini; // Nodo inicial de la lista
        while (actual != null) {
            // Agregar el nombre del departamento al comboBox
            vista.cbxDepar.addItem(actual.depa);
            actual = actual.sig; // Avanzar al siguiente nodo
        }
    }

    public static void cargarComboBox(UI_Incidencias vista, Arreglo_TipoIncidencias arreglo) {
        // Limpiar el comboBox por si ya tiene elementos
        vista.cbxTipoInci.removeAllItems();

        // Recorrer el arreglo de TipoIncidencia y agregar los nombres al comboBox
        for (int i = 0; i < arreglo.cantidad(); i++) {
            TipoIncidencia tipoIncidencia = arreglo.obtener(i);
            vista.cbxTipoInci.addItem(tipoIncidencia);
        }
    }
    
    public static void cargarComboPersonal(UI_Incidencias vista, ListaPersonal listaPersonal) { 
        vista.cbxClientes.removeAllItems(); // Limpiar el combo box antes de agregar los elementos
        listaPersonal.getLista().removeIf(Objects::isNull); // Eliminar elementos null de la lista

        // Recorrer la lista de personal y agregar solo los de cargo "Usuario" al combo box
        for (Personal personal : listaPersonal.getLista()) {
            if ("Cliente".equalsIgnoreCase(personal.getCargo())) { // Comparación insensible a mayúsculas
                vista.cbxClientes.addItem(personal); // Agregar solo los que tienen cargo "Usuario"
            }
        } 
    }

    public static void mostrarInci(UI_Incidencias vista, ColasIncidencias cola) {
        String[] titulos = { "ID", "Usuario Registrador", "Fecha - hora Registrada", "Departamento", 
                             "Area Incidencia", "Descripción", "Fecha Incidencia","Tipo Incidencia",
                             "Prioridad", "Cliente" };
        DefaultTableModel dm = new DefaultTableModel(null, titulos);
        vista.tblIncidencia.setModel(dm);
        int num = 0;
        for (Incidencias inc : cola.getCola()) {
            num++;
            dm.addRow(inc.Registro(num));
        }
    }
    
    public static void anchito(UI_Incidencias vista){
        int anchostabla[]={15,65,100,60,60,40,50,60,60};
        for(int i=0;i<anchostabla.length;i++){
            vista.tblIncidencia.getColumnModel().getColumn(i).setPreferredWidth(anchostabla[i]);
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
    
    // Obtiene el ID más alto de las incidencias registradas
    public static int getMaxId() {
        int maxId = 0;

        // Recuperar la lista de incidencias desde la persistencia
        ColasIncidencias listaIncidencias = SaveIncidencias.Recuperar();

        // Buscar el ID más alto en la lista
        if (listaIncidencias != null) {
            for (Incidencias incidencia : listaIncidencias.getCola()) {
                maxId = Math.max(maxId, incidencia.getId());
            }
        }

        return maxId;
    }

    // Método para obtener el siguiente ID disponible
    public static int getIdActual() {
        return getMaxId() + 1;
    }
    
}
