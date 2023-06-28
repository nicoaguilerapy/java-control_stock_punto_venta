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
import modelo.LotesDetalles;
import modelo.LotesProductos;
import vista.*;

public class LotesDetallesDAO extends Thread {

    private LotesDetalles canti;

    public ArrayList<LotesDetalles> resultadoLoteDet, resultado;

    public HashMap<Integer, Integer> mapLotesDetalles, mapLotesUltimoP;

    @Override
    public void run() {
        try {
            actualizarSuma();
        } catch (Exception e) {
            System.out.println("Error al generar DetallesVentas: " + e.getMessage());
        }

        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoLoteDet = listar(condicion);
    }

    public ArrayList<LotesDetalles> getLista(String condicion) {
        return listar(condicion);
    }

    public void actualizarSuma() {
        resultado = listarSuma();
        mapLotesDetalles = new HashMap<>();
        mapLotesDetalles.clear();

        for (int i = 0; i < resultado.size(); i++) {
            mapLotesDetalles.put(resultado.get(i).getIdProducto(), resultado.get(i).getCantidad());
        }
    }

    public Integer getUltimoValor(int idProducto) {
        actualizarLista("");
        for (int i = 0; i < resultadoLoteDet.size(); i++) {
            if (resultadoLoteDet.get(i).getIdProducto() == idProducto && main.lotesProductosDAO.getLote(resultadoLoteDet.get(i).getIdLote())) {
                return resultadoLoteDet.get(i).getCosto();
            }
        }
        return 0;
    }

    public ArrayList listraUltimoPrecio() {
        mapLotesUltimoP = new HashMap<>();
        try {
            inicio.gConexion.comprobar();
            String q = "SELECT ld.idProducto, ld.cantidad, costo, lp.fecha FROM lotesdetalles ld INNER JOIN lotesproductos lp INNER JOIN productos p\n"
                    + "ON  lp.estado='Ingresado' AND ld.idProducto = p.id AND lp.id = ld.idLote\n"
                    + "ORDER BY ld.idProducto ASC";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();
            ArrayList rowArray = new ArrayList();
            while (rs.next()) {
                String row = rs.getInt("idProducto") + ";" + rs.getInt("cantidad") + ";" + rs.getInt("costo") + ";" + rs.getString("fecha");
                arr.add(row);
            }

            String row = "", aux = "";
            Integer rowid = -1, auxid = -1;

            for (int i = 0; i < arr.size(); i++) {
                if (i == 0) {
                    aux = (String) arr.get(i);
                } else {
                    row = (String) arr.get(i);
                    String[] rowParts = row.split(";");
                    String[] auxParts = aux.split(";");

                    if (rowParts[0].equals(auxParts[0]) && inicio.gFechas.compareDates(rowParts[3], auxParts[3])) {
                        aux = (String) arr.get(i);
                    } else {
                        rowArray.add(aux);
                        mapLotesUltimoP.put(Integer.parseInt(auxParts[0]), Integer.parseInt(auxParts[2]));
                        aux = (String) arr.get(i);
                    }
                }
            }
            return rowArray;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public ArrayList listarSuma() {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT idProducto, SUM(cantidad) AS suma FROM lotesdetalles ld INNER JOIN lotesproductos lp INNER JOIN productos p\n"
                    + "ON  lp.estado='Ingresado' AND ld.idProducto = p.id AND lp.id = ld.idLote\n"
                    + "GROUP BY idProducto";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                canti = new LotesDetalles(
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

    private ArrayList listar(String condicion) {

        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM lotesdetalles " + condicion + " ORDER BY id desc ";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                canti = new LotesDetalles(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getInt("costo"),
                        rs.getInt("idLote"),
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

    public boolean insertar(LotesDetalles canti) {
        String q = " INSERT INTO lotesdetalles( cantidad, costo, idLote, idProducto, idUsuarios) "
                + "VALUES (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, canti.getCantidad());
            ps.setInt(2, canti.getCosto());
            ps.setInt(3, canti.getIdLote());
            ps.setInt(4, canti.getIdProducto());
            ps.setInt(5, canti.getIdUsuarios());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agregado con Éxito", "Cantidad de Productos", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Cantidad de Productos", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.LotesDetallesDAO.insertar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean modificar(LotesDetalles canti) {

        String q = " UPDATE lotesdetalles SET cantidad=?, costo=?, idLote=?, idProducto=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, canti.getCantidad());
            ps.setInt(2, canti.getCosto());
            ps.setInt(3, canti.getIdLote());
            ps.setInt(4, canti.getIdProducto());
            ps.setInt(5, canti.getIdUsuarios());

            ps.setInt(6, canti.getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Modificado con Exito", "Cantidad de Productos", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Cantidad de Productos", JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.LotesDetallesDAO.modificar() ERROR " + ex.getMessage());
        }

        return false;
    }

    public boolean eliminar(LotesDetalles canti) {
        String q = "DELETE FROM lotesdetalles WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, canti.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Cantidad de Productos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

}
