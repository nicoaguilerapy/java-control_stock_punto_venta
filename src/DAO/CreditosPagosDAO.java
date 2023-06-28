package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.CreditosPagos;
import vista.*;

public class CreditosPagosDAO extends Thread {

    private CreditosPagos deu;
    public ArrayList<CreditosPagos> resultadoDeudaPagos;

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
        resultadoDeudaPagos = listar(condicion);
    }
    
     public ArrayList getDeudaPagosList(Integer id) {
        ArrayList arr = new ArrayList();
        for (int i = 0; i < resultadoDeudaPagos.size(); i++) {
            if (resultadoDeudaPagos.get(i).getIdDeudas() == id) {
                arr.add(resultadoDeudaPagos.get(i));
            }
        }
        return arr;
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from creditos_pagos " + condicion + " order by id desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                deu = new CreditosPagos(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getInt("idcreditos"),
                        rs.getInt("pago"),
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
    

    public boolean insertar(CreditosPagos deu) {
        String q = " insert into creditos_pagos( fecha, idcreditos, pago, idUsuarios ) "
                + "values (?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, deu.getFecha());
            ps.setInt(2, deu.getIdDeudas());
            ps.setInt(3, deu.getPago());
            ps.setInt(4, deu.getIdUsuarios());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar deudas_ventas", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificar(CreditosPagos deu) {
        String q = " UPDATE creditos_pagos SET fecha=?, idcreditos=?, idventas=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, deu.getFecha());
            ps.setInt(2, deu.getIdDeudas());
            ps.setInt(3, deu.getPago());
            ps.setInt(4, deu.getIdUsuarios());

            ps.setInt(5, deu.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar deudas_ventas", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean eliminar(CreditosPagos deu) {
        String q = "DELETE FROM creditos_pagos WHERE id=?";

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
