package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.balance.Balance;
import vista.JbancoN;
import vista.Jcaja;
import vista.JivaDebito;
import vista.JtarjetaN;
import vista.inicio;
import vista.main;

public class BalanceDAO extends Thread {

    private Balance bal;

    public ArrayList<Balance> resultadoCaja, resultadoBanco, resultadoIvaDebito, resultadoTarjeta;

    public ArrayList<Balance> arrayTemp, cierresArray, aperturasArray;

    @Override
    public void run() {

        try {
            actualizarCaja("");
            main.jCaja = new Jcaja();
        } catch (Exception e) {

            System.out.println("Error al generar Caja");
        }

        try {
            actualizarBanco("");
            main.jBanco = new JbancoN();
        } catch (Exception e) {

            System.out.println("Error al generar Banco");
        }

        try {
            actualizarTarjeta("");
            main.jTarjeta = new JtarjetaN();
        } catch (Exception e) {

            System.out.println("Error al generar Tarjeta");
        }

        try {
            actualizarIvaDebito("");
            main.jIvaDebito = new JivaDebito();
        } catch (Exception e) {

            System.out.println("Error al generar IVA Débito");
        }

        yield();
    }

    public void actualizarCaja(String condicion) {
        resultadoCaja = new ArrayList();
        resultadoCaja = listar(condicion, "caja");
    }

    public void actualizarBanco(String condicion) {
        resultadoBanco = new ArrayList();
        resultadoBanco = listar(condicion, "banco");
    }

    public void actualizarTarjeta(String condicion) {
        resultadoTarjeta = new ArrayList();
        resultadoTarjeta = listar(condicion, "tarjeta");
    }

    public void actualizarIvaDebito(String condicion) {
        resultadoIvaDebito = new ArrayList();
        resultadoIvaDebito = listar(condicion, "ivadebito");
    }

    public void actualizarCierres() {
        arrayTemp = new ArrayList();
        arrayTemp = listar(" WHERE concepto LIKE '%Cierre%' OR concepto LIKE '%Apertura%'  ", "caja");
        cierresArray = new ArrayList();
        aperturasArray = new ArrayList();

        for (int i = 0; i < arrayTemp.size(); i++) {

            if (arrayTemp.get(i).getConcepto().contains("Apertura")) {
                aperturasArray.add(arrayTemp.get(i));
            } else {
                cierresArray.add(arrayTemp.get(i));
            }
        }
    }

    public ArrayList listar(String condicion, String tipo) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from " + tipo + " " + condicion + " order by fecha asc ";
            System.out.println("datos.BalanceDAO.listar() "+q);
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                bal = new Balance(
                        rs.getInt("id"),
                        rs.getString("concepto"),
                        rs.getInt("ingreso"),
                        rs.getInt("egreso"),
                        rs.getString("fecha"),
                        rs.getInt("idUsuarios"),
                        tipo
                );
                arr.add(bal);
            }

            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Balance bal) {
        String q = " insert into " + bal.getTipo() + " (concepto , ingreso, egreso, fecha, idUsuarios) "
                + "values (?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, bal.getConcepto());
            ps.setInt(2, bal.getIngreso());
            ps.setInt(3, bal.getEgreso());
            ps.setString(4, bal.getFecha());
            ps.setInt(5, bal.getIdUsuarios());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Agregar " + bal.getTipo(), JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificar(Balance bal) {
        String q = " UPDATE " + bal.getTipo() + " SET concepto=?, ingreso=?, egreso=?, fecha=?, idUsuarios=? "
                + " where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, bal.getConcepto());
            ps.setInt(2, bal.getIngreso());
            ps.setInt(3, bal.getEgreso());
            ps.setString(4, bal.getFecha());
            ps.setInt(5, bal.getIdUsuarios());

            ps.setInt(6, bal.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar " + bal.getTipo(), JOptionPane.ERROR_MESSAGE);
            System.out.println("datos.BalanceDAO.modificar() error: " + ex.getMessage());
        }
        return false;
    }

    public boolean eliminar(Balance bal, String tipo) {
        String q = "DELETE FROM " + tipo + " WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, bal.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar " + tipo, JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
