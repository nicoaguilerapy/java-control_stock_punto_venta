package DAO;

import herramientas.Sonido;
import java.awt.Color;
import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Usuarios;
import vista.*;

public class UsuariosDAO extends Thread {

    private Usuarios user;
    public HashMap<Integer, Object> mapUsuarios;
    public ArrayList<Usuarios> resultadoUsuarios;

    @Override
    public void run() {
        inicio.login.lbEstado.setText("Conectando...");

        try {
            actualizarLista("");
            main.jUsuarios = new Jusuarios();
            inicio.login.lbEstado.setText("Conectado");
            Sonido audio = new Sonido("efectoConectado");
            inicio.login.lbEstado.setForeground(new Color(204, 255, 204));
        } catch (Exception e) {
            System.out.println("Error al generar Usuarios: " + e.getMessage());
        }

        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoUsuarios = listar(condicion);
        mapUsuarios = new HashMap<>();
        mapUsuarios.clear();

        for (int i = 0; i < resultadoUsuarios.size(); i++) {
            mapUsuarios.put(resultadoUsuarios.get(i).getId(), resultadoUsuarios.get(i));
        }
    }

    public ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM usuarios " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                user = new Usuarios(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("pass"),
                        rs.getString("rango")
                );
                arr.add(user);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(Object obj) {
        user = (Usuarios) obj;
        String q = " INSERT INTO usuarios( nombre, usuario, pass, rango) VALUES (?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getRango());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agregado Éxito", "Agregar Usuario", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean modificar(Object obj) {
        user = (Usuarios) obj;
        String q = " UPDATE usuarios SET nombre=?, usuario=?, pass=?, rango=? where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getRango());

            ps.setInt(5, user.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Modificado Éxito", "Modificar Usuario", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean eliminar(Object obj) {
        user = (Usuarios) obj;
        String q = "DELETE FROM usuarios WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado Éxito", "Eliminar Usuario", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Eliminar Usuario", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}
