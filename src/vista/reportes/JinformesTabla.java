package vista.reportes;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import modelo.BajasProductos;
import modelo.VentasDetalles;
import modelo.LotesProductos;
import modelo.Productos;
import modelo.Usuarios;
import modelo.Ventas;
import modelo.persona.Proveedores;
import vista.inicio;
import vista.main;

public class JinformesTabla extends javax.swing.JFrame {

    DefaultTableModel datos;
    String data[][] = {};
    String columnNames[] = {"FECHA", "PRODUCTO", "CONCEPTO", "COSTO", "USUARIO", "CANTIDAD"};
    String columnNames1[] = {"FECHA", "FACTURA", "APELLIDO", "NOMBRE", "CANTIDAD"};
    String categoria;
    Integer id;

    public JinformesTabla(String tipo, Integer aux) {
        initComponents();
        categoria = tipo;
        id = aux;
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Buscar " + tipo + " -" + inicio.version);

        if (categoria.equals("informeProductoAB")) {
            mostrarInformeProductoAB();
        } else {
            if (categoria.equals("quienesCompran")) {
                mostrarQuienesCompran();
            }
        }
    }

    public void mostrarInformeProductoAB() {
        Productos prodObj = (Productos) main.productosDAO.mapId.get(id);
        LotesProductos lotObj = null;
        BajasProductos bajObj = null;
        Integer ventastotales = 0;
        Integer bajasprod = 0;
        Integer sumatotal = 0;

        datos = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(datos);
        datos.setRowCount(0);
        main.lotesDetallesDAO.actualizarLista(" where idProducto='" + id + "'");
        System.out.println("vista.Jinformes.mostrarInformeProductoAB()");
        System.out.println(prodObj.toString());

        Object[] fila = new Object[datos.getColumnCount()];

        for (int i = 0; i < main.lotesDetallesDAO.resultadoLoteDet.size(); i++) {
            System.out.println(main.lotesDetallesDAO.resultadoLoteDet.get(i).toString());

            lotObj = (LotesProductos) main.lotesProductosDAO.map.get(main.lotesDetallesDAO.resultadoLoteDet.get(i).getIdLote());
            System.out.println(lotObj.toString());

            fila[0] = lotObj.getFecha();
            fila[1] = "[" + prodObj.getCodigo() + "] " + prodObj.getNombre();
            fila[2] = "Ingreso de Mercaderia ID: " + lotObj.getId();
            fila[3] = main.lotesDetallesDAO.resultadoLoteDet.get(i).getCosto();
            fila[4] = main.lotesDetallesDAO.resultadoLoteDet.get(i).getIdUsuarios();
            fila[5] = main.lotesDetallesDAO.resultadoLoteDet.get(i).getCantidad();

            if (lotObj.getEstado().equals("Ingresado")) {
                datos.addRow(fila);
                sumatotal = sumatotal + main.lotesDetallesDAO.resultadoLoteDet.get(i).getCantidad();
            }

        }

        //ventas
        main.detVentasDAO.actualizarSuma();

        ventastotales = main.detVentasDAO.mapDetVentas.get(id);

        System.out.println("Suma: " + ventastotales);

        if (ventastotales == null) {
            ventastotales = 0;
        }

        fila[0] = "";
        fila[1] = "[" + prodObj.getCodigo() + "] " + prodObj.getNombre();
        fila[2] = "Ventas Totales";
        fila[3] = 0;
        fila[4] = 1;
        fila[5] = -1 * ventastotales;

        datos.addRow(fila);
        sumatotal = sumatotal + (-1 * ventastotales);

        //bajas
        main.bajasProductosDAO.actualizarLista(" where idProducto='" + id + "'");

        for (int i = 0; i < main.bajasProductosDAO.resultadoBajas.size(); i++) {

            fila[0] = main.bajasProductosDAO.resultadoBajas.get(i).getFecha();
            fila[1] = "[" + prodObj.getCodigo() + "] " + prodObj.getNombre();
            fila[2] = "Bajas Motivo: " + main.bajasProductosDAO.resultadoBajas.get(i).getMotivo();
            fila[3] = 0;
            fila[4] = main.balanceDAO.resultadoCaja.get(i).getIdUsuarios();
            fila[5] = -1 * main.bajasProductosDAO.resultadoBajas.get(i).getCantidad();

            datos.addRow(fila);
            sumatotal = sumatotal + (-1 * main.bajasProductosDAO.resultadoBajas.get(i).getCantidad());
        }

        fila[0] = "";
        fila[1] = "";
        fila[2] = "Suma Total";
        fila[3] = 0;
        fila[4] = 1;
        fila[5] = sumatotal;
        datos.addRow(fila);

        sustituirUsuario();

        //inicio.tools.centrar(table, new int[]{0, 4, 5, 6, 7});
        //inicio.tools.izquierda(table, new int[]{1});
        //inicio.tools.derecha(table, new int[]{2, 3});
    }

    public void mostrarQuienesCompran() {
        String sql = "SELECT v.id AS 'ID VENTAS', v.fechapago as 'FECHA PAGO', c.cinruc as 'CLIENTE DOC.', c.nombres as 'CLIENTE NOM.', c.apellidos as 'CLIENTE APE.', d.cantidad as 'CANTIDAD'FROM \n"
                + "detallesventas d\n"
                + "INNER JOIN ventas v  ON v.id = d.idVentas\n"
                + "INNER JOIN clientes c ON c.id = v.idClientes\n"
                + "WHERE idproductos = '" + id + "' AND (v.estado ='Pagado Financiado' or v.estado='Pagado')";
        datos = inicio.gInformes.getInforme(sql, inicio.gConexion.con);
        table.setModel(datos);

        inicio.tools.centrar(table, new int[]{0});
        inicio.tools.izquierda(table, new int[]{1, 2, 3, 4});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            System.out.println(".mouseClicked()");// to detect doble click events
            if (table.getValueAt(table.getSelectedRow(), 2).toString().equals("Ventas Totales")) {
                JinformesTabla jInformes = new JinformesTabla("quienesCompran", id);
                jInformes.setVisible(true);
                jInformes.setLocationRelativeTo(null);
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    public void sustituirUsuario() {

        int j, i;
        for (i = 0; i < datos.getRowCount(); i++) {

            int idT = (int) table.getValueAt(i, 4);

            for (j = 0; j < main.usuariosDAO.resultadoUsuarios.size(); j++) {

                int idU = main.usuariosDAO.resultadoUsuarios.get(j).getId();
                if (idT == idU) {

                    String usuario = main.usuariosDAO.resultadoUsuarios.get(j).getUsuario();

                    table.setValueAt(usuario, i, 4);
                }
            }
        }
    }
}
