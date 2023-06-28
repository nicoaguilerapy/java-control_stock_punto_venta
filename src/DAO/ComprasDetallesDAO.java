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
import modelo.ComprasDetalles;
import vista.*;

public class ComprasDetallesDAO extends Thread {

    private ComprasDetalles canti;

    public ArrayList<ComprasDetalles> resultadoCompras, resultado;

    @Override
    public void run() {
        try {

        } catch (Exception e) {
            System.out.println("Error al generar DetallesVentas: " + e.getMessage());
        }

        yield();
    }

    public ArrayList<ComprasDetalles> getLista(String condicion) {
        return listar(condicion);
    }
    
    private ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM comprasdetalles " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                canti = new ComprasDetalles(
                        rs.getInt("id"),
                        rs.getString("concepto"),
                        rs.getInt("cantidad"),
                        rs.getInt("costo"),
                        rs.getInt("idCompras")
                );
                arr.add(canti);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(ComprasDetalles canti) {
        String q = " INSERT INTO comprasdetalles ( concepto, cantidad, costo, idCompras) "
                + "VALUES (?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, canti.getConcepto());
            ps.setInt(2, canti.getCantidad());
            ps.setInt(3, canti.getCosto());
            ps.setInt(4, canti.getIdCompras());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Compras Detalles", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.ComprasDetallesDAO.insertar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean modificar(ComprasDetalles canti) {

        String q = " UPDATE comprasdetalles SET concepto=?, cantidad=?, costo=?, idCompras=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, canti.getConcepto());
            ps.setInt(2, canti.getCantidad());
            ps.setInt(3, canti.getCosto());
            ps.setInt(4, canti.getIdCompras());

            ps.setInt(5, canti.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Compras Detalles", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.ComprasDetallesDAO.modificar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean eliminar(ComprasDetalles canti) {
        String q = "DELETE FROM comprasdetalles WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, canti.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Compras Detalles", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

}
