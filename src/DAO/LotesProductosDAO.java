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

public class LotesProductosDAO extends Thread {

    private LotesProductos lotPro;

    public ArrayList<LotesProductos> resultadoLotesProductos;
    public HashMap<Integer, Object> map;

    @Override
    public void run() {
        try {
            actualizarLista("");
            main.jLoteProductos = new JlotesProductos();
        } catch (Exception e) {
            System.out.println("Error al generar LotesProductos");
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoLotesProductos = listar(condicion);
        map = new HashMap<>();

        for (int i = 0; i < resultadoLotesProductos.size(); i++) {
            map.put(resultadoLotesProductos.get(i).getId(), resultadoLotesProductos.get(i));
        }
    }

    public LotesProductos getUltimoLote() {
        int i = resultadoLotesProductos.size() - 1;
        return resultadoLotesProductos.get(i);
    }

    public boolean getLote(int idDetalle) {
        for (int i = 0; i < resultadoLotesProductos.size(); i++) {
            if (resultadoLotesProductos.get(i).getId() == idDetalle &&  resultadoLotesProductos.get(i).getEstado().equals("Ingresado")) {
                return true;
            }
        }
        return false;
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from lotesproductos " + condicion + " ";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            ArrayList arr = new ArrayList();
            while (rs.next()) {
                lotPro = new LotesProductos(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getString("facturanum"),
                        rs.getString("estado"),
                        rs.getInt("gastototal"),
                        rs.getInt("ivacredito"),
                        rs.getInt("idProveedor"),
                        rs.getInt("idUsuarios")
                );
                arr.add(lotPro);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(LotesProductos lotPro) {
        String q = " insert into lotesproductos( fecha, facturanum, estado, gastototal, ivacredito, idProveedor, idUsuarios ) "
                + "values (?,?,?,?,?,?,?)";
        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, lotPro.getFecha());
            ps.setString(2, lotPro.getFacturanum());
            ps.setString(3, lotPro.getEstado());
            ps.setInt(4, lotPro.getGastototal());
            ps.setInt(5, lotPro.getIvacredito());
            ps.setInt(6, lotPro.getIdProveedor());
            ps.setInt(7, lotPro.getIdUsuarios());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Lotes", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificar(LotesProductos lotPro) {
        String q = " UPDATE lotesproductos SET fecha=?, facturanum=?, estado=?, gastototal=?, ivacredito=?, idProveedor=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, lotPro.getFecha());
            ps.setString(2, lotPro.getFacturanum());
            ps.setString(3, lotPro.getEstado());
            ps.setInt(4, lotPro.getGastototal());
            ps.setInt(5, lotPro.getIvacredito());
            ps.setInt(6, lotPro.getIdProveedor());
            ps.setInt(7, lotPro.getIdUsuarios());

            ps.setInt(8, lotPro.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Lote", JOptionPane.ERROR_MESSAGE);
            System.out.println("error " + ex.getMessage());
            System.out.println("error " + ex.getSQLState());
            System.out.println("error " + ex.getErrorCode());

        }

        return false;
    }

    public boolean eliminar(LotesProductos lotPro) {
        String q = "DELETE FROM lotesproductos WHERE id=? and estado='Cancelado'";
        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, lotPro.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Lote", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
