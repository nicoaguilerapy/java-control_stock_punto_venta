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

public class CreditosVentasDAO extends Thread {

    private CreditosVentas deu;
    public ArrayList<CreditosVentas> resultadoDeudaVentas;

    @Override
    public void run() {
        try {
            actualizarLista("");
        } catch (Exception e) {
            System.out.println("Error al generar Deudas Ventas");
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultadoDeudaVentas = listar(condicion);
    }

    public ArrayList getDeudaVentasList(Integer id) {
        ArrayList arr = new ArrayList();
        for (int i = 0; i < resultadoDeudaVentas.size(); i++) {
            if (resultadoDeudaVentas.get(i).getIdDeudas()== id) {
                arr.add(resultadoDeudaVentas.get(i));
            }
        }
        return arr;
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from creditos_ventas " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                deu = new CreditosVentas(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getInt("idcreditos"),
                        rs.getInt("idventas"),
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

    public boolean insertar(CreditosVentas deu) {
        String q = " insert into creditos_ventas( fecha, idcreditos, idventas, idUsuarios ) "
                + "values (?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, deu.getFecha());
            ps.setInt(2, deu.getIdDeudas());
            ps.setInt(3, deu.getIdVentas());
            ps.setInt(4, deu.getIdUsuarios());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar deudas_ventas", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificar(CreditosVentas deu) {
        String q = " UPDATE creditos_ventas SET fecha=?, idcreditos=?, idventas=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, deu.getFecha());
            ps.setInt(2, deu.getIdDeudas());
            ps.setInt(3, deu.getIdVentas());
            ps.setInt(4, deu.getIdUsuarios());

            ps.setInt(5, deu.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar deudas_ventas", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean eliminar(CreditosVentas deu) {
        String q = "DELETE FROM creditos_ventas WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, deu.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar deudas_ventas", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }
}
