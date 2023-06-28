package Gestores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Compras;
import modelo.LotesProductos;
import modelo.Pedidos;
import modelo.Ventas;
import modelo.balance.Balance;
import vista.inicio;
import vista.main;
import modelo.Datos;

public class GestorScripts {

    ArrayList<Balance> balanceArray;
    Balance balObj = null;
    Integer sw = 0;

    public GestorScripts() {

        if (!comprobarCreacion()) {
            if (ejecutar()) {
                JOptionPane.showMessageDialog(null, "Ejecución correcta de base de datos, se va reiniciar la aplicación", "Control Base de Datos", JOptionPane.INFORMATION_MESSAGE);

                try {
                    inicio.restartApplication();
                } catch (IOException | URISyntaxException e) {
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error en la ejecución", "Control Base de Datos", JOptionPane.ERROR_MESSAGE);
            }
        }

        String email_email = null;
        try {
            email_email = (String) main.datosDAO.map.get("email_email");
        } catch (Exception e) {

        }
        if (email_email == null) {
            System.out.println("Gestores.GestorScripts() Creacíon de Datos");
            Datos dat = null;
            dat = new Datos(null, "email_email", "xxx@gmail.com");
            main.datosDAO.insertar(dat);
            dat = new Datos(null, "email_password", "");
            main.datosDAO.insertar(dat);
            dat = new Datos(null, "email_asunto", "Recibo de Pago ID: {idventa}");
            main.datosDAO.insertar(dat);
            dat = new Datos(null, "email_automatic_send", "No");
            main.datosDAO.insertar(dat);
            dat = new Datos(null, "email_question_send", "No");
            main.datosDAO.insertar(dat);

            main.datosDAO.actualizarLista("");
        }
    }

    public boolean ejecutar() {
        controlDB("DROP TABLE deudas;");

        if (controlDB("create table creditos(\n"
                + "\n"
                + "id int NOT NULL AUTO_INCREMENT,\n"
                + "idClientes int,\n"
                + "estado varchar(15),\n"
                + "idUsuarios int,\n"
                + "PRIMARY KEY (id),\n"
                + "FOREIGN KEY (idClientes) REFERENCES clientes(id),\n"
                + "FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)\n"
                + "\n"
                + ");")) {

            if (controlDB("create table creditos_ventas(\n"
                    + "\n"
                    + "id int NOT NULL AUTO_INCREMENT,\n"
                    + "fecha varchar(10),\n"
                    + "idcreditos int,\n"
                    + "idventas int,\n"
                    + "idUsuarios int,\n"
                    + "PRIMARY KEY (id),\n"
                    + "FOREIGN KEY (idcreditos) REFERENCES creditos(id),\n"
                    + "FOREIGN KEY (idventas) REFERENCES ventas(id),\n"
                    + "FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)\n"
                    + "\n"
                    + ");")) {
                if (controlDB("create table creditos_pagos(\n"
                        + "\n"
                        + "id int NOT NULL AUTO_INCREMENT,\n"
                        + "fecha varchar(10),\n"
                        + "idcreditos int,\n"
                        + "pago int,\n"
                        + "idUsuarios int,\n"
                        + "PRIMARY KEY (id),\n"
                        + "FOREIGN KEY (idcreditos) REFERENCES creditos(id),\n"
                        + "FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)\n"
                        + "\n"
                        + ");")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean controlDB(String sql) {

        try {
            inicio.gConexion.comprobar();
            java.sql.Statement s = inicio.gConexion.con.createStatement();
            s.executeUpdate(sql);
            s.close();

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }

    }

    public boolean comprobarCreacion() {
        try {
            inicio.gConexion.comprobar();
            String q = "SHOW tables";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString("tables_in_miempre").equals("creditos")) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
