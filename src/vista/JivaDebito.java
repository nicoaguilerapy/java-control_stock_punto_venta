package vista;

import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

public class JivaDebito extends javax.swing.JFrame {

    private DefaultTableModel datosTable;
    private String data[][] = {};
    private String columnNames[] = {"ID", "FECHA", "CONCEPTO", "DEBITO", "CREDITO"};
    public Integer ingresoT = 0, egresoT = 0, bal = 0;
    public String impuestosDelMes;
    private String fecha, mouthTmp, fecha1, fecha2, condicion, yearTmp, dayTmp;

    public JivaDebito() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("IVA Débito " + inicio.version);
        datosTable = new DefaultTableModel(data, columnNames);
        tableDatos.setModel(datosTable);

        fecha = inicio.fechaYMD;

    }

    public void actualizar() {
        
        fecha1 = inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual()+ "/01";
        fecha2 = inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/31";

        condicion = " WHERE fecha BETWEEN '" + fecha1 + "' AND '" + fecha2 + "'";

        main.balanceDAO.actualizarIvaDebito(condicion);

        ingresoT = 0;
        egresoT = 0;
        datosTable.setRowCount(0);

        Object[] fila = new Object[datosTable.getColumnCount()];

        for (int i = 0; i < main.balanceDAO.resultadoIvaDebito.size(); i++) {

            ingresoT = ingresoT + main.balanceDAO.resultadoIvaDebito.get(i).getIngreso();
            egresoT = egresoT + main.balanceDAO.resultadoIvaDebito.get(i).getEgreso();

            fecha = main.balanceDAO.resultadoIvaDebito.get(i).getFecha();
            fecha = inicio.gFechas.invertir(fecha);

            fila[0] = main.balanceDAO.resultadoIvaDebito.get(i).getId();
            fila[1] = fecha;
            fila[2] = main.balanceDAO.resultadoIvaDebito.get(i).getConcepto();
            fila[3] = inicio.format.format(main.balanceDAO.resultadoIvaDebito.get(i).getIngreso());
            fila[4] = inicio.format.format(main.balanceDAO.resultadoIvaDebito.get(i).getEgreso());

            datosTable.addRow(fila);
        }

        fila[0] = "";
        fila[1] = fecha;
        fila[2] = "Sumas Totales";
        fila[3] = inicio.format.format(ingresoT);
        fila[4] = inicio.format.format(egresoT);
        datosTable.addRow(fila);

        bal = ingresoT - egresoT;

        impuestosDelMes = inicio.format.format(bal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableDatos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDatos;
    // End of variables declaration//GEN-END:variables
}
