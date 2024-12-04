/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import javax.swing.table.DefaultTableModel;

import Model.Incidencias;
import Structure.Colas.ColasIncidencias;
import Structures.ListasEnlazadas.ListaEnlazada;
import View.UI_Home;
import View.UI_Incidencias;
import View.UI_Informe;

/**
 *
 * @author franc
 */
public class ProcessHome {
  public static void mostrarInci(UI_Home vista, ColasIncidencias cola) {
    String[] titulos = { "ID", "Usuario", "Departamento", "Area incidencia", "Fecha", "Descripcion", "Tipo incidencia",
        "Prioridad" };
    DefaultTableModel dm = new DefaultTableModel(null, titulos);
    vista.jtHome.setModel(dm);
    int num = 0;
    for (Incidencias inc : cola.getCola()) {
      num++;
      dm.addRow(inc.Registro(num));
    }
  }

  // mostrar por intervalo de fechas
  public static void mostrarInciFechas(UI_Home vista, ColasIncidencias cola, String fechaInicio, String fechaFin) {
    String[] titulos = { "ID", "Usuario", "Departamento", "Area incidencia", "Fecha", "Descripcion", "Tipo incidencia",
        "Prioridad" };
    DefaultTableModel dm = new DefaultTableModel(null, titulos);
    vista.jtHome.setModel(dm);
    int num = 0;
    for (Incidencias inc : cola.getCola()) {
      if (inc.getFechaFormat().compareTo(fechaInicio) >= 0 && inc.getFechaFormat().compareTo(fechaFin) <= 0) {
        num++;
        dm.addRow(inc.Registro(num));
      }
    }
  }

}
