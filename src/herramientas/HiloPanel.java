package herramientas;

import java.util.ArrayList;
import vista.inicio;
import vista.main;

public class HiloPanel extends Thread {

    @Override
    public void run() {
        getValorPerdidas();
        getValorInventario();
    }

    public void getValorInventario() {
        Integer sumador = 0, cantTemp = 0;
        ArrayList arr = new ArrayList();

        for (int i = 0; i < main.productosDAO.resultado.size(); i++) {
            if (!main.productosDAO.resultado.get(i).isIlimitado()) {

                cantTemp = main.jProductos.cantidadProd(main.productosDAO.resultado.get(i).getId());
                sumador = sumador + (cantTemp * main.lotesDetallesDAO.getUltimoValor(main.productosDAO.resultado.get(i).getId()));
                // System.out.println(main.productosDAO.resultado.get(i).toString()+" | Cantidad: "+cantTemp+" | Suma Total: "+sumador);
            }
        }

        main.JpanelInformes.txtValorI.setText(inicio.format.format(sumador));

    }

    public void getValorPerdidas() {
        Integer sumador = 0, cantTemp = 0;
        ArrayList arr = new ArrayList();
        main.bajasProductosDAO.actualizarLista(" where fecha BETWEEN '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/01' AND '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/31' ");

        for (int i = 0; i < main.bajasProductosDAO.resultado.size(); i++) {

            cantTemp = main.bajasProductosDAO.resultado.get(i).getCantidad();
            sumador = sumador + (cantTemp * main.lotesDetallesDAO.getUltimoValor(main.bajasProductosDAO.resultado.get(i).getIdProducto()));

        }
        main.bajasProductosDAO.actualizarLista("");
        main.JpanelInformes.txtValorP.setText(inicio.format.format(sumador));

    }

}
