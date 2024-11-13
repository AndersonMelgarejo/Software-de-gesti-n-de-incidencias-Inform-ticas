/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import Model.Informe;
import Structures.ListasEnlazadas.*;
import View.UI_Informe;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProcessInforme {

    public static Informe LeerInforme(UI_Informe Inf) {
        

        Informe informe = new Informe();
        informe.setAccionesTomadas(Inf.cbxAccionesTomadas.getSelectedItem().toString());
        informe.setFechaRes(new java.util.Date());
        informe.setDescripcion(Inf.atxtDescripcion.getText());
        informe.setEstado(Inf.cbxEstado.getSelectedItem().toString());
        
        return informe;
    }

    public static void LimpiaCampos(UI_Informe inf) {

        inf.cbxIDIncidencia.setSelectedIndex(0);
        inf.cbxAccionesTomadas.setSelectedIndex(0);
        inf.cbxEstado.setSelectedIndex(0);
        inf.atxtDescripcion.setText("");
    }

    public static void MostrarInf(UI_Informe inf, ListaEnlazada lista) {

        String titulos[] = {"ID", "Acciones Tomadas", "Estado", "Descripción", "fecha"};

        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        inf.tblInformes.setModel(mt);

        int num = 0;
        for (Nodo aux = lista.getIni(); aux != null; aux = aux.sig) {
            num++;
            mt.addRow(aux.inf.RegistroInforme(num));
        }

    }

}
