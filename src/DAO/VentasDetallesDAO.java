package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

public class VentasDetallesDAO extends Thread {

    VentasDetalles detallesVenta;

    public ArrayList<VentasDetalles> resultado;
    public HashMap<Integer, Integer> mapDetVentas, mapDetVentasReport;

    @Override
    public void run() {
        try {
            actualizarSuma();
            main.jCobranzas = new Jcobranzas();
        } catch (Exception e) {
            System.out.println("Error al generar DetallesVentas: " + e.getMessage());
        }
        yield();
    }

    public void actualizarSuma() {
        resultado = listarSuma();
        mapDetVentas = new HashMap<>();
        mapDetVentas.clear();

        for (int i = 0; i < resultado.size(); i++) {
            mapDetVentas.put(resultado.get(i).getIdProductos(), resultado.get(i).getCantidad());
        }
    }
    
    public ArrayList listarSuma() {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT idProductos, SUM(cantidad) AS suma FROM detallesventas d INNER JOIN ventas v INNER JOIN productos p\n"
                    + "ON  v.estado='Pagado' AND d.idProductos = p.id AND v.id = d.idVentas\n"
                    + "GROUP BY idProductos;";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                detallesVenta = new VentasDetalles(
                        rs.getInt("idProductos"),
                        rs.getInt("suma")
                );
                arr.add(detallesVenta);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM detallesventas " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                detallesVenta = new VentasDetalles(
                        rs.getInt("id"),
                        rs.getInt("idVentas"),
                        rs.getInt("idProductos"),
                        rs.getInt("cantidad"),
                        rs.getInt("iva5"),
                        rs.getInt("iva10"),
                        rs.getInt("precio"),
                        rs.getInt("subtotal")
                );
                arr.add(detallesVenta);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(Object obj) {
        detallesVenta = (VentasDetalles) obj;
        String q = " INSERT INTO detallesventas( idVentas, idProductos, cantidad, precio, iva5, iva10, subtotal) VALUES (?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, detallesVenta.getIdVentas());
            ps.setInt(2, detallesVenta.getIdProductos());
            ps.setInt(3, detallesVenta.getCantidad());
            ps.setInt(4, detallesVenta.getPrecio());
            ps.setInt(5, detallesVenta.getIva5());
            ps.setInt(6, detallesVenta.getIva10());
            ps.setInt(7, detallesVenta.getSubtotal());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Agregar DetallesVentas", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean eliminar(Object obj) {
        detallesVenta = (VentasDetalles) obj;
        String q = "DELETE FROM detallesventas WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, detallesVenta.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Eliminar DetallesVentas", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}
