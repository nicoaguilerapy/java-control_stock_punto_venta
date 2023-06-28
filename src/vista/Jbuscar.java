package vista;

import vista.reportes.JinformesTabla;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Creditos;
import modelo.Productos;
import modelo.Usuarios;
import modelo.persona.Clientes;
import modelo.persona.Personas;

public class Jbuscar extends javax.swing.JFrame {

    DefaultTableModel datosClientes;
    private TableRowSorter<TableModel> sorter;
    String data[][] = {};
    String columnNames1[] = {"ID", "CIN", "APELLIDOS", "NOMBRES"};

    Integer id = -1, filaSeleccionada = -1;
    String CINRUC = "", cadena = "";
    String categoria = "";

    Color cGris;
    Image img;
    ImageIcon img2;

    String columnNames2[] = {"ID", "COD", "NOMBRE", "PRECIO A", "PRECIO B", "PRECIO C", "CANTIDAD"};

    public Jbuscar(String tipo) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Buscar " + tipo + " -" + inicio.version);

        categoria = tipo;

        actualizar();
        inicio.tools.Busqued(tableDatos, datosClientes, txtBuscador);

    }

    private void actualizar() {
        if (categoria.equals("cliente") || categoria.equals("historialCliente") || categoria.equals("deudas") || categoria.equals("informesclientes")) {
            img = new ImageIcon(getClass().getResource("/imagenes/icon-cliente-agregar.png")).getImage();
            img2 = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

            lbNuevo.setIcon(img2);

            datosClientes = new DefaultTableModel(data, columnNames1) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            if (categoria.equals("historialCliente")) {
                lbNuevo.setEnabled(false);
            }

            tableDatos.setModel(datosClientes);
            jPanel1.setBackground(new Color(153, 153, 255));

            mostrarCliente();
        } else {
            if (categoria.equals("producto") || categoria.equals("informeProductoAB") || categoria.equals("quienesCompran") || categoria.equals("bajaproducto")) {

                img = new ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

                datosClientes = new DefaultTableModel(data, columnNames2) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                lbNuevo.setEnabled(false);
                tableDatos.setModel(datosClientes);
                jPanel1.setBackground(new Color(255, 204, 204));

                mostrarProductoLote();
            } else {
                if (categoria.equals("empleados")) {

                    img = new ImageIcon(getClass().getResource("/imagenes/icon-cliente-agregar.png")).getImage();
                    img2 = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

                    lbNuevo.setIcon(img2);

                    datosClientes = new DefaultTableModel(data, columnNames1) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    tableDatos.setModel(datosClientes);
                    jPanel1.setBackground(new Color(255, 255, 255));

                    mostrarEmpleados();
                }
            }
        }

        sorter = new TableRowSorter<>(datosClientes);
        tableDatos.setRowSorter(sorter);
        tableDatos.getSelectionModel().setSelectionInterval(0, 0);
    }

    private void mostrarProductoLote() {

        datosClientes.setRowCount(0);
        main.productosDAO.actualizarLista("");

        Object[] fila = new Object[datosClientes.getColumnCount()];

        for (int i = 0; i < main.productosDAO.resultado.size(); i++) {
            fila[0] = inicio.formatID.format(main.productosDAO.resultado.get(i).getId());
            fila[1] = main.productosDAO.resultado.get(i).getCodigo();
            fila[2] = main.productosDAO.resultado.get(i).getNombre();
            fila[3] = main.productosDAO.resultado.get(i).getValor1();
            fila[4] = main.productosDAO.resultado.get(i).getValor2();
            fila[5] = main.productosDAO.resultado.get(i).getValor3();
            if (main.productosDAO.resultado.get(i).isIlimitado()) {
                fila[6] = "Ilimitado";
            } else {
                fila[6] = main.jProductos.cantidadProd(main.productosDAO.resultado.get(i).getId());
            }

            datosClientes.addRow(fila);
        }

        inicio.tools.decimalTable(tableDatos, 3);
        inicio.tools.decimalTable(tableDatos, 4);
        inicio.tools.decimalTable(tableDatos, 5);
    }

    private void mostrarCliente() {

        datosClientes.setRowCount(0);
        main.personasDAO.actualizarClientes("");

        Object[] fila = new Object[datosClientes.getColumnCount()];

        for (int i = 0; i < main.personasDAO.resultadoClientes.size(); i++) {
            fila[0] = inicio.formatID.format(main.personasDAO.resultadoClientes.get(i).getId());
            fila[1] = main.personasDAO.resultadoClientes.get(i).getCINRUC();
            fila[2] = main.personasDAO.resultadoClientes.get(i).getApellidos();
            fila[3] = main.personasDAO.resultadoClientes.get(i).getNombres();

            datosClientes.addRow(fila);
        }
    }

    private void mostrarEmpleados() {

        datosClientes.setRowCount(0);
        main.empleadosDAO.actualizarEmpleados("");

        Object[] fila = new Object[datosClientes.getColumnCount()];

        for (int i = 0; i < main.empleadosDAO.resultadoEmpleados.size(); i++) {
            fila[0] = inicio.formatID.format(main.empleadosDAO.resultadoEmpleados.get(i).getId());
            fila[1] = main.empleadosDAO.resultadoEmpleados.get(i).getCINRUC();
            fila[2] = main.empleadosDAO.resultadoEmpleados.get(i).getApellidos();
            fila[3] = main.empleadosDAO.resultadoEmpleados.get(i).getNombres();

            datosClientes.addRow(fila);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDatos = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();
        lbNuevo = new javax.swing.JLabel();
        lbSeleecionar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CIN", "APELLIDOS", "NOMBRES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDatos);
        if (tableDatos.getColumnModel().getColumnCount() > 0) {
            tableDatos.getColumnModel().getColumn(0).setResizable(false);
            tableDatos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableDatos.getColumnModel().getColumn(1).setResizable(false);
            tableDatos.getColumnModel().getColumn(2).setResizable(false);
            tableDatos.getColumnModel().getColumn(3).setResizable(false);
        }

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyPressed(evt);
            }
        });

        lbNuevo.setBackground(new java.awt.Color(255, 255, 255));
        lbNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-agregar.png"))); // NOI18N
        lbNuevo.setText("NUEVO");
        lbNuevo.setOpaque(true);
        lbNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNuevoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNuevoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbNuevoMousePressed(evt);
            }
        });

        lbSeleecionar.setBackground(new java.awt.Color(255, 255, 255));
        lbSeleecionar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSeleecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-seleccion.png"))); // NOI18N
        lbSeleecionar.setText("Seleccionar");
        lbSeleecionar.setOpaque(true);
        lbSeleecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSeleecionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSeleecionarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbSeleecionarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbSeleecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscador)
                    .addComponent(lbNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbSeleecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDatosMouseClicked
        if (evt.getClickCount() == 2) {
            seleccionar();
        }
    }//GEN-LAST:event_tableDatosMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated

    private void lbNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNuevoMouseEntered
        lbNuevo.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_lbNuevoMouseEntered

    private void lbNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNuevoMouseExited
        lbNuevo.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbNuevoMouseExited

    private void lbNuevoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNuevoMousePressed
        lbNuevo.setBackground(Color.DARK_GRAY);
        nuevo();
    }//GEN-LAST:event_lbNuevoMousePressed

    private void lbSeleecionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSeleecionarMouseEntered
        lbSeleecionar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_lbSeleecionarMouseEntered

    private void lbSeleecionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSeleecionarMouseExited
        lbSeleecionar.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbSeleecionarMouseExited

    private void lbSeleecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSeleecionarMousePressed
        lbSeleecionar.setBackground(Color.DARK_GRAY);
        seleccionar();
    }//GEN-LAST:event_lbSeleecionarMousePressed

    private void txtBuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            seleccionar();
        }
    }//GEN-LAST:event_txtBuscadorKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNuevo;
    private javax.swing.JLabel lbSeleecionar;
    private javax.swing.JTable tableDatos;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables

    private void seleccionar() {
        //try {
        if (categoria.equals("cliente")) {
            enviarCliente();
        } else {
            if (categoria.equals("producto")) {
                enviarProducto();
            } else {
                if (categoria.equals("historialCliente")) {
                    enviarHistorialCliente();
                } else {
                    if (categoria.equals("empleados")) {
                        enviarEmpleados();
                    } else {
                        if (categoria.equals("informeProductoAB")) {
                            enviarInformeProductoAB();
                        } else {
                            if (categoria.equals("quienesCompran")) {
                                enviarQuienesCompran();
                            } else {
                                if (categoria.equals("bajaproducto")) {
                                    enviarBajaProducto();
                                } else {
                                    if (categoria.equals("deudas")) {
                                        enviarDeudas();
                                    } else {
                                        if (categoria.equals("informesclientes")) {
                                            enviarClienteInforme();
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

    }

    public void enviarCliente() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));
        CINRUC = (String) tableDatos.getValueAt(filaSeleccionada, 1);
        main.jVentas.txtCIN.setText(CINRUC);
        cadena = (String) tableDatos.getValueAt(filaSeleccionada, 3) + " " + tableDatos.getValueAt(filaSeleccionada, 2);
        main.jVentas.txtNombre.setText(cadena);
        main.jVentas.IdCliente = id;
        this.dispose();
    }

    public void enviarClienteInforme() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));

        main.JpanelInformes.jInformesClientes.txtCIN.setText(tableDatos.getValueAt(filaSeleccionada, 1).toString());
        main.JpanelInformes.jInformesClientes.buscarCliente();
        this.dispose();
    }

    public void enviarEmpleados() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));

        main.jUsuarios.recibirEmpleado(id);
        this.dispose();
    }

    public void enviarProducto() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));
        cadena = (String) tableDatos.getValueAt(filaSeleccionada, 1);
        main.jVentas.txtCodProducto.setText(cadena);
        main.jVentas.buscarProductoMap();
        this.dispose();
    }

    public void enviarBajaProducto() {
        filaSeleccionada = tableDatos.getSelectedRow();
        inicio.login.jMenu.jbp.txtCodigo.setText(tableDatos.getValueAt(filaSeleccionada, 1).toString());
        inicio.login.jMenu.jbp.buscarProducto();
        this.dispose();
    }

    public void enviarHistorialCliente() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));

        Personas per = (Personas) main.personasDAO.mapClientes.get(id);

        inicio.gReportes.enviarHistorialCliente(per);
    }

    public void enviarInformeProductoAB() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));
        JinformesTabla jInformes = new JinformesTabla("informeProductoAB", id);
        jInformes.setVisible(true);
        jInformes.setLocationRelativeTo(null);

    }

    public void enviarQuienesCompran() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));
        JinformesTabla jInformes = new JinformesTabla("quienesCompran", id);
        jInformes.setVisible(true);
        jInformes.setLocationRelativeTo(null);

    }

    public void enviarDeudas() {
        filaSeleccionada = tableDatos.getSelectedRow();

        id = Integer.parseInt((String) tableDatos.getValueAt(filaSeleccionada, 0));
        Personas persObj = (Personas) main.personasDAO.mapClientes.get(id);

        Creditos deuObj = new Creditos(id, persObj.getId(), "Pendiente", inicio.userObj.getId());

        if (main.creditosDAO.insertar(deuObj)) {
            this.dispose();
        }
    }

    private void nuevo() {
        if (categoria.equals("cliente") || categoria.equals("deudas")) {
            inicio.login.jMenu.iniciarClientes();
        } else {
            if (categoria.equals("producto")) {
                inicio.login.jMenu.iniciarControldeProductos();
            } else if (categoria.equals("empleados")) {
                inicio.login.jMenu.iniciarEmpleados();
            } else {
            }
        }

    }

}
