/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncidenciaController extends PanelController implements ActionListener {

    UI_Incidencias inci;

    public IncidenciaController(UI_Incidencias inci, UI_Dashboard dash) {
        super(inci, dash);
        this.inci = inci;
        super.showWindow(inci);

    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void reloadWindow() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
