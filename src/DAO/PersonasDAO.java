package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.persona.Personas;
import vista.*;

public class PersonasDAO extends Thread {

    private Personas per;
    public HashMap<Integer, Object> mapClientes;
    public ArrayList<Personas> resultadoClientes;

    @Override
    public void run() {

        try {
            actualizarClientes("");
            main.jClientes = new Jclientes();
        } catch (Exception e) {

            System.out.println("Error al generar Clientes");
        }
        yield();
    }

    public void actualizarClientes(String condicion) {
        resultadoClientes = listar(condicion, "clientes");
        mapClientes = new HashMap<>();
        mapClientes.clear();

        for (int i = 0; i < resultadoClientes.size(); i++) {
            mapClientes.put(resultadoClientes.get(i).getId(), resultadoClientes.get(i));
        }
    }


    public ArrayList listar(String condicion, String tipo) {
        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM " + tipo + " " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {

                per = new Personas(
                        rs.getInt("id"),
                        rs.getString("cinruc"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("fecha"),
                        rs.getString("celtel"),
                        rs.getString("correo"),
                        rs.getInt("idUsuarios"),
                        rs.getString("ciudad"),
                        tipo
                );
                arr.add(per);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Personas per) {
        String q = " INSERT INTO " + per.getTipo() + " (cinruc, nombres , apellidos, direccion, fecha, celtel, correo, ciudad, idUsuarios ) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, per.getCINRUC());
            ps.setString(2, per.getNombres());
            ps.setString(3, per.getApellidos());
            ps.setString(4, per.getDireccion());
            ps.setString(5, per.getFecha());
            ps.setString(6, per.getCeltel());
            ps.setString(7, per.getCorreo());
            ps.setString(8, per.getCiudad());
            ps.setInt(9, per.getIdUsuarios());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agregado con Exito", "Agregar " + per.getTipo(), JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesado: " + ex.getMessage(), "Agregar " + per.getTipo(), JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public boolean modificar(Personas per) {
        String q = " UPDATE " + per.getTipo() + " SET cinruc=?, nombres=?, apellidos=?, direccion=?, fecha=?, ciudad=?, celtel=?, "
                + " correo=?, idUsuarios=? where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, per.getCINRUC());
            ps.setString(2, per.getNombres());
            ps.setString(3, per.getApellidos());
            ps.setString(4, per.getDireccion());
            ps.setString(5, per.getFecha());
            ps.setString(6, per.getCiudad());
            ps.setString(7, per.getCeltel());
            ps.setString(8, per.getCorreo());
            ps.setInt(9, per.getIdUsuarios());

            ps.setInt(10, per.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesado: " + ex.getMessage(), "Modicar " + per.getTipo(), JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public boolean eliminar(Personas per) {
        String q = "DELETE FROM " + per.getTipo() + " WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, per.getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar " + per.getTipo(), JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + ex.getMessage(), "Eliminar " + per.getTipo(), JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

}
