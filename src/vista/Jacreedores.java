package vista;

import modelo.balance.Balance;
import Gestores.ColorAcreedores;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class Jacreedores extends javax.swing.JFrame {

    private Acreedores presObj;
    private Balance balObj;

    private Integer filaDetalle, monto = 0;

    private String descripcion = "", estado = "", condicion = "";

    private DefaultTableModel datosAcreedores, datosDetalles;
    private ArrayList<Balance> balanceArray;

    int filaSelecionada, id;

    public Jacreedores() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Acreedores -" + inicio.version);

        btnAnular.setEnabled(false);
        btnPagar.setEnabled(false);
        btnCancelado.setEnabled(false);

        txtFecha.setText("" + inicio.gFechas.getFechaActual());
        txtFecha1.setText("" + inicio.gFechas.getFechaActual());

        datosAcreedores = (DefaultTableModel) tableAcreedores.getModel();
        datosDetalles = (DefaultTableModel) tableDetalles.getModel();
        inicio.tools.Busqued(tableAcreedores, datosAcreedores, txtBuscador);
    }

    public void limpiar() {
        txtDescripcion.setText("");
        txtMonto.setText("0");
        presObj = null;
    }

    public void actualizar() {
        try {
            if (inicio.userObj.getRango().equals("Operador")) {
                condicion = " where idUsuarios=" + inicio.userObj.getId();
            }
            id = -1;
            main.acreedoresDAO.actualizarAcreedores("" + condicion);
            datosAcreedores = (DefaultTableModel) tableAcreedores.getModel();
            datosAcreedores.setRowCount(0);

            Object[] fila = new Object[datosAcreedores.getColumnCount()];

            for (int i = 0; i < main.acreedoresDAO.resultadoAcreedores.size(); i++) {

                Usuarios usuario = (Usuarios) main.usuariosDAO.mapUsuarios.get(main.acreedoresDAO.resultadoAcreedores.get(i).getIdUsuarios());

                fila[0] = main.acreedoresDAO.resultadoAcreedores.get(i).getId();
                fila[1] = main.acreedoresDAO.resultadoAcreedores.get(i).getFecha();
                fila[2] = main.acreedoresDAO.resultadoAcreedores.get(i).getDescripcion();
                fila[3] = main.acreedoresDAO.resultadoAcreedores.get(i).getMonto();
                fila[4] = 0;
                fila[5] = main.acreedoresDAO.resultadoAcreedores.get(i).getEstado();
                fila[6] = usuario.getUsuario();
                datosAcreedores.addRow(fila);
            }

            balanceArray = new ArrayList<>();
            balanceArray = main.balanceDAO.listar(" WHERE concepto LIKE '%Pago de Prestamo%' ", "caja");

            for (int i = 0; i < balanceArray.size(); i++) {

                String auxStr = balanceArray.get(i).getConcepto();
                auxStr = auxStr.replaceAll("[^0-9]+", " ");
                auxStr = auxStr.replaceAll(" ", "");
                id = Integer.parseInt(auxStr);
                Integer sum = 0, sumAct = 0;

                for (int j = 0; j < tableAcreedores.getRowCount(); j++) {
                    Integer aux = (Integer) tableAcreedores.getValueAt(j, 0);

                    if (aux == id) {
                        sumAct = (Integer) tableAcreedores.getValueAt(j, 4);
                        sum = sumAct + balanceArray.get(i).getEgreso();
                        tableAcreedores.setValueAt(sum, j, 4);
                    }
                }
            }

            inicio.tools.centrar(tableAcreedores, new int[]{0, 1, 5});
            inicio.tools.derecha(tableAcreedores, new int[]{3, 4});
            inicio.tools.decimalTable(tableAcreedores, 3);
            inicio.tools.decimalTable(tableAcreedores, 4);
        } catch (Exception e) {
            System.out.println("vista.Jacreedores.actualizar( er)");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtBuscador = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAcreedores = new Gestores.ColorAcreedores();//javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetalles = new javax.swing.JTable();
        btnPagar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        txtFecha1 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPagado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPendiente = new javax.swing.JTextField();
        btnCancelado = new javax.swing.JButton();
        txtMonto1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();

        setTitle(".:: Deudores ::.");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));

        jScrollPane1.setBackground(new java.awt.Color(51, 0, 51));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        tableAcreedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Descripcion", "Prestamo", "Pagos", "Estado", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAcreedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAcreedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAcreedores);
        if (tableAcreedores.getColumnModel().getColumnCount() > 0) {
            tableAcreedores.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jPanel4.setBackground(new java.awt.Color(51, 0, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPane2.setBackground(new java.awt.Color(51, 0, 51));
        jScrollPane2.setBorder(null);

        tableDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "MONTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDetalles);

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-pagado2.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-anulado.png"))); // NOI18N
        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        try {
            txtFecha1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha:");

        txtPagado.setEditable(false);
        txtPagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPagado.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Pagado:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Pendiente:");

        txtPendiente.setEditable(false);
        txtPendiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPendiente.setText("0");

        btnCancelado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-caja.png"))); // NOI18N
        btnCancelado.setText("Cambiar a Cancelado");
        btnCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanceladoActionPerformed(evt);
            }
        });

        txtMonto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMonto1.setText("0");
        txtMonto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMonto1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMonto1KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnular)
                .addGap(3, 3, 3))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(btnCancelado))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMonto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagar)
                    .addComponent(btnAnular)
                    .addComponent(jLabel6)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnCancelado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(51, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deudor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        txtDescripcion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Descripcion:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Monto:");

        txtMonto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMonto.setText("0");
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
        );

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-actualizar.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscador)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar))
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (!presObj.getEstado().equals("Pendiente")) {
            JOptionPane.showMessageDialog(null, "Solo se puede eliminar Acreedores en Estado: Pendiente", "Modificar Acreedor", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar("Pendiente");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (!presObj.getEstado().equals("Pendiente")) {
            JOptionPane.showMessageDialog(null, "Solo se puede eliminar Acreedores en Estado: Pendiente", "Eliminar Acreedor", JOptionPane.ERROR_MESSAGE);

        } else {
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        pagarPrestamo();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void tableAcreedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAcreedoresMouseClicked
        filaSelecionada = tableAcreedores.getSelectedRow();

        filaSelecionada = (Integer) tableAcreedores.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.acreedoresDAO.resultadoAcreedores.size(); i++) {

            if (filaSelecionada == main.acreedoresDAO.resultadoAcreedores.get(i).getId()) {

                presObj = main.acreedoresDAO.resultadoAcreedores.get(i);
                System.out.println("vista.Jacreedores.tableAcreedoresMouseClicked() " + presObj.toString());
                txtDescripcion.setText(presObj.getDescripcion());
                txtMonto.setText("" + presObj.getMonto());
                txtFecha.setText(inicio.gFechas.invertir(presObj.getFecha()));

                mostrarDetalles();
                txtID.setText("" + presObj.getId());
            }

        }
        if (presObj == null) {
            txtID.setText("");
        }

    }//GEN-LAST:event_tableAcreedoresMouseClicked

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        AnularPrestamo();
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated

    private void tableDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetallesMouseClicked
        try {
            if (presObj.getEstado().equals("Cancelado")) {
                txtFecha1.setEnabled(false);
                txtMonto1.setEnabled(false);
                btnAnular.setEnabled(false);
                btnPagar.setEnabled(false);
                btnCancelado.setEnabled(false);
            } else {
                filaDetalle = tableDetalles.getSelectedRow();
                filaDetalle = (Integer) tableDetalles.getValueAt(filaDetalle, 0);
                btnAnular.setEnabled(true);
                btnPagar.setEnabled(false);

            }
        } catch (Exception e) {
            btnAnular.setEnabled(false);
            btnPagar.setEnabled(false);
        }
    }//GEN-LAST:event_tableDetallesMouseClicked

    private void btnCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanceladoActionPerformed
        modificar("Cancelado");
    }//GEN-LAST:event_btnCanceladoActionPerformed

    private void txtMonto1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonto1KeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMonto1KeyTyped

    private void txtMonto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonto1KeyPressed
        try {
            if (!txtMonto1.getText().equals("") || !txtMonto1.getText().equals("0")) {
                btnPagar.setEnabled(true);
            } else {
                btnPagar.setEnabled(false);
            }
        } catch (Exception e) {
            btnPagar.setEnabled(false);
        }
    }//GEN-LAST:event_txtMonto1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnCancelado;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableAcreedores;
    private javax.swing.JTable tableDetalles;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JFormattedTextField txtFecha1;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMonto1;
    private javax.swing.JTextField txtPagado;
    private javax.swing.JTextField txtPendiente;
    // End of variables declaration//GEN-END:variables

    public void agregar() {
        Balance auxBal = null;
        if (txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese una Descripcion", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
        } else {
            if (inicio.gFechas.validarFecha(inicio.gFechas.invertir(txtFecha.getText()))) {
                auxBal = inicio.gAyC.getApertura(inicio.gFechas.invertir(txtFecha.getText()));
                if (auxBal != null) {
                    try {
                        monto = Integer.parseInt(txtMonto.getText());
                        descripcion = txtDescripcion.getText();

                        if (monto > 0) {
                            presObj = new Acreedores(null, descripcion, monto, inicio.gFechas.invertir(txtFecha.getText()), "Pendiente", inicio.userObj.getId());
                            main.acreedoresDAO.insertar(presObj);

                            int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea ingresar CAJA el monto de " + inicio.format.format(monto) + " en fecha de " + inicio.gFechas.invertir(txtFecha.getText()) + "?",
                                    "Agregar Acreedor", JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                    new Object[]{"SI", "NO"}, "salir");

                            try {
                                if (inforTipo == 0) {
                                    presObj = main.acreedoresDAO.resultadoAcreedores.get(0);

                                    Balance caja = new Balance(null, "#(" + presObj.getId() + ")Prestamo: " + presObj.getDescripcion(), presObj.getMonto(), 0, presObj.getFecha(), presObj.getIdUsuarios(), "caja");
                                    main.balanceDAO.insertar(caja);
                                }
                            } catch (Exception e) {
                            }

                            actualizar();
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ingrese un Monto " + e.getMessage(), "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha con Apertura de Caja", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fecha inválida", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void modificar(String aux) {

        Balance auxBal = null;

        if (txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese una Descripcion", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean fechaVal = inicio.gFechas.validarFecha(inicio.gFechas.invertir(txtFecha.getText()));
            if (fechaVal) {
                auxBal = inicio.gAyC.getApertura(inicio.gFechas.invertir(txtFecha.getText()));
                if (auxBal != null) {
                    try {

                        monto = Integer.parseInt(txtMonto.getText());
                        descripcion = txtDescripcion.getText();

                        if (monto > 0) {
                            presObj = new Acreedores(filaSelecionada, descripcion, monto, inicio.gFechas.invertir(txtFecha.getText()), aux, inicio.userObj.getId());

                            System.out.println("vista.Jacreedores.modificar() presboj:" + presObj.toString());
                            main.acreedoresDAO.modificar(presObj);

                            Balance bal = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + presObj.getId() + ")Prestamo: %'  ", "caja").get(0);
                            bal.setTipo("caja");
                            bal.setIngreso(monto);
                            main.balanceDAO.modificar(bal);
                            actualizar();
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ingrese un Monto " + e.getMessage(), "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha con Apertura de Caja", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fecha inválida", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void eliminar() {

        int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea eliminar este Acreedor ID: " + presObj.getId() + "?",
                "Eliminar Pago", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"SI", "NO"}, "salir");
        String montoPagado = (String) tableAcreedores.getValueAt(tableAcreedores.getSelectedRow(), 4);

        try {
            if (inforTipo == 0) {

                if (montoPagado.equals("0")) {
                    main.acreedoresDAO.eliminar(presObj);
                    Balance bal = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + presObj.getId() + ")Prestamo: %'  ", "caja").get(0);
                    if (bal != null) {
                        main.balanceDAO.eliminar(bal, "caja");
                    }
                    actualizar();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo se puede eliminar Acreedores con Pagos en 0", "Eliminar Acreedor", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
        }

    }

    public void mostrarDetalles() {
        txtID.setText("" + presObj.getId());
        Integer pagosTotales = 0;
        datosDetalles.setRowCount(0);
        Object[] fila = new Object[datosDetalles.getColumnCount()];

        for (int i = 0; i < balanceArray.size(); i++) {
            if (balanceArray.get(i).getConcepto().equals("#(" + presObj.getId() + ")Pago de Prestamo")) {
                pagosTotales = pagosTotales + balanceArray.get(i).getEgreso();
                fila[0] = balanceArray.get(i).getId();
                fila[1] = balanceArray.get(i).getFecha();
                fila[2] = balanceArray.get(i).getEgreso();
                datosDetalles.addRow(fila);
            }
        }

        inicio.tools.centrar(tableDetalles, new int[]{0, 1});
        inicio.tools.decimalTable(tableDetalles, 2);

        txtPagado.setText(inicio.format.format(pagosTotales));
        txtPendiente.setText(inicio.format.format(presObj.getMonto() - pagosTotales));

        if (presObj.getEstado().equals("Pendiente")) {
            txtFecha1.setEnabled(true);
            txtMonto1.setEnabled(true);
        } else {
            txtFecha1.setEnabled(false);
            txtMonto1.setEnabled(false);
        }

        if (pagosTotales >= presObj.getMonto() && presObj.getEstado().equals("Pendiente")) {
            btnCancelado.setEnabled(true);
        } else {
            btnCancelado.setEnabled(false);
        }

    }

    public void pagarPrestamo() {
        try {
            if (inicio.gAyC.getApertura(inicio.gFechas.invertir(txtFecha1.getText())) != null) {
                Balance balObj = new Balance(null, "#(" + presObj.getId() + ")Pago de Prestamo", 0, Integer.parseInt(txtMonto1.getText()), inicio.gFechas.invertir(txtFecha1.getText()), inicio.userObj.getId(), "caja");
                main.balanceDAO.insertar(balObj);
                actualizar();
                mostrarDetalles();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha con Apertura de Caja", "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
        }
    }

    public void AnularPrestamo() {

        int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea anular este Pago?",
                "Eliminar Pago", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"SI", "NO"}, "salir");

        try {
            if (inforTipo == 0) {
                Balance balObj = new Balance(filaDetalle);
                main.balanceDAO.eliminar(balObj, "caja");
                actualizar();
                mostrarDetalles();
            }
        } catch (Exception e) {
        }

    }
}
