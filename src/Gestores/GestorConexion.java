package Gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConexion {

    private String parametros = "?autoReconnect=true&useSSL=false";
    private String db = "base";
    private String url = "jdbc:postgresql://localhost:5432/base";
    private String user = "postgres";
    private String pass = "";
    public Connection con = null;

    public GestorConexion() {
        
    }

    public void comprobar() {
        try {
            if (con == null || con.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection(url, user, pass);
                } catch (ClassNotFoundException Driver) {
                    Driver.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } catch (Exception e) {
        }
    }

    public void cerrar() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al Cerrar el servidor");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

}
