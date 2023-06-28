/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Productos;
import modelo.persona.Proveedores;
import vista.*;

public class ProveedoresDAO extends Thread {

    private Proveedores proveedores;

    public ArrayList<Proveedores> resultado;
    public HashMap<Integer, Object> map;

    @Override
    public void run() {
        actualizarLista("");
        main.jProveedores = new Jproveedores();
        try {

        } catch (Exception e) {

            System.out.println("Error al generar Proveedores");
        }
        yield();
    }

    public void actualizarLista(String condicion) {
        resultado = listar(condicion);
        map = new HashMap<>();
        map.clear();

        for (int i = 0; i < resultado.size(); i++) {
            map.put(resultado.get(i).getId(), resultado.get(i));
        }
    }

    public ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM proveedores " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {
                proveedores = new Proveedores(
                        rs.getInt("id"),
                        rs.getString("cinruc"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("fecha"),
                        rs.getString("celtel"),
                        rs.getString("correo"),
                        rs.getInt("idUsuarios"),
                        null,
                        rs.getString("empresanombre")
                );
                arr.add(proveedores);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Object obj) {
        proveedores = (Proveedores) obj;
        String q = " INSERT INTO proveedores(cinruc, nombres , apellidos, direccion, fecha, celtel, correo, idUsuarios, empresanombre) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, proveedores.getCINRUC());
            ps.setString(2, proveedores.getNombres());
            ps.setString(3, proveedores.getApellidos());
            ps.setString(4, proveedores.getDireccion());
            ps.setString(5, proveedores.getFecha());
            ps.setString(6, proveedores.getCeltel());
            ps.setString(7, proveedores.getCorreo());
            ps.setInt(8, proveedores.getIdUsuarios());
            ps.setString(9, proveedores.getEmpresaNombre());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agregado con Exito", "Agregar Cliente", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Agregar Cliente", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean modificar(Object obj) {
        proveedores = (Proveedores) obj;
        String q = " UPDATE proveedores SET cinruc=?, nombres=?, apellidos=?, direccion=?, fecha=?, celtel=?, "
                + " correo=?, idUsuarios=?, empresanombre=? where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, proveedores.getCINRUC());
            ps.setString(2, proveedores.getNombres());
            ps.setString(3, proveedores.getApellidos());
            ps.setString(4, proveedores.getDireccion());
            ps.setString(5, proveedores.getFecha());
            ps.setString(6, proveedores.getCeltel());
            ps.setString(7, proveedores.getCorreo());
            ps.setInt(8, proveedores.getIdUsuarios());
            ps.setString(9, proveedores.getEmpresaNombre());

            ps.setInt(10, proveedores.getId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modificado con exito", "Modicar Cliente", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Modicar Cliente", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public boolean eliminar(Object obj) {
        proveedores = (Proveedores) obj;
        String q = "DELETE FROM proveedores WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, proveedores.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar Cliente", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + ex.getMessage(), "Eliminar Cliente", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

}
