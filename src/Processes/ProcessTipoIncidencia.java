package Processes;

import javax.swing.table.DefaultTableModel;

import Model.TipoIncidencia;
import Structures.Arreglo_TipoIncidencias;
import View.UI_Categorias;

public class ProcessTipoIncidencia {

  public static TipoIncidencia LeerTipoIncidencia(UI_Categorias vista) {
    TipoIncidencia tipoIncidencia = new TipoIncidencia();
    tipoIncidencia.setNombre(vista.txtName.getText());
    tipoIncidencia.setNivel(vista.cbPrioridad.getSelectedItem().toString());
    tipoIncidencia.setCategoria(vista.cbCategoria.getSelectedItem().toString());
    tipoIncidencia.setDescripcion(vista.atxtDescripcion.getText());
    tipoIncidencia.setFechaMod(new java.util.Date());
    return tipoIncidencia;
  }

  public static void LimpiaCampos(UI_Categorias vista) {
    vista.txtName.setText("");
    vista.cbPrioridad.setSelectedIndex(0);
    vista.cbCategoria.setSelectedIndex(0);
    vista.atxtDescripcion.setText("");
  }

  public static void LlenarCampos(UI_Categorias vista, TipoIncidencia tipoIncidencia) {
    vista.txtName.setText(tipoIncidencia.getNombre());
    vista.cbPrioridad.setSelectedItem(tipoIncidencia.getNivel());
    vista.cbCategoria.setSelectedItem(tipoIncidencia.getCategoria());
    vista.atxtDescripcion.setText(tipoIncidencia.getDescripcion());
  }

  public static void MostrarTipoIncidencia(UI_Categorias vista, TipoIncidencia[] arreglo) {
    String[] columnas = { "ID", "Nombre", "Nivel", "Categoria", "Descripcion", "Fecha Modificacion" };
    DefaultTableModel modelo = new DefaultTableModel(null, columnas);
    vista.jtTipos.setModel(modelo);
    for (int i = 0; i < Arreglo_TipoIncidencias.contador; i++) {
      modelo.addRow(arreglo[i].Registro(i + 1));
    }

  }
}
