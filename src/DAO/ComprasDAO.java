package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Compras;
import vista.*;

public class ComprasDAO extends Thread {

    public ArrayList<Compras> resultadoCompras;

    @Override
    public void run() {

        try {
            actualizarLista("");
            main.jCompras = new Jcompras();
        } catch (Exception e) {
            System.out.println("Error al generar Compras: " + e.getMessage());
        }

        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoCompras = listar(condicion);
    }

    public Compras getUltimoLote() {
        int i = resultadoCompras.size() - 1;
        return resultadoCompras.get(i);
    }

    public ArrayList listar(String condicion) {
        Compras compra;
        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM compras " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                compra = new Compras(
                        rs.getInt("id"),
                        rs.getString("facturanum"),
                        rs.getString("fecha"),
                        rs.getInt("total"),
                        rs.getInt("iva"),
                        rs.getString("estado"),
                        rs.getString("tipo"),
                        rs.getInt("idProveedor"),
                        rs.getInt("idUsuarios")
                );
                arr.add(compra);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(Compras compra) {
        String q = " INSERT INTO compras( fecha, total, iva, estado, facturanum, idUsuarios, idProveedor, tipo) VALUES (?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, compra.getFecha());
            ps.setInt(2, compra.getTotal());
            ps.setInt(3, compra.getIva());
            ps.setString(4, compra.getEstado());
            ps.setString(5, compra.getFacturanum());
            ps.setInt(6, compra.getIdUsuarios());
            ps.setInt(7, compra.getIdProveedor());
            ps.setString(8, compra.getTipo());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Agregar Compras", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean modificar(Compras compra) {
        String q = " UPDATE compras SET fecha=?, total=?, iva=?, estado=?, facturanum=?, idUsuarios=?, idProveedor=?, tipo=? "
                + "where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, compra.getFecha());
            ps.setInt(2, compra.getTotal());
            ps.setInt(3, compra.getIva());
            ps.setString(4, compra.getEstado());
            ps.setString(5, compra.getFacturanum());
            ps.setInt(6, compra.getIdUsuarios());
            ps.setInt(7, compra.getIdProveedor());
            ps.setString(8, compra.getTipo());

            ps.setInt(9, compra.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Modificar Compras", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean eliminar(Compras compra) {
        String q = "DELETE FROM compras WHERE id=? and estado='Anulado'";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, compra.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado con Éxito", "Eliminar Compras", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Eliminar Compras", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}
