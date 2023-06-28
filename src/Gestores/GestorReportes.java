package Gestores;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Compras;
import modelo.Empleados;
import modelo.LotesProductos;
import modelo.Productos;
import modelo.Usuarios;
import modelo.Ventas;
import modelo.VentasDetalles;
import modelo.persona.Clientes;
import modelo.persona.Personas;
import modelo.persona.Proveedores;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.inicio;
import vista.main;
import java.awt.Dialog;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GestorReportes {

    private JasperReport reporte;
    HashMap<String, Object> map;
    ArrayList<VentasDetalles> detVentaList;
    Proveedores provObj;
    Productos prodObj;
    JasperPrint jasperPrint = null;

    public GestorReportes() {
        map = new HashMap<String, Object>();
    }

    private void iniciarReporte(String path, HashMap<String, Object> map, boolean paraEnviar) {
        try {
            URL master = this.getClass().getResource(path);
            System.out.println("master " + master);

            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro en "+path);
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                e.printStackTrace();
                System.exit(3);
            }

            jasperPrint = JasperFillManager.fillReport(masterReport, map, inicio.gConexion.con);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);

            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(!paraEnviar);

            if (paraEnviar) {
                String PATH = "boleta" + map.get("idventas") + ".pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, PATH);

                String asunto = (String) main.datosDAO.map.get("email_asunto");
                String idventa = (String) map.get("idventas");
                asunto = asunto.replace("{idventa}", idventa);

                GestorCorreos gCorreo = new GestorCorreos();

                gCorreo.setEmail((String) main.datosDAO.map.get("email_email"));
                gCorreo.setPassword((String) main.datosDAO.map.get("email_password"));
                gCorreo.setEmailTo((String) map.get("clientecorreo"));
                gCorreo.setAsunto(asunto);
                gCorreo.setPATH(PATH);

                try {
                    gCorreo.start();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error de Correo en Remitente", "Error al Enviar Correo", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void iniciarReporteList(HashMap<String, Object> map, List lista) {
        try {

            reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes/Iinformes.jasper"); //Se carga el reporte de su localizacion
            JasperPrint jprint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lista));
            JasperViewer viewer = new JasperViewer(jprint, false);

            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void boletaVenta(Ventas ventas) {
        Integer iva5 = 0, iva10 = 0, ivaT = 0;

        Personas per = (Personas) main.personasDAO.mapClientes.get(ventas.getIdClientes());

        for (int i = 0; i < main.detVentasDAO.resultado.size(); i++) {
            if (Objects.equals(main.detVentasDAO.resultado.get(i).getIdVentas(), ventas.getId())) {
                iva5 = main.detVentasDAO.resultado.get(i).getIva5();
                iva10 = main.detVentasDAO.resultado.get(i).getIva10();

                ivaT = ivaT + iva5 + iva10;
            }
        }

        String sql = "SELECT p.codigo, p.nombre, d.cantidad, d.precio, d.subtotal\n"
                + "FROM productos p INNER JOIN detallesventas d\n"
                + "ON p.id=d.idProductos and d.idVentas='" + ventas.getId() + "'";
        main.datosDAO.map.put("sql", sql);

        main.datosDAO.map.put("clientenombre", "  " + per.getNombres() + " " + per.getApellidos());
        main.datosDAO.map.put("clientedireccion", "  " + per.getDireccion());
        main.datosDAO.map.put("clientedocumento", "  " + per.getCINRUC());
        main.datosDAO.map.put("fecha", "  " + inicio.gFechas.invertir(ventas.getFecha()));
        main.datosDAO.map.put("idVentas", ventas.getId());
        main.datosDAO.map.put("total", "  " + inicio.format.format(ventas.getTotal()));
        main.datosDAO.map.put("iva", "  " + inicio.format.format(ivaT));

        iniciarReporte("/reportes/Iventa.jasper", main.datosDAO.map, false);
        main.datosDAO.actualizarLista("");

    }

    public void cajasIndividuales() {

        String q = "SELECT u.id, u.nombre, u.usuario, sum(c.ingreso) AS ingreso, sum(c.egreso) AS egreso , sum(c.ingreso - c.egreso) AS diferencia\n"
                + "FROM caja c INNER JOIN usuarios u\n"
                + "ON c.idUsuarios = u.id AND c.fecha='" + inicio.fechaYMD + "' \n"
                + "GROUP BY u.id";

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sql", q);

        iniciarReporte("/reportes/IcajasIndividuales.jasper", map, false);
    }

    public void entradaCompras(Compras comp) {
        map = new HashMap<String, Object>();
        String titulo = "Compras de Mercaderias";

        String q = "SELECT ROW_NUMBER() OVER (ORDER BY cd.id) AS row, cd.id, cd.concepto, cd.cantidad, cd.costo, cd.cantidad * cd.costo as total FROM comprasdetalles cd\n"
                + "INNER JOIN compras c\n"
                + "WHERE cd.idCompras = c.id AND c.id = '" + comp.getId() + "'";
        map.put("id", "" + comp.getId());
        map.put("fecha", "" + comp.getFecha());
        map.put("facturanum", "" + comp.getFacturanum());
        map.put("total", "" + comp.getTotal());

        map.put("sql", q);
        map.put("titulo", titulo);
        provObj = (Proveedores) main.proveedoresDAO.map.get(comp.getIdProveedor());
        map.put("proveedor", provObj.getEmpresaNombre() + "");

        iniciarReporte("/reportes/IentradaCompras.jasper", map, false);
    }

    public void entradaMercaderias(LotesProductos lote) {
        map = new HashMap<String, Object>();
        String titulo = "Entrada de Mercaderías";

        String q = "SELECT distinct ROW_NUMBER() OVER (ORDER BY ld.id) AS row, ld.id, p.nombre, ld.cantidad, ld.costo,  ld.cantidad * ld.costo as total FROM lotesdetalles ld\n"
                + "INNER JOIN   lotesproductos l INNER JOIN productos p\n"
                + "WHERE ld.idLote = l.id AND ld.idProducto = p.id AND  l.id = '" + lote.getId() + "'";

        map.put("id", "" + lote.getId());
        map.put("fecha", "" + lote.getFecha());
        map.put("facturanum", "" + lote.getFacturanum());
        map.put("total", "" + lote.getGastototal());

        map.put("sql", q);
        map.put("titulo", titulo);
        provObj = (Proveedores) main.proveedoresDAO.map.get(lote.getIdProveedor());
        map.put("proveedor", provObj.getEmpresaNombre() + "");

        iniciarReporte("/reportes/IentradaMercaderias.jasper", map, false);
    }

    public void clientesCompradores() {
        iniciarReporte("/reportes/Iclientes.jasper", null, false);
    }

    public void productosComprados() {
        iniciarReporte("/reportes/Iproductos.jasper", null, false);
    }

    public void enviarHistorialCliente(Personas per) {

        String q = "SELECT v.id, v.facturanum, v.fecha, p.nombre, d.cantidad\n"
                + "FROM ventas v INNER JOIN productos p INNER join detallesventas d\n"
                + "ON v.id = d.idVentas AND v.estado = 'Pagado' AND p.id = d.idProductos AND v.idClientes = '" + per.getId() + "'\n"
                + "ORDER BY v.fecha DESC";

        map.put("sql", q);
        map.put("cin", per.getCINRUC());
        map.put("nombre", per.getNombres() + " " + per.getApellidos());

        iniciarReporte("/reportes/IClienteHistorial.jasper", map, false);
        map.clear();
    }

    public void boleta(Ventas venta, boolean paraEnviar) {
        detVentaList = new ArrayList<>();
        detVentaList = main.detVentasDAO.listar(" where idVentas='" + venta.getId() + "'");
        Integer iva5 = 0, iva10 = 0, total = 0, ivaTotal = 0;

        map = new HashMap<>();

        main.datosDAO.actualizarLista("");
        //Convertir a texto la fecha

        map.put("fecha", "" + venta.getFecha());
        map.put("fechapago", "" + venta.getFechaPago());
        map.put("idventas", "" + venta.getId());

        //Datos del cliente
        Personas per = (Personas) main.personasDAO.mapClientes.get(venta.getIdClientes());
        map.put("clientenombre", "  " + per.getNombres() + " " + per.getApellidos());
        map.put("clientedireccion", "  " + per.getDireccion());
        map.put("clientedocumento", "  " + per.getCINRUC());
        map.put("clientecorreo", per.getCorreo());

        //datos de la empresa
        map.put("empresa", main.datosDAO.map.get("empresa"));
        map.put("direccion1", main.datosDAO.map.get("direccion1"));
        map.put("direccion2", main.datosDAO.map.get("direccion2"));
        map.put("correo", main.datosDAO.map.get("correo"));
        map.put("celtel", main.datosDAO.map.get("celtel"));

        System.out.println("Cliente ");
        System.out.println(map.get("clientedocumento"));
        System.out.println(map.get("clientenombre"));
        System.out.println(map.get("clientedireccion"));

        System.out.println("Datos ");
        System.out.println(map.get("empresa"));
        System.out.println(map.get("direccion1"));
        System.out.println(map.get("direccion2"));
        System.out.println(map.get("celtel"));
        System.out.println(map.get("correo"));

        int c = 0, sw = 0;
        String prodCod = "", prodNombre = "", prodSubtotal = "", prodPrecio = "", prodCant = "";

        System.out.println("Detalles: ");
        for (int i = 0; i < detVentaList.size(); i++) {
            c = 0;

            try {
                prodObj = (Productos) main.productosDAO.mapId.get(detVentaList.get(i).getIdProductos());
                prodCod = prodObj.getCodigo();
                prodNombre = prodObj.getNombre();
                prodPrecio = detVentaList.get(i).getPrecio() + "";
                prodSubtotal = detVentaList.get(i).getSubtotal() + "";
                prodCant = detVentaList.get(i).getCantidad() + "";
            } catch (Exception e) {
                System.out.println("herramientas.GestorReportes.factura() error de producto:");
                System.out.println("Error: " + e.getMessage());
                prodObj = null;
            }

            map.put("detcod" + i, "" + prodCod);
            map.put("detnom" + i, "" + prodNombre);
            map.put("detpre" + i, "" + prodPrecio);
            map.put("detsub" + i, "" + prodSubtotal);
            map.put("detcant" + i, "" + prodCant);

            System.out.println("Fila: " + i + " " + prodCod + " " + prodNombre + " " + prodPrecio + " " + prodSubtotal);

            ///calcular iva total
            //iva5 = iva5 + detVentaList.get(i).getIva10();
            //iva10 = iva10 + detVentaList.get(i).getIva5();
        }

        for (int i = detVentaList.size(); i <= 10; i++) {

            prodCod = "";
            prodNombre = "";
            prodPrecio = "";
            prodSubtotal = "";
            prodCant = "";

            map.put("detcod" + i, "" + prodCod);
            map.put("detnom" + i, "" + prodNombre);
            map.put("detpre" + i, "" + prodPrecio);
            map.put("detsub" + i, "" + prodSubtotal);
            map.put("detcant" + i, "" + prodCant);

            System.out.println("Fila: " + i + " " + prodCod + " " + prodNombre + " " + prodPrecio + " " + prodSubtotal);
        }

        map.put("descuento", "-" + venta.getDescuento());
        map.put("total", "" + venta.getTotalACobrar());

        iniciarReporte("/reportes/Ifactura.jasper", map, paraEnviar);
    }

    public void transferir(Integer totalT, Integer idTransferido) {
        Usuarios userT = (Usuarios) main.usuariosDAO.mapUsuarios.get(idTransferido);
        map.put("fila", "Transferencia A Caja de ID: " + idTransferido + " [" + userT.getUsuario() + "]");
        map.put("valor", "" + inicio.format.format(totalT));
        map.put("nombre", inicio.userObj.getNombre());
        map.put("id", "" + inicio.userObj.getId());
        map.put("tnombre", "" + userT.getNombre());

        inicio.gReportes.iniciarReporte("/reportes/Itransferir.jasper", map, false);

        map.clear();
    }

    public void cierreCaja(String fechaCerrar, Integer tarjetaT, Integer bancoT, Integer i, Integer e) {

        String sql = "select id, concepto, ingreso, egreso from caja where idUsuarios='" + inicio.userObj.getId() + "' and fecha='" + fechaCerrar + "' and concepto<>'&Sincronizacion' ";

        map.put("sql", sql);
        map.put("nombre", inicio.userObj.getNombre());

        int id = (int) main.empleadosDAO.mapEmpleadosCuenta.get(inicio.userObj.getId());
        Empleados emp = (Empleados) main.empleadosDAO.mapEmpleados.get(id);

        map.put("cin", emp.getCINRUC());
        //  map.put("cin", inicio.login.emp.getCINRUC());
        map.put("fecha", fechaCerrar);

        map.put("tarjeta", "# Cierre: TARJETA ID: " + inicio.userObj.getId());
        map.put("banco", "# Cierre: BANCO ID: " + inicio.userObj.getId());

        map.put("tarjetacierre", "" + tarjetaT);
        map.put("bancocierre", "" + bancoT);

        map.put("sumatotali", "" + inicio.format.format(i));
        map.put("sumatotale", "" + inicio.format.format(e));

        inicio.gReportes.iniciarReporte("/reportes/Icierre.jasper", map, false);
        map.clear();
    }

    public JasperPrint reporteClientesDetallado(Integer idclientes, String desde, String hasta, Integer idcobrador, String tipo) {
        JasperPrint jasperPrint = null;
        final List<VentasDetalles> lista = new ArrayList();
        try {
            String q = "SELECT  v.id AS idventas, v.idClientes, v.fechapago, v.total, v.descuento, dv.idProductos, p.nombre, dv.cantidad, dv.precio FROM detallesventas dv\n"
                    + "INNER JOIN   ventas v INNER JOIN productos p\n"
                    + "ON dv.idVentas = v.id AND p.id = dv.idProductos\n"
                    + "WHERE \n"
                    + "v.fechapago BETWEEN '" + desde + "' AND '" + hasta + "' \n"
                    + "AND (v.idCobrador='" + idcobrador + "' AND v.estado='Pagado' OR v.estado='Pagado Financiado' OR v.estado='Financiado') \n";

            if (idclientes != -1) {
                q = q + "and idclientes='" + idclientes + "'\n";
            }

            q = q + " ORDER BY v.idclientes desc";
            PreparedStatement ps = inicio.gConexion.con.prepareStatement(q);
            System.out.println("Gestores.GestorReportes.reporteClientesDetallado()\n" + q);
            ResultSet rs = ps.executeQuery();

            Integer isResultado = 0;
            while (rs.next()) {
                isResultado = 1;
                VentasDetalles det = new VentasDetalles(rs.getInt("idventas"));
                det.setCantidad(rs.getInt("cantidad"));
                det.setPrecio(rs.getInt("precio"));
                det.setIdProductos(rs.getInt("idProductos"));

                lista.add(det);
            }

            URL master = this.getClass().getResource("/reportes/extracto_detallado.jasper");
            System.out.println("master " + master);

            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                e.printStackTrace();
                System.exit(3);
            }

            Map parametro = new HashMap();

            JRDataSource data = new JRDataSource() {

                int inddet = -1;

                public boolean next() throws JRException {
                    return ++inddet < lista.size();
                }

                public Object getFieldValue(JRField jrf) throws JRException {
                    Object value = null;
                    Ventas venta = main.ventasDAO.getVenta(lista.get(inddet).getIdVentas());
                    Personas cliObj = (Personas) main.personasDAO.mapClientes.get(venta.getIdClientes());
                    Productos prodObj = (Productos) main.productosDAO.mapId.get(lista.get(inddet).getIdProductos());

                    if ("codCliente".equals(jrf.getName())) {
                        value = cliObj.getId();
                    }
                    if ("nroVenta".equals(jrf.getName())) {
                        value = venta.getId();
                    }
                    if ("factura".equals(jrf.getName())) {
                        value = "Sin Factura";
                    }
                    if ("fecha".equals(jrf.getName())) {
                        value = venta.getFechaPago();
                    }
                    if ("doc".equals(jrf.getName())) {
                        value = cliObj.getCINRUC();
                    }
                    if ("cliente".equals(jrf.getName())) {
                        value = cliObj.getNombreCompleto();
                    }
                    if ("descuento".equals(jrf.getName())) {
                        value = venta.getDescuento();
                    }
                    if ("cod_prod".equals(jrf.getName())) {
                        value = prodObj.getCodigo();
                    }
                    if ("producto".equals(jrf.getName())) {
                        value = prodObj.getNombre();
                    }
                    if ("cantidad".equals(jrf.getName())) {
                        value = lista.get(inddet).getCantidad();
                    }
                    if ("unitario".equals(jrf.getName())) {
                        value = lista.get(inddet).getPrecio();
                    }
                    if ("total".equals(jrf.getName())) {
                        value = lista.get(inddet).getPrecio() * lista.get(inddet).getCantidad();
                    }
                    if ("estado".equals(jrf.getName())) {
                        value = venta.getEstado();
                    }

                    System.out.println("Value: " + value);
                    return value;
                }
            };
            if (isResultado == 1) {
                jasperPrint = JasperFillManager.fillReport(masterReport, parametro, data);
            } else {
                JOptionPane.showMessageDialog(null, "Solicitud Vacia", "Informe detallado", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
            j.printStackTrace();
        }
        return jasperPrint;
    }

    public JasperPrint reporteClientesGeneral(Integer idclientes, String desde, String hasta, Integer idcobrador, String tipo) {
        JasperPrint jasperPrint = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String q = "";
        final List<Ventas> lista = new ArrayList();

        ArrayList<Integer[]> ventasCredito = new ArrayList<>();
        HashMap<Integer, Integer> pagosCreditos = new HashMap<>();
        HashMap<Integer, Integer> cantidadVentas = new HashMap<>();
        HashMap<Integer, Integer> creditosMontoPagado = new HashMap<>();

        try {
            q = "SELECT * FROM ventas  \n"
                    + "WHERE \n"
                    + "fechapago BETWEEN '" + desde + "' AND '" + hasta + "' \n"
                    + "AND (idCobrador='" + idcobrador + "' AND estado='Pagado' or estado='Pagado Financiado' or estado='Financiado') \n";

            if (idclientes != -1) {
                q = q + "and idclientes='" + idclientes + "'\n";
            }

            q = q + " ORDER BY idclientes desc";
            ps = inicio.gConexion.con.prepareStatement(q);
            System.out.println("Gestores.GestorReportes.reporteExtractoVista()\n" + q);
            rs = ps.executeQuery();

            Integer isResultado = 0;
            while (rs.next()) {
                isResultado = 1;
                Ventas venta = new Ventas(rs.getInt("id"));
                venta.setIdClientes(rs.getInt("idclientes"));
                venta.setFechaPago(rs.getString("fechapago"));
                venta.setTotal(rs.getInt("total"));
                venta.setDescuento(rs.getInt("descuento"));
                venta.setEstado(rs.getString("estado"));
                System.out.println(venta.toString());

                lista.add(venta);
            }

            //crea un map con las ventas, y su credito a la cual pertenece con sus montos a pagar correspondientes
            q = "SELECT v.id as idventas, c.idcreditos FROM ventas v INNER JOIN creditos_ventas c ON v.id = c.idventas;";

            ps = inicio.gConexion.con.prepareStatement(q);
            System.out.println("Gestores.GestorReportes.reporteExtractoVista() Etapa 1:\n" + q);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer[] datos = {rs.getInt("idventas"), rs.getInt("idcreditos")};
                ventasCredito.add(datos);
            }

            for (Integer[] integers : ventasCredito) {
                System.err.println(integers[0] + "\t " + integers[1]);
            }

            //busca suma la suma de los pagos por credito
            q = "select idcreditos, SUM(pago) AS pagos FROM creditos_pagos GROUP BY idcreditos;";

            ps = inicio.gConexion.con.prepareStatement(q);
            System.out.println("Gestores.GestorReportes.reporteExtractoVista() Etapa 2:\n" + q);
            rs = ps.executeQuery();

            while (rs.next()) {
                pagosCreditos.put(rs.getInt("idcreditos"), rs.getInt("pagos"));
            }

            for (Map.Entry<Integer, Integer> entry : pagosCreditos.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                System.err.println(key + "\t" + value);
            }

            //busca suma de cantidad de ventas por credito
            q = "SELECT idcreditos, COUNT(idventas) AS cantidad FROM creditos_ventas GROUP BY idcreditos;";

            ps = inicio.gConexion.con.prepareStatement(q);
            System.out.println("Gestores.GestorReportes.reporteExtractoVista() Etapa 3:\n" + q);
            rs = ps.executeQuery();

            while (rs.next()) {
                cantidadVentas.put(rs.getInt("idcreditos"), rs.getInt("cantidad"));
            }

            for (Map.Entry<Integer, Integer> entry : cantidadVentas.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                System.err.println(key + "\t" + value);
            }

            //una vez finalizado ambas busquedas, crea una map donde se ubican los pagos de cada venta financiada
            for (Integer[] venta : ventasCredito) {
                Integer idcredito = venta[1];
                System.err.println("idcredito: "+venta[1]+", venta: "+venta[0]);
                System.err.println("idcredito: "+venta[1]+", venta: "+venta[0]);
                try {
                    creditosMontoPagado.put(venta[0], pagosCreditos.get(idcredito) / cantidadVentas.get(idcredito));
                } catch (Exception e) {
                    creditosMontoPagado.put(venta[0], 0);
                }
            }

            for (Map.Entry<Integer, Integer> entry : creditosMontoPagado.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                System.err.println(key + "\t" + value);
            }

            //System.exit(0);
            URL master = this.getClass().getResource("/reportes/extracto_detallado_por_ventas.jasper");
            System.out.println("master " + master);

            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                e.printStackTrace();
                System.exit(3);
            }

            Map parametro = new HashMap();

            JRDataSource data = new JRDataSource() {

                int inddet = -1;

                public boolean next() throws JRException {
                    return ++inddet < lista.size();
                }

                public Object getFieldValue(JRField jrf) throws JRException {
                    Object value = null;
                    Personas cliObj = (Personas) main.personasDAO.mapClientes.get(lista.get(inddet).getIdClientes());

                    if ("codCliente".equals(jrf.getName())) {
                        value = lista.get(inddet).getIdClientes();
                    }

                    if ("nroVenta".equals(jrf.getName())) {
                        value = lista.get(inddet).getId();
                    }
                    if ("factura".equals(jrf.getName())) {
                        value = "Sin Factura";
                    }
                    if ("fecha".equals(jrf.getName())) {
                        value = lista.get(inddet).getFechaPago();
                    }
                    if ("doc".equals(jrf.getName())) {
                        value = cliObj.getCINRUC();
                    }
                    if ("cliente".equals(jrf.getName())) {
                        value = cliObj.getNombreCompleto();
                    }
                    if ("total".equals(jrf.getName())) {
                        value = lista.get(inddet).getTotal();
                    }
                    if ("descuento".equals(jrf.getName())) {
                        value = lista.get(inddet).getDescuento();
                    }
                    if ("estado".equals(jrf.getName())) {
                        value = lista.get(inddet).getEstado();
                    }

                    if (lista.get(inddet).getEstado().equals("Financiado") || lista.get(inddet).getEstado().equals("Pagado Financiado")) {
                        if ("pagado".equals(jrf.getName())) {
                            try {
                                value = creditosMontoPagado.get(lista.get(inddet).getId());
                            } catch (Exception e) {
                                value = 0;
                            }
                        }
                        if ("saldo".equals(jrf.getName())) {
                            try {
                                value = lista.get(inddet).getTotal() - creditosMontoPagado.get(lista.get(inddet).getId());
                            } catch (Exception e) {
                                value = 0;
                            }

                        }
                    } else if (lista.get(inddet).getEstado().equals("Pagado")) {
                        if ("pagado".equals(jrf.getName())) {
                            value = lista.get(inddet).getTotal();
                        }
                        if ("saldo".equals(jrf.getName())) {
                            value = 0;
                        }
                    }

                    System.out.println("Value: " + value);
                    return value;
                }
            };
            if (isResultado == 1) {
                jasperPrint = JasperFillManager.fillReport(masterReport, parametro, data);
            } else {
                JOptionPane.showMessageDialog(null, "Solicitud Vacia", "Informe detallado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
            j.printStackTrace();
        }
        return jasperPrint;
    }

}
