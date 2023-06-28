package vista;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Usuarios;
import modelo.balance.Balance;

public class JbancoN extends javax.swing.JFrame {

    private Balance balObj;

    private DefaultTableModel datosBalance;
    private TableRowSorter<TableModel> sorter;

    private String concepto;
    private Integer id = -1, ingreso = -1, egreso = -1, sumI, sumE, balance, mesActual = 0, mesActual2 = 0, filaSelecionada, estado = 1;
    private String fecha, fechaT;
    private Integer ventasMes = 0;
    private String condicion = "", ventasMesString = "";

    public Integer bal;

    public JbancoN() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("banco -" + inicio.version);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        String fechaAc = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fechaAc);
        txtIngreso.setText("0");
        txtEgreso.setText("0");

        datosBalance = (DefaultTableModel) tableBalance.getModel();
        inicio.tools.Busqued(tableBalance, datosBalance, txtBuscador);

        sorter = new TableRowSorter<>(datosBalance);
        tableBalance.setRowSorter(sorter);
        tableBalance.getSelectionModel().setSelectionInterval(0, 0);

    }

    public void actualizar() {
        actualizarCB();
        id = -1;

        String fecha = inicio.gFechas.getFechaActualBarras();
        fecha = inicio.gFechas.invertir(fecha);

        if (inicio.userObj.getRango().equals("Operador")) {
            txtFecha.setEnabled(false);
            chbxHistorial.setEnabled(false);
            chbxAdmin.setEnabled(false);
            cbCaja.setEnabled(false);
            condicion = " WHERE fecha='" + inicio.fechaYMD + "' and idUsuarios='" + inicio.userObj.getId() + "'";
            //condicion = " WHERE fecha BETWEEN '"+inicio.gFechas.getAnoActual()+"/"+inicio.gFechas.getMesctual()+"/01' AND '"+inicio.gFechas.getAnoActual()+"/"+inicio.gFechas.getMesctual()+"/31' and idUsuarios='" + inicio.userObj.getId() + "'";
        } else {
            if (inicio.userObj.getRango().equals("Administrador")) {

                if (!chbxHistorial.isSelected()) {
                    condicion = " where fecha='" + fecha + "'  ";
                } else {
                    condicion = "  where id > '0'  ";
                }

                if (chbxAdmin.isSelected()) {
                    condicion = condicion + " and idUsuarios='1'";
                }
            }
        }

        id = -1;
        sumI = 0;
        sumE = 0;

        main.balanceDAO.actualizarBanco("" + condicion);
        datosBalance = (DefaultTableModel) tableBalance.getModel();
        datosBalance.setRowCount(0);

        Object[] fila = new Object[datosBalance.getColumnCount()];

        for (int i = 0; i < main.balanceDAO.resultadoBanco.size(); i++) {

            Usuarios usuario = (Usuarios) main.usuariosDAO.mapUsuarios.get(main.balanceDAO.resultadoBanco.get(i).getIdUsuarios());

            //fecha = main.balanceDAO.resultadoBanco.get(i).getFecha();
            //fecha = inicio.gFechas.invertir(fecha);
            sumI = sumI + main.balanceDAO.resultadoBanco.get(i).getIngreso();
            sumE = sumE + main.balanceDAO.resultadoBanco.get(i).getEgreso();

            fila[0] = main.balanceDAO.resultadoBanco.get(i).getId();
            fila[1] = inicio.gFechas.invertir(main.balanceDAO.resultadoBanco.get(i).getFecha());
            fila[2] = main.balanceDAO.resultadoBanco.get(i).getConcepto();
            fila[3] = main.balanceDAO.resultadoBanco.get(i).getIngreso();
            fila[4] = main.balanceDAO.resultadoBanco.get(i).getEgreso();
            fila[5] = usuario.getUsuario();
            datosBalance.addRow(fila);
        }

        bal = sumI - sumE;

        txtBalance.setText(inicio.format.format(bal));
        txtIngresosT.setText(inicio.format.format(sumI));
        txtEgresosT.setText(inicio.format.format(sumE));

        inicio.tools.centrar(tableBalance, new int[]{0, 1, 5});
        inicio.tools.derecha(tableBalance, new int[]{3, 4});
        inicio.tools.decimalTable(tableBalance, 3);
        inicio.tools.decimalTable(tableBalance, 4);

        if (inicio.userObj.getRango().equals("Operador")) {
            if (inicio.login.jMenu.aperturaCaja && !inicio.login.jMenu.cierreCaja) {
                botones(true, false, false);
            } else {
                botones(false, false, false);
            }
        } else if (inicio.userObj.getRango().equals("Administrador")) {
            botones(true, true, true);
        }

    }

    public void botones(boolean agr, boolean mod, boolean eli) {
        btnAgregar.setEnabled(agr);
        btnModificar.setEnabled(mod);
        btnEliminar.setEnabled(eli);
    }

    public void limpiar() {
        String fechaAc = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fechaAc);
        txtConcepto.setText("");
        txtEgreso.setText("0");
        txtIngreso.setText("0");
    }

    private void actualizarCB() {
        cbCaja.removeAllItems();

        try {
            for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
                cbCaja.addItem(main.usuariosDAO.resultadoUsuarios.get(i).getUsuario());
            }
        } catch (Exception e) {
            System.out.println("Usuarios vacio");
        }

        setIdUsuario(inicio.userObj.getId());

    }

    private int getIdUsuario() {

        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
            String aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            String aux2 = (String) cbCaja.getSelectedItem();
            if (aux.equals(aux2)) {
                return main.usuariosDAO.resultadoUsuarios.get(i).getId();
            }
        }
        return -1;
    }

    private void setIdUsuario(int id) {
        String aux = "";
        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {

            if (main.usuariosDAO.resultadoUsuarios.get(i).getId() == id) {
                aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            }

            cbCaja.setSelectedItem(aux);
        }
    }

    /* public boolean apertura() {
        try {
            if () {
                botones(true, true, true);
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public boolean cierre() {
        try {

            if (main.balanceDAO.resultadoCaja.get(main.balanceDAO.resultadoCaja.size() - 1).getConcepto().equals("# Cierre: CAJA ID: " + inicio.userObj.getId())) {

                if (inicio.userObj.getRango().equals("Administrador")) {
                    btnAgregar.setEnabled(true);
                } else {
                    btnAgregar.setEnabled(false);
                }

                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);

                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtConcepto = new javax.swing.JTextField();
        txtIngreso = new javax.swing.JTextField();
        txtEgreso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        btnActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbCaja = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIngresosT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEgresosT = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBalance = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();
        chbxHistorial = new javax.swing.JCheckBox();
        chbxAdmin = new javax.swing.JCheckBox();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));

        txtConcepto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConceptoActionPerformed(evt);
            }
        });

        txtIngreso.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtIngreso.setText("0");
        txtIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresoActionPerformed(evt);
            }
        });
        txtIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresoKeyTyped(evt);
            }
        });

        txtEgreso.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEgreso.setText("0");
        txtEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEgresoActionPerformed(evt);
            }
        });
        txtEgreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEgresoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Concepto:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingreso:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Egreso:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingreso:");

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 102, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Caja:");

        cbCaja.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbCaja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConcepto)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbCaja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1))
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ingresos Totales:");

        txtIngresosT.setEditable(false);
        txtIngresosT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtIngresosT.setText("0");
        txtIngresosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresosTActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Egresos Totales:");

        txtEgresosT.setEditable(false);
        txtEgresosT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEgresosT.setText("0");

        txtBalance.setEditable(false);
        txtBalance.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBalance.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Balance:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu-banco.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEgresosT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIngresosT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBalance, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIngresosT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEgresosT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "CONCEPTO", "INGRESO", "EGRESO", "USUARIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBalanceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBalance);
        if (tableBalance.getColumnModel().getColumnCount() > 0) {
            tableBalance.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));
        txtBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscadorActionPerformed(evt);
            }
        });

        chbxHistorial.setBackground(new java.awt.Color(255, 255, 255));
        chbxHistorial.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        chbxHistorial.setText("Historial Completo");
        chbxHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxHistorialActionPerformed(evt);
            }
        });

        chbxAdmin.setBackground(new java.awt.Color(255, 255, 255));
        chbxAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        chbxAdmin.setText("Solo Administrador");
        chbxAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbxAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chbxHistorial)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane1))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbxHistorial)
                            .addComponent(chbxAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBalanceMouseClicked
        filaSelecionada = tableBalance.getSelectedRow();
        filaSelecionada = (Integer) tableBalance.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.balanceDAO.resultadoBanco.size(); i++) {

            if (filaSelecionada == main.balanceDAO.resultadoBanco.get(i).getId()) {

                char[] concepto = main.balanceDAO.resultadoBanco.get(i).getConcepto().toCharArray();
                if (concepto[0] == '#' || concepto[0] == '*') {
                    txtConcepto.setText("");
                    txtEgreso.setText("0");
                    txtIngreso.setText("0");
                    botones(true, false, false);
                } else {
                    if (!inicio.login.jMenu.cierreCaja && inicio.login.jMenu.aperturaCaja) {
                        id = main.balanceDAO.resultadoBanco.get(i).getId();
                        txtConcepto.setText(main.balanceDAO.resultadoBanco.get(i).getConcepto());
                        txtEgreso.setText("" + main.balanceDAO.resultadoBanco.get(i).getEgreso());
                        txtIngreso.setText("" + main.balanceDAO.resultadoBanco.get(i).getIngreso());
                        try {
                            fecha = inicio.gFechas.invertir(main.balanceDAO.resultadoBanco.get(i).getFecha());
                        } catch (Exception e) {
                        }
                        botones(true, true, true);
                    }
                }
            }
        }

        txtFecha.setText(fecha);
    }//GEN-LAST:event_tableBalanceMouseClicked

    private void txtIngresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtIngresoKeyTyped

    private void txtEgresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEgresoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtEgresoKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (JOptionPane.showConfirmDialog(null, "¿Seguro que quiere eliminar?", "Eliminar Registro",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtIngresosTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresosTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresosTActionPerformed

    private void txtBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscadorActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();

    }//GEN-LAST:event_formWindowActivated

    private void txtConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConceptoActionPerformed
        txtConcepto.transferFocus();
    }//GEN-LAST:event_txtConceptoActionPerformed

    private void txtIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresoActionPerformed
        txtIngreso.transferFocus();
    }//GEN-LAST:event_txtIngresoActionPerformed

    private void txtEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEgresoActionPerformed
        txtEgreso.transferFocus();
    }//GEN-LAST:event_txtEgresoActionPerformed

    private void chbxHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxHistorialActionPerformed
        actualizar();
    }//GEN-LAST:event_chbxHistorialActionPerformed

    private void chbxAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxAdminActionPerformed
        actualizar();
    }//GEN-LAST:event_chbxAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbCaja;
    private javax.swing.JCheckBox chbxAdmin;
    private javax.swing.JCheckBox chbxHistorial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBalance;
    public static javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtEgreso;
    private javax.swing.JTextField txtEgresosT;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtIngreso;
    private javax.swing.JTextField txtIngresosT;
    // End of variables declaration//GEN-END:variables

    public void agregar() {

        try {
            concepto = txtConcepto.getText();
            ingreso = Integer.parseInt(txtIngreso.getText());
            egreso = Integer.parseInt(txtEgreso.getText());

            fechaT = txtFecha.getText();
            fecha = inicio.gFechas.invertir(fechaT);

            if (inicio.gFechas.validarFecha(fecha) && !txtConcepto.getText().equals("")) {
                balObj = new Balance(null, concepto, ingreso, egreso, fecha, getIdUsuario(), "caja");
                main.balanceDAO.insertar(balObj);
                actualizar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Error al Ingresar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Error al Ingresar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificar() {

        try {
            concepto = txtConcepto.getText();
            ingreso = Integer.parseInt(txtIngreso.getText());
            egreso = Integer.parseInt(txtEgreso.getText());

            fechaT = txtFecha.getText();
            fecha = inicio.gFechas.invertir(fechaT);

            if (inicio.gFechas.validarFecha(fecha) && !txtConcepto.getText().equals("")) {
                balObj = new Balance(id, concepto, ingreso, egreso, fecha, getIdUsuario(), "caja");
                main.balanceDAO.modificar(balObj);
                actualizar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Error al Modificar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Error al Modificar", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eliminar() {

        if (id > -1) {
            balObj = new Balance(id);
            main.balanceDAO.eliminar(balObj, "caja");
            actualizar();
            limpiar();
        }
    }

}
