package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Datos;
import vista.Jconfiguracion;
import vista.inicio;
import vista.main;

public class DatosDAO extends Thread {

    private Datos dat;
    public HashMap<String, Object> map;
    public ArrayList<Datos> resultado;

    @Override
    public void run() {
        actualizarLista("");
        try {

        } catch (Exception e) {
            System.out.println("Error al generar Datos:" + e.getMessage());
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultado = listar(condicion);
        map = new HashMap<>();
        map.clear();
        
        for (int i = 0; i < resultado.size(); i++) {
            map.put(resultado.get(i).getAtributo(), resultado.get(i).getValor());
        }
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "select * from datos " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                dat = new Datos(
                        rs.getInt("id"),
                        rs.getString("atributo"),
                        rs.getString("valor")
                );
                arr.add(dat);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Object obj) {
        dat = (Datos) obj;
        String q = " insert into datos (atributo , valor) values (?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, dat.getAtributo());
            ps.setString(2, dat.getValor());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Agregar Datos", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean modificar(Object obj) {
        dat = (Datos) obj;
        String q = " UPDATE datos SET valor=? where atributo=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setString(1, dat.getValor());
            ps.setString(2, dat.getAtributo());

            ps.executeUpdate();
            System.out.println("datos.DatosDAO.modificar()");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Modicar Datos", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean eliminar(Object obj) {
        dat = (Datos) obj;
        String q = "DELETE FROM datos WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ps.setInt(1, dat.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar Datos", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar", "Eliminar Datos", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }
}
