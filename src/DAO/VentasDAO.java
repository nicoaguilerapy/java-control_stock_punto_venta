package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Ventas;
import vista.*;

public class VentasDAO extends Thread {

    private Ventas venta;

    public ArrayList<Ventas> ventasArray;

    @Override
    public void run() {

        try {
            actualizarLista("");
            main.jVentas = new Jventas();
        } catch (Exception e) {
            System.out.println("Error al generar Ventas: " + e.getMessage());
        }

        yield();
    }

    public void actualizarLista(String condicion) {
        ventasArray = listar(condicion);
    }

    public Ventas getVenta(Integer id) {
        Ventas ventaObj = null;

        for (int i = 0; i < ventasArray.size(); i++) {
            if (id == ventasArray.get(i).getId()) {
                ventaObj = ventasArray.get(i);
                return ventaObj;
            }
        }
        ventaObj = (Ventas) listar(" where id ='" + id + "'").get(0);
        return ventaObj;
    }

    public ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM ventas " + condicion + " ORDER BY id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                venta = new Ventas(
                        rs.getInt("id"),
                        rs.getString("facturanum"),
                        rs.getString("fecha"),
                        rs.getString("fechapago"),
                        rs.getInt("idClientes"),
                        rs.getInt("total"),
                        rs.getString("estado"),
                        rs.getString("formapago"),
                        rs.getInt("descuento"),
                        rs.getInt("idVendedor"),
                        rs.getInt("idCobrador")
                );
                arr.add(venta);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(Object obj) {
        venta = (Ventas) obj;
        String q = " INSERT INTO ventas( fecha,  idClientes, total, estado, idVendedor, facturanum, formapago, descuento, idCobrador, fechapago) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, venta.getFecha());
            ps.setInt(2, venta.getIdClientes());
            ps.setInt(3, venta.getTotal());
            ps.setString(4, venta.getEstado());
            ps.setInt(5, venta.getIdVendedor());
            ps.setString(6, venta.getFacturanum());
            ps.setString(7, venta.getFormaPago());
            ps.setInt(8, venta.getDescuento());
            ps.setInt(9, venta.getIdCobrador());
            ps.setString(10, venta.getFechaPago());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Creado con Éxito", "Crear Venta", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Agregar Ventas", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean modificar(Object obj) {
        venta = (Ventas) obj;
        String q = " UPDATE ventas SET fecha=?, idClientes=?, total=?, estado=?, idVendedor=?, facturanum=?, formapago=?, descuento=?, idCobrador=?, fechapago=? "
                + "where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, venta.getFecha());
            ps.setInt(2, venta.getIdClientes());
            ps.setInt(3, venta.getTotal());
            ps.setString(4, venta.getEstado());
            ps.setInt(5, venta.getIdVendedor());
            ps.setString(6, venta.getFacturanum());
            ps.setString(7, venta.getFormaPago());
            ps.setInt(8, venta.getDescuento());
            ps.setInt(9, venta.getIdCobrador());
            ps.setString(10, venta.getFechaPago());

            ps.setInt(11, venta.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Modificar Ventas", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public boolean eliminar(Object obj) {
        venta = (Ventas) obj;
        String q = "DELETE FROM ventas WHERE id=? and estado='Presupuesto'";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, venta.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Eliminar Ventas", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public Integer ventasMes(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT count(*) as cantidad FROM ventas " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            System.err.println("DAO.VentasDAO.ventasMes() "+ps.toString());
            ArrayList arr = new ArrayList();

            while (rs.next()) {
                
                return rs.getInt("cantidad");
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;
    }

}
