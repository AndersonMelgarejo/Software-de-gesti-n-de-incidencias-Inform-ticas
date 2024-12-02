/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Processes.ProcessLogin;
import View.UI_Dashboard;
import View.UI_Login;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author franc
 */
public class LoginController implements ActionListener {
    
    public static String usuario;
    UI_Login login;
    UI_Dashboard dashboard = new UI_Dashboard();
    ListaPersonal listaPersonal;
    
    public LoginController(UI_Login login,ListaPersonal listaPersonal) {
        this.login = login;
        this.listaPersonal=listaPersonal;
        login.txtUser.putClientProperty("JTextField.placeholderText", "Ingresa tu usuario");
        login.txtUser.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        login.txtAccess.putClientProperty("JTextField.placeholderText", "Ingresa tu contraseña");
        if (login.jPanel2 != null) {
            login.jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc: 90");
            login.jPanel2.setOpaque(false);
        }
        login.btnAccess.addActionListener(this);        
        ProcessLogin.initialize(login);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnAccess) {
            String user = login.txtUser.getText();
            String password = new String(login.txtAccess.getPassword());
            
            // Validar usuario y contraseña usando ProcessLogin
            if (ProcessLogin.validarUsuario(user, password, listaPersonal)) {
                // Credenciales correctas, abrir dashboard
                usuario=user;
                DashboardController controllerDash = new DashboardController(dashboard);
                login.dispose();
            } else {
                // Credenciales incorrectas, mostrar mensaje de error
                JOptionPane.showMessageDialog(login, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                login.txtAccess.putClientProperty("JComponent.outline", "error");
                login.txtUser.putClientProperty("JComponent.outline", "error");
                ProcessLogin.limpiar(login);
            }
        }
    }

}