/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformeController extends PanelController implements ActionListener{

    UI_Informe info;

    public InformeController(UI_Informe info, UI_Dashboard dash) {
        super(info, dash);
        this.info = info;
        info.btnRegistrar.addActionListener(this);
    }
    

    @Override
    protected void addListeners() {
       
    }

    @Override
    protected void reloadWindow() {
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
