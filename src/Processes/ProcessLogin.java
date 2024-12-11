/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import ArrayList.ListaPersonal;
import Model.Personal;
import View.UI_Login;
import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author Renzo
 */
public class ProcessLogin {
    public static void initialize(UI_Login login) {
        login.setLocationRelativeTo(null);
        login.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        login.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153, 0, 51));
        login.setVisible(true);
    }

    public static void limpiar(UI_Login login) {
        login.txtUser.setText("");
        login.txtAccess.setText("");
        login.btnAccess.setSelected(false);
    }

    public static boolean validarUsuario(String user, String password, ListaPersonal lista) {
        // Elimina elementos nulos antes de la iteración
        lista.getLista().removeIf(Objects::isNull);
        // Itera por la lista sin modificarla dentro del bucle
        for (Personal p : lista.getLista()) {
            if (p.getUser().equals(user) && p.getPassword().equals(password)) {
                return true; // Usuario y contraseña correctos
            }
        }
        return false; // Usuario o contraseña incorrectos
    }

    public static String getRol(String user, ListaPersonal lista) {
        // Elimina elementos nulos antes de la iteración
        lista.getLista().removeIf(Objects::isNull);
        // Itera por la lista sin modificarla dentro del bucle
        for (Personal p : lista.getLista()) {
            if (p.getUser().equals(user)) {
                return p.getCargo(); // Devuelve el rol del usuario
            }
        }
        return null; // Usuario no encontrado
    }
    // ultima edicion 02:02
}
