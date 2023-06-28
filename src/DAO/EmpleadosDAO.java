package DAO;

import static java.lang.Thread.yield;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Empleados;
import vista.*;

public class EmpleadosDAO extends Thread {

    private Empleados emp;
    public HashMap<Integer, Object> mapEmpleados, mapEmpleadosCuenta;
    public ArrayList<Empleados> resultadoEmpleados;

    @Override
    public void run() {

        try {
            actualizarEmpleados("");
            main.jEmpleados = new Jempleados();
        } catch (Exception e) {

            System.out.println("Error al generar Empleados");
        }
        yield();
    }

    public void actualizarEmpleados(String condicion) {
        resultadoEmpleados = listar(condicion);
        mapEmpleados = new HashMap<>();
        mapEmpleados.clear();

        mapEmpleadosCuenta = new HashMap<>();
        mapEmpleadosCuenta.clear();

        for (int i = 0; i < resultadoEmpleados.size(); i++) {
            mapEmpleados.put(resultadoEmpleados.get(i).getId(), resultadoEmpleados.get(i));
        }

        for (int i = 0; i < resultadoEmpleados.size(); i++) {
            mapEmpleadosCuenta.put(resultadoEmpleados.get(i).getIdCuenta(), resultadoEmpleados.get(i).getId());
        }

    }

    private ArrayList listar(String condicion) {
        try {
            inicio.gConexion.comprobar();
            String q = "SELECT * FROM empleados " + condicion;
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            ArrayList arr = new ArrayList();

            while (rs.next()) {

                emp = new Empleados(
                        rs.getInt("id"),
                        rs.getString("cinruc"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("fecha"),
                        rs.getString("celtel"),
                        rs.getString("correo"),
                        rs.getString("ciudad"),
                        rs.getInt("idCuenta"),
                        rs.getInt("idUsuarios")
                );
                arr.add(emp);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean insertar(Empleados emp) {
        String q = " INSERT INTO empleados (cinruc, nombres , apellidos, direccion, fecha, celtel, correo, ciudad, idUsuarios ) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, emp.getCINRUC());
            ps.setString(2, emp.getNombres());
            ps.setString(3, emp.getApellidos());
            ps.setString(4, emp.getDireccion());
            ps.setString(5, emp.getFecha());
            ps.setString(6, emp.getCeltel());
            ps.setString(7, emp.getCorreo());
            ps.setString(8, emp.getCiudad());
            ps.setInt(9, emp.getIdUsuarios());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agregado con Exito", "Agregar Empleados", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesado: " + ex.getMessage(), "Agregar Empleados", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public boolean modificar(Empleados emp) {
        String q = " UPDATE empleados SET cinruc=?, nombres=?, apellidos=?, direccion=?, fecha=?, celtel=?, correo=?, "
                + " ciudad=?, idUsuarios=? where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setString(1, emp.getCINRUC());
            ps.setString(2, emp.getNombres());
            ps.setString(3, emp.getApellidos());
            ps.setString(4, emp.getDireccion());
            ps.setString(5, emp.getFecha());
            ps.setString(6, emp.getCeltel());
            ps.setString(7, emp.getCorreo());
            ps.setString(8, emp.getCiudad());
            ps.setInt(9, emp.getIdUsuarios());

            ps.setInt(10, emp.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesado: " + ex.getMessage(), "Modicar Empleados", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public boolean asignarCuenta(Empleados emp, Integer idCuenta) {
        String q = " UPDATE empleados SET idCuenta=? where id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, idCuenta);
            ps.setInt(2, emp.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesado: " + ex.getMessage(), "Modicar Empleados", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public boolean eliminar(Empleados emp) {
        String q = "DELETE FROM empleados WHERE id=?";

        try {
            inicio.gConexion.comprobar();
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);

            ps.setInt(1, emp.getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Elimar Empleados", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + ex.getMessage(), "Eliminar Empleados", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

}
