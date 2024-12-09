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
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author franc
 */
public class ProcessHome {
  public static void mostrarInci(UI_Home vista, ColasIncidencias cola) {
    String[] titulos = { "ID", "Usuario Registrador", "Fecha - hora Registrada", "Departamento",
        "Area Incidencia", "Descripción", "Fecha Incidencia", "Tipo Incidencia",
        "Prioridad", "Cliente" };
    DefaultTableModel dm = new DefaultTableModel(null, titulos);
    vista.jtHome.setModel(dm);
    int num = 0;
    for (Incidencias inc : cola.getCola()) {
      num++;
      dm.addRow(inc.Registro(num));
    }
    int anchostabla[] = { 15, 65, 100, 60, 60, 40, 50, 60, 60 };
    for (int i = 0; i < anchostabla.length; i++) {
      vista.jtHome.getColumnModel().getColumn(i).setPreferredWidth(anchostabla[i]);
    }
  }

  // mostrar por intervalo de fechas
  public static void mostrarInciFechas(UI_Home vista, ColasIncidencias cola, Date fechaInicio, Date fechaFin) {
    String[] titulos = { "ID", "Usuario Registrador", "Fecha - hora Registrada", "Departamento",
        "Area Incidencia", "Descripción", "Fecha Incidencia", "Tipo Incidencia",
        "Prioridad", "Cliente" };
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
    int anchostabla[] = { 15, 65, 100, 60, 60, 40, 50, 60, 60 };
    for (int i = 0; i < anchostabla.length; i++) {
      vista.jtHome.getColumnModel().getColumn(i).setPreferredWidth(anchostabla[i]);
    }
  }

  // mostrar total de incidencias y el porcentaje de incidencias en relación al
  // mes anterior
  public static void mostrarTotalInci(UI_Home vista, ColasIncidencias cola) {
    // vista.lblInciPercent.setText("+0%");
    LocalDate fechaActual = LocalDate.now();
    LocalDate primerDiaActual = fechaActual.withDayOfMonth(1);
    LocalDate primerDiaMesAnterior = primerDiaActual.minusMonths(1);

    int mesActualIncidencias = 0;
    int mesAnteriorIncidencias = 0;

    for (Incidencias inc : cola.getCola()) {
      LocalDate fechaIncidencia = inc.getFechaincidencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      if (!fechaIncidencia.isBefore(primerDiaActual)) {
        mesActualIncidencias++;
      } else if (!fechaIncidencia.isBefore(primerDiaMesAnterior)) {
        mesAnteriorIncidencias++;
      }
    }

    // Mostrar total histórico de incidencias
    vista.lblInci.setText("" + cola.getCola().size());

    // Calcular relación de incidencias con el mes anterior
    if (mesAnteriorIncidencias > 0) {
      int diferencia = mesActualIncidencias - mesAnteriorIncidencias;
      int porcentaje = Math.abs(diferencia) * 100 / mesAnteriorIncidencias; // Obtener valor absoluto para el cálculo
      String signo = diferencia >= 0 ? "+" : "-";
      vista.lblInciPercent.setText(signo + porcentaje + "%");
    } else {
      // Caso donde no hay incidencias en el mes anterior
      if (mesActualIncidencias > 0) {
        vista.lblInciPercent.setText("+100%"); // Incremento absoluto
      } else {
        vista.lblInciPercent.setText("+0%"); // Sin cambios
      }
    }

    System.out.println("Incidencias mes actual: " + mesActualIncidencias);
    System.out.println("Incidencias mes anterior: " + mesAnteriorIncidencias);

  }

}