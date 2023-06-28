package DAO;

import herramientas.Sonido;
import java.awt.Color;
import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.JOptionPane;
import modelo.BajasProductos;
import modelo.LotesProductos;
import vista.*;

public class BajasProductosDAO extends Thread {

    private BajasProductos canti;

    public ArrayList<BajasProductos> resultadoBajas, resultado;

    public HashMap<Integer, Integer> mapBajas;

    @Override
    public void run() {
        try {
            actualizarSuma();
        } catch (Exception e) {
            System.out.println("Error al generar Bajas Productos: " + e.getMessage());
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoBajas = listar(condicion);
    }

    public ArrayList listarSuma() {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT idProducto, SUM(cantidad) AS suma FROM bajasproductos\n"
                    + "GROUP BY idProducto;";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                canti = new BajasProductos(
                        rs.getInt("idProducto"),
                        rs.getInt("suma")
                );
                arr.add(canti);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public void actualizarSuma() {
        actualizarLista("");
        resultado = listarSuma();
        mapBajas = new HashMap<>();
        mapBajas.clear();

        for (int i = 0; i < resultado.size(); i++) {
            mapBajas.put(resultado.get(i).getIdProducto(), resultado.get(i).getCantidad());
        }
    }

    private ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM bajasproductos " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            System.out.println("DAO.BajasProductosDAO.listar() "+ps.toString());
            ArrayList arr = new ArrayList();

            while (rs.next()) {
                canti = new BajasProductos(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getString("motivo"),
                        rs.getInt("cantidad"),
                        rs.getInt("idProducto"),
                        rs.getInt("idUsuarios")
                );
                arr.add(canti);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public boolean insertar(BajasProductos canti) {
        String q = " INSERT INTO bajasproductos( motivo, cantidad, idProducto, idUsuarios, fecha) "
                + "VALUES (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, canti.getMotivo());
            ps.setInt(2, canti.getCantidad());
            ps.setInt(3, canti.getIdProducto());
            ps.setInt(4, canti.getIdUsuarios());

            ps.setString(5, canti.getFecha());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Baja de Productos", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.BajasProductosDAO.insertar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean modificar(BajasProductos canti) {

        String q = " UPDATE lotesdetalles SET motivo=?, cantidad=?, idProducto=?, idUsuarios=?, fecha=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, canti.getMotivo());
            ps.setInt(2, canti.getCantidad());
            ps.setInt(3, canti.getIdProducto());
            ps.setInt(4, canti.getIdUsuarios());
            ps.setString(5, canti.getFecha());

            ps.setInt(6, canti.getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Modificado con Exito", "Baja de Productos", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Baja de Productos", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.BajasProductosDAO.modificar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean eliminar(BajasProductos canti) {
        String q = "DELETE FROM bajasproductos WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, canti.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Baja de Productos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

}
