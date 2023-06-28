package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.UsuariosHistorial;
import vista.*;

public class UsuariosHistorialDAO extends Thread {

    private UsuariosHistorial userH;

    public ArrayList<UsuariosHistorial> resultadoUHistorial;

    @Override
    public void run() {
        main.jUHistorial = new JusuariosHistorial();
        yield();
    }

    public void actualizarLista(String condicion){
    resultadoUHistorial = listar(condicion);
    }
    
    public ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM usuarioshistorial " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                userH = new UsuariosHistorial(
                        rs.getInt("id"),
                        rs.getInt("idUsuarios"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("ip"),
                        rs.getString("host")
                );
                arr.add(userH);
            }  
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Object obj) {
        userH = (UsuariosHistorial) obj;
        String q = " INSERT INTO usuarioshistorial( idUsuarios, fecha, hora, ip, host) VALUES (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, userH.getIdUsuarios());
            ps.setString(2, userH.getFecha());
            ps.setString(3, userH.getHora());
            ps.setString(4, userH.getIp());
            ps.setString(5, userH.getHost());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Agregar Historial", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
