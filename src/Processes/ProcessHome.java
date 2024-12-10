/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import Model.AsignarPersonal;
import javax.swing.table.DefaultTableModel;

import Model.Incidencias;
import Structure.Colas.ColasIncidencias;
import Structure.Pilas.PilaAsignacionPersonal;
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

  public static void mostrarInciEstado(UI_Home vista, PilaAsignacionPersonal pila) {
    String[] titulos = { "ID", "Hora Registrada", "Asignador",
        "personal asignado", "Fecha solución",
        "Estado", "Descripcion" };
    DefaultTableModel dm = new DefaultTableModel(null, titulos);
    vista.jtEstado.setModel(dm);
    for (int i = 0; i < pila.getPila().size(); i++) {
      dm.addRow(pila.getPila().get(i).Registro(i + 1));
    }
    int anchostabla[] = { 5, 30, 40, 50, 60, 60, 75 };
    for (int i = 0; i < anchostabla.length; i++) {
      vista.jtEstado.getColumnModel().getColumn(i).setPreferredWidth(anchostabla[i]);
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

  public static void mostrarTotalSol(UI_Home vista, PilaAsignacionPersonal pila, ColasIncidencias cola) {
    int totalIncidencias = cola.getCola().size();
    int totalSoluciones = pila.getPila().size();
    int porcentajeSoluciones;

    // Mostrar total histórico de soluciones
    vista.lblSoluciones.setText("" + totalSoluciones);

    // Validar y calcular porcentaje
    if (totalIncidencias == 0) {
      // Caso: Sin incidencias registradas
      porcentajeSoluciones = 0; // No hay relación porcentual
    } else if (totalSoluciones > totalIncidencias) {
      // Caso: Más soluciones que incidencias
      int excesoSoluciones = totalSoluciones - totalIncidencias;
      porcentajeSoluciones = -(excesoSoluciones * 100 / totalIncidencias); // Porcentaje negativo
    } else {
      // Calcular porcentaje de soluciones restantes
      int solucionesRestantes = totalIncidencias - totalSoluciones;
      porcentajeSoluciones = (solucionesRestantes * 100) / totalIncidencias;
    }

    // Mostrar el porcentaje con el símbolo correspondiente
    vista.lblSolucionesPercent.setText(porcentajeSoluciones + "%");
  }

  public static void mostrarTotalInciEstado(UI_Home vista, PilaAsignacionPersonal pila) {
    int totalIncidencias = pila.getPila().size();
    int totalSoluciones = 0;
    int totalAtendido = 0;
    int totalEnProceso = 0;
    int totalDerivado = 0;

    // Contar incidencias por estado
    for (AsignarPersonal asignar : pila.getPila()) {
      switch (asignar.getEstado()) {
        case "ATENDIDO":
          totalAtendido++;
          break;
        case "EN PROCESO":
          totalEnProceso++;
          break;
        case "DERIVADO":
          totalDerivado++;
          break;
      }
    }

    // Calcular total de soluciones (resueltas o derivadas)
    totalSoluciones = totalAtendido + totalDerivado;
    System.out.println("Total de incidencias: " + totalIncidencias);
    System.out.println("Total de soluciones: " + totalSoluciones);
    System.out.println("Total de incidencias atendidas: " + totalAtendido);
    System.out.println("Total de incidencias en proceso: " + totalEnProceso);

    // Mostrar total histórico de soluciones en lblSol
    vista.lblSol.setText("" + totalSoluciones);

    // Calcular porcentaje de incidencias atendidas
    int porcentajeAtendido = totalIncidencias > 0
        ? (totalAtendido * 100) / totalIncidencias
        : 0;

    // Mostrar porcentaje en lblSolPercent
    vista.lblSolPercent.setText(porcentajeAtendido + "%");
  }

}
