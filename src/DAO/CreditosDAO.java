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

public class CreditosDAO extends Thread {

    private Creditos deu;
    public HashMap<Integer, Object> mapDeudas;
    public ArrayList<Creditos> resultadoDeuda;

    @Override
    public void run() {
        try {
            actualizarLista("");
            main.jDeudas = new Jcreditos();
        } catch (Exception e) {
            System.out.println("Error al generar Deudas");
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoDeuda = listar(condicion);
        mapDeudas = new HashMap<>();
        mapDeudas.clear();

        for (int i = 0; i < resultadoDeuda.size(); i++) {
            mapDeudas.put(resultadoDeuda.get(i).getId(), resultadoDeuda.get(i));
        }

    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from creditos " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                deu = new Creditos(
                        rs.getInt("id"),
                        rs.getInt("idclientes"),
                        rs.getString("estado"),
                        rs.getInt("idUsuarios")
                );
                arr.add(deu);
            }

            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Creditos deu) {
        String q = " insert into creditos( idclientes, estado, idUsuarios ) "
                + "values (?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, deu.getIdClientes());
            ps.setString(2, deu.getEstado());;
            ps.setInt(3, deu.getIdUsuarios());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Deuda", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificar(Creditos deu) {
        String q = " UPDATE creditos SET idclientes=?, estado=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, deu.getIdClientes());
            ps.setString(2, deu.getEstado());;
            ps.setInt(3, deu.getIdUsuarios());

            ps.setInt(4, deu.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Deuda", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean eliminar(Creditos deu) {
        String q = "DELETE FROM creditos WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, deu.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Deuda", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
