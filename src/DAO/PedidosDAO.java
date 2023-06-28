package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

public class PedidosDAO extends Thread {

    private Pedidos pep;

    public ArrayList<Pedidos> resultado;

    @Override
    public void run() {
        try {
            actualizarLista("");
            main.jPedidos = new Jpedidos();
        } catch (Exception e) {

            System.out.println("Error al generar Pedidos");
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultado = listar(condicion);
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from pedidos " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                pep = new Pedidos(
                        rs.getInt("id"),
                        rs.getString("pedido"),
                        rs.getString("detalles"),
                        rs.getString("estado"),
                        rs.getString("fecha"),
                        rs.getInt("idUsuarios")
                );
                arr.add(pep);
            }

            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int insertar(Pedidos pep) {
        int res = 0;
        String q = " insert into pedidos( pedido, detalles, estado, fecha, idUsuarios ) "
                + "values (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, pep.getPedido());
            ps.setString(2, pep.getDetalles());
            ps.setString(3, pep.getEstado());
            ps.setString(4, pep.getFecha());
            ps.setInt(5, pep.getIdUsuarios());

            ps.executeUpdate();
            res = 1;
            JOptionPane.showMessageDialog(null, "Agregado con Exito", "Agregar Pedidos", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Pedidos", JOptionPane.ERROR_MESSAGE);

        }

        return res;
    }

    public int modificar(Pedidos pep) {
        int res = 0;
        String q = " UPDATE pedidos SET pedido=?, detalles=?, estado=?, fecha=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, pep.getPedido());
            ps.setString(2, pep.getDetalles());
            ps.setString(3, pep.getEstado());
            ps.setString(4, pep.getFecha());
            ps.setInt(5, pep.getIdUsuarios());

            ps.setInt(6, pep.getId());

            ps.executeUpdate();
            res = 1;

            JOptionPane.showMessageDialog(null, "Modificado con exito", "Modicar Pedidos", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Pedidos", JOptionPane.ERROR_MESSAGE);

        }

        return res;
    }

    public int eliminar(Pedidos pep) {
        int res = 0;
        String q = "DELETE FROM pedidos WHERE id=? and estado='Cancelado'";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, pep.getId());
            res = ps.executeUpdate();
            res = 1;
            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar Pedidos", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Pedidos", JOptionPane.ERROR_MESSAGE);

        }

        return res;
    }
}
