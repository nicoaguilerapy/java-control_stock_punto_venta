package vista;

import vista.reportes.JinformesPanel;
import DAO.BalanceDAO;
import DAO.EmpleadosDAO;
import DAO.LotesProductosDAO;
import DAO.DatosDAO;
import DAO.CreditosDAO;
import DAO.LotesDetallesDAO;
import DAO.PersonasDAO;
import DAO.CreditosVentasDAO;
import DAO.UsuariosDAO;
import DAO.ProveedoresDAO;
import DAO.AcreedoresDAO;
import DAO.UsuariosHistorialDAO;
import DAO.ComprasDAO;
import DAO.BajasProductosDAO;
import DAO.PedidosDAO;
import DAO.VentasDetallesDAO;
import DAO.VentasDAO;
import DAO.ProductosDAO;
import DAO.ComprasDetallesDAO;
import DAO.CreditosPagosDAO;

public class main {

    //DAO varios
    public static UsuariosDAO usuariosDAO;
    public static UsuariosHistorialDAO usuariosHistorialDAO;
    public static DatosDAO datosDAO;
    public static PedidosDAO pedidosDAO;
    public static EmpleadosDAO empleadosDAO;

    //DAO multiples
    public static BalanceDAO balanceDAO;
    public static AcreedoresDAO acreedoresDAO;
    public static PersonasDAO personasDAO;

    //DAO deudas
    public static CreditosDAO creditosDAO;
    public static CreditosVentasDAO creditosVentasDAO;
    public static CreditosPagosDAO creditosPagosDAO;

    //DAO facturacion
    public static ProveedoresDAO proveedoresDAO;
    public static ProductosDAO productosDAO;
    public static VentasDAO ventasDAO;
    public static VentasDetallesDAO detVentasDAO;
    public static LotesProductosDAO lotesProductosDAO;
    public static LotesDetallesDAO lotesDetallesDAO;
    public static BajasProductosDAO bajasProductosDAO;
    public static ComprasDAO comprasDAO;
    public static ComprasDetallesDAO comprasDetallesDAO;

    //Jframes varios
    public static Jusuarios jUsuarios;
    public static JusuariosHistorial jUHistorial;
    public static Jempleados jEmpleados;
    public static Jpedidos jPedidos;
    public static Jconfiguracion Jconfig;
    public static JinformesPanel JpanelInformes;

    //Jframes Balance
    public static JbancoN jBanco;
    public static Jcaja jCaja;
    public static JtarjetaN jTarjeta;

    //Jframes Prestamos
    public static Jacreedores jAcreedores;

    //Jframes deudas
    public static Jcreditos jDeudas;

    //Jframes facturacion
    public static Jclientes jClientes;
    public static Jproveedores jProveedores;
    public static Jproductos jProductos;
    public static Jventas jVentas;
    public static Jcobranzas jCobranzas;
    public static JivaDebito jIvaDebito;
    public static JlotesProductos jLoteProductos;
    public static Jcompras jCompras;

    public main() {

        usuariosDAO = new UsuariosDAO();
        usuariosHistorialDAO = new UsuariosHistorialDAO();
        datosDAO = new DatosDAO();
        empleadosDAO = new EmpleadosDAO();

        balanceDAO = new BalanceDAO();
        acreedoresDAO = new AcreedoresDAO();
        personasDAO = new PersonasDAO();

        creditosDAO = new CreditosDAO();
        creditosVentasDAO = new CreditosVentasDAO();
        creditosPagosDAO = new CreditosPagosDAO();

        proveedoresDAO = new ProveedoresDAO();
        productosDAO = new ProductosDAO();
        ventasDAO = new VentasDAO();
        detVentasDAO = new VentasDetallesDAO();
        pedidosDAO = new PedidosDAO();
        lotesProductosDAO = new LotesProductosDAO();
        lotesDetallesDAO = new LotesDetallesDAO();
        bajasProductosDAO = new BajasProductosDAO();
        comprasDAO = new ComprasDAO();
        comprasDetallesDAO = new ComprasDetallesDAO();
    }

    public boolean start() {
        try {
            usuariosDAO.start();
            usuariosHistorialDAO.start();
            datosDAO.start();
            pedidosDAO.start();
            empleadosDAO.start();

            balanceDAO.start();
            acreedoresDAO.start();
            personasDAO.start();

            creditosDAO.start();
            creditosVentasDAO.start();
            creditosPagosDAO.start();

            proveedoresDAO.start();
            productosDAO.start();
            ventasDAO.start();
            detVentasDAO.start();
            lotesProductosDAO.start();
            lotesDetallesDAO.start();
            bajasProductosDAO.start();
            comprasDAO.start();
            comprasDetallesDAO.start();

            return true;
        } catch (Exception e) {
            System.out.println("vista.main.start() error al iniciar hilos");
            return false;
        }
    }
}
