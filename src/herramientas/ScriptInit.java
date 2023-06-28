package herramientas;

import java.util.ArrayList;
import modelo.Compras;
import modelo.LotesProductos;
import modelo.balance.Balance;
import vista.inicio;
import vista.main;

public class ScriptInit {

    ArrayList<LotesProductos> arrayLot;
    ArrayList<Compras> arrayComp;
    Balance balCompra, balAnulado;

    public void run() {

        loteproductos();
        compras();
    }

    private void loteproductos() {
        arrayLot = new ArrayList<>();
        arrayLot = main.lotesProductosDAO.listar(" where estado='Anulado' and gastototal<>0 ");

        for (int i = 0; i < arrayLot.size(); i++) {
            System.out.println("----------");
            System.out.println("Lote Producto: " + arrayLot.get(i).toString());
            balCompra = null;
            balAnulado = null;

            try {
                balCompra = (Balance) main.balanceDAO.listar(" where concepto='#Compra de Mercaderías ID: " + arrayLot.get(i).getId() + "'", "caja").get(0);
                //Caja cajaObj = new Caja(null, "#Anulacion Compra de Mercaderías ID: " + arrayLot.get(i).getId(), bal.getEgreso(), 0, inicio.fechaYMD, inicio.userObj.getId());
                //main.balanceDAO.insertar(cajaObj);
                balCompra.setTipo("caja");
                System.out.println("Compra: " + balCompra.toString());
            } catch (Exception e) {
                balCompra = null;
            }

            try {
                balAnulado = (Balance) main.balanceDAO.listar(" where concepto='#Anulacion Compra de Mercaderías ID: " + arrayLot.get(i).getId() + "'", "caja").get(0);
                balAnulado.setTipo("caja");
                System.out.println("Anulacion: " + balAnulado.toString());
                /**/

            } catch (Exception e) {
                balAnulado = null;
            }

            if (balAnulado != null && balCompra != null) {
                if (!balCompra.getFecha().equals(balAnulado.getFecha())) {
                    balAnulado.setFecha(balCompra.getFecha());
                    System.out.println("Anulacion Modificada: " + balAnulado.toString());
                }
            } else {
                if (balAnulado == null && balCompra != null) {
                    Balance cajaObj = new Balance(null, "#Anulacion Compra de Mercaderías ID: " + arrayLot.get(i).getId(), balCompra.getEgreso(), 0, balCompra.getFecha(), inicio.userObj.getId(), "caja");
                    main.balanceDAO.insertar(cajaObj);
                    System.out.println("Anulacion Creada: " + cajaObj.toString());
                }
            }
        }

        System.out.println("----------");

    }

    private void compras() {
        arrayComp = new ArrayList<>();
        arrayComp = main.comprasDAO.listar(" where estado='Anulado' and total<>0 ");

        for (int i = 0; i < arrayComp.size(); i++) {
            System.out.println("----------");
            System.out.println("Compras: " + arrayLot.get(i).toString());
            balCompra = null;
            balAnulado = null;

            try {
                balCompra = (Balance) main.balanceDAO.listar(" where concepto='#Compra de Articulos ID: " + arrayComp.get(i).getId() + "'", "caja").get(0);
                //Caja cajaObj = new Caja(null, "#Anulacion Compra de Mercaderías ID: " + arrayLot.get(i).getId(), bal.getEgreso(), 0, inicio.fechaYMD, inicio.userObj.getId());
                //main.balanceDAO.insertar(cajaObj);
                balCompra.setTipo("caja");
                System.out.println("Compra: " + balCompra.toString());
            } catch (Exception e) {
                balCompra = null;
            }

            try {
                balAnulado = (Balance) main.balanceDAO.listar(" where concepto='#Anulacion Compra de Articulos ID: " + arrayComp.get(i).getId() + "'", "caja").get(0);
                balAnulado.setTipo("caja");
                System.out.println("Anulacion: " + balAnulado.toString());
                /**/

            } catch (Exception e) {
                balAnulado = null;
            }

            if (balAnulado != null && balCompra != null) {
                if (!balCompra.getFecha().equals(balAnulado.getFecha())) {
                    balAnulado.setFecha(balCompra.getFecha());
                    System.out.println("Anulacion Modificada: " + balAnulado.toString());
                }
            } else {
                if (balAnulado == null && balCompra != null) {
                    Balance cajaObj = new Balance(null, "#Anulacion Compra de Articulos ID: " + arrayLot.get(i).getId(), balCompra.getEgreso(), 0, balCompra.getFecha(), inicio.userObj.getId(), "caja");
                    main.balanceDAO.insertar(cajaObj);
                    System.out.println("Anulacion Creada: " + cajaObj.toString());
                }
            }
        }

        System.out.println("----------");

    }

}
