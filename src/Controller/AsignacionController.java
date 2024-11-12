/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.UI_Asignacion;
import View.UI_Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author franc
 */
public class AsignacionController extends PanelController implements ActionListener {
  UI_Asignacion asignacion;

  public AsignacionController(UI_Asignacion asignacion, UI_Dashboard app) {
    super(asignacion, app);
    this.asignacion = asignacion;
    super.showWindow(asignacion);

  }

  @Override
  protected void addListeners() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  protected void reloadWindow() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
