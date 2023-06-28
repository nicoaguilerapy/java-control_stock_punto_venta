package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

public class ProductosDAO extends Thread {

    private Productos pro;
    public HashMap<String, Object> map;
    public HashMap<Integer, Object> mapId;
    public ArrayList<Productos> resultado;

    @Override
    public void run() {

        try {
            actualizarLista("");
            main.jProductos = new Jproductos();
        } catch (Exception ex) {
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al generar Productos");
        }

        yield();
    }

    public void actualizarLista(String condicion) {
        resultado = listar(condicion);
        map = new HashMap<>();
        mapId = new HashMap<>();
        map.clear();

        for (int i = 0; i < resultado.size(); i++) {
            map.put(resultado.get(i).getCodigo(), resultado.get(i));
            mapId.put(resultado.get(i).getId(), resultado.get(i));
        }

    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select id, codigo, nombre, valor1, valor2, valor3,  estado, ilimitado, iva, idUsuarios from productos " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                pro = new Productos(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("valor1"),
                        rs.getInt("valor2"),
                        rs.getInt("valor3"),
                        rs.getString("estado"),
                        rs.getBoolean("ilimitado"),
                        rs.getInt("iva"),
                        rs.getInt("idUsuarios")
                );
                arr.add(pro);
            }

            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Object obj) {
        pro = (Productos) obj;
        String q = " insert into productos( codigo, nombre, valor1, valor2, valor3, estado, ilimitado, iva, idUsuarios) "
                + "values (?,?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getValor1());
            ps.setInt(4, pro.getValor2());
            ps.setInt(5, pro.getValor3());
            ps.setString(6, pro.getEstado());
            ps.setBoolean(7, pro.isIlimitado());
            ps.setInt(8, pro.getIva());
            ps.setInt(9, pro.getIdUsuarios());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agregado con Exito", "Agregar Producto", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Producto", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean modificar(Object obj) {
        pro = (Productos) obj;
        String q = " UPDATE productos SET nombre=?, codigo=?, valor1=?, valor2=?, valor3=?, estado=?, ilimitado=?, "
                + " iva=?, idUsuarios=?  where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getCodigo());
            ps.setInt(3, pro.getValor1());
            ps.setInt(4, pro.getValor2());
            ps.setInt(5, pro.getValor3());
            ps.setString(6, pro.getEstado());
            ps.setBoolean(7, pro.isIlimitado());
            ps.setInt(8, pro.getIva());
            ps.setInt(9, pro.getIdUsuarios());

            ps.setInt(10, pro.getId());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Acreedor", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean eliminar(Object obj) {
        pro = (Productos) obj;
        String q = "DELETE FROM productos WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, pro.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar Acreedor", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Acreedor", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }
}
