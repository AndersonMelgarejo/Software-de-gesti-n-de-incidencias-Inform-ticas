/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import ArrayList.ListaPersonal;
import Model.Personal;
import View.UI_Login;
import java.awt.Color;

/**
 *
 * @author Renzo
 */
public class ProcessLogin {
    public static void initialize(UI_Login login){
        login.setLocationRelativeTo(null);
        login.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        login.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153, 0, 51));
        login.setVisible(true);
    }
    public static void limpiar(UI_Login login){
        login.txtUser.setText("");
        login.txtAccess.setText("");
        login.btnAccess.setSelected(false);
    }
    public static boolean validarUsuario(String user, String password, ListaPersonal lista) {
    for (Personal p : lista.getLista()) {            
            if (p.getUser().equals(user) && p.getPassword().equals(password)) {
                return true; // Usuario y contraseña correctos
            }
        }
        return false; // Usuario o contraseña incorrectos
    }

}
