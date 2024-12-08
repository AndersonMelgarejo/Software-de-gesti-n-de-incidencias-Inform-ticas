/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import javax.swing.table.DefaultTableModel;

import Model.Incidencias;
import Structure.Colas.ColasIncidencias;
import View.UI_Home;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

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
  public static void mostrarInciFechas(UI_Home vista, ColasIncidencias cola, Date fechaInicio, Date fechaFin) {
    String[] titulos = { "ID", "Usuario", "Departamento", "Area incidencia", "Fecha", "Descripcion", "Tipo incidencia",
        "Prioridad" };
    DefaultTableModel dm = new DefaultTableModel(null, titulos);
    vista.jtHome.setModel(dm);
    int num = 0;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    for (Incidencias inc : cola.getCola()) {
      try {
        Date fechaIncidencia = formato.parse(inc.getFechaFormat());
        if (!fechaIncidencia.before(fechaInicio) && !fechaIncidencia.after(fechaFin)) {
          num++;
          dm.addRow(inc.Registro(num));
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
  }

}
