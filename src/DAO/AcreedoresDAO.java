package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Acreedores;
import vista.*;

public class AcreedoresDAO extends Thread {

    private Acreedores pres;

    public ArrayList<Acreedores> resultadoAcreedores;

    @Override
    public void run() {
        try {
            actualizarAcreedores("");
            main.jAcreedores = new Jacreedores();
        } catch (Exception e) {

            System.out.println("Error al generar Acreedores");
        }
        yield();
    }

    public void actualizarAcreedores(String condicion) {
        resultadoAcreedores = listar(condicion);
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from  acreedores " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                pres = new Acreedores(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("monto"),
                        rs.getString("fecha"),
                        rs.getString("estado"),
                        rs.getInt("idUsuarios")
                );
                arr.add(pres);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Acreedores pres) {
        String q = " insert into acreedores ( descripcion, monto, fecha, estado, idUsuarios ) "
                + "values (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, pres.getDescripcion());
            ps.setInt(2, pres.getMonto());
            ps.setString(3, pres.getFecha());
            ps.setString(4, pres.getEstado());

            ps.setInt(5, pres.getIdUsuarios());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Acreedores", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean modificar(Acreedores pres) {
        String q = " UPDATE acreedores SET descripcion=?, monto=?, fecha=?, estado=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, pres.getDescripcion());
            ps.setInt(2, pres.getMonto());
            ps.setString(3, pres.getFecha());
            ps.setString(4, pres.getEstado());
            ps.setInt(5, pres.getIdUsuarios());

            ps.setInt(6, pres.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Acreedores", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean eliminar(Acreedores pres) {
        String q = "DELETE FROM acreedores WHERE id=? and estado='Pendiente'";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, pres.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Acreedores", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }
}
