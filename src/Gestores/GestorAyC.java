package Gestores;

import static java.lang.Thread.yield;
import java.util.ArrayList;
import modelo.balance.Balance;
import vista.inicio;
import vista.main;

public class GestorAyC {

    ArrayList<Balance> balanceArray;
    Balance balObj = null, balObjAux = null;
    Integer sw = 0;

    public GestorAyC() {
    }

    public void actualizarAyC() {
        balanceArray = main.balanceDAO.listar(" where idusuarios='" + inicio.userObj.getId() + "'", "caja");

        String fechaAux = "";
        Integer ingreso = 0, egreso = 0;
        int sw = 0;
        for (int i = 0; i < balanceArray.size(); i++) {
            if (!balanceArray.get(i).getConcepto().contains("# Cierre") && !balanceArray.get(i).getConcepto().contains("# Apertura")
                    && !balanceArray.get(i).getConcepto().contains("*Cierre") && !balanceArray.get(i).getConcepto().contains("*Apertura")) {
                if (sw == 0) {
                    fechaAux = balanceArray.get(i).getFecha();
                    ingreso = ingreso + balanceArray.get(i).getIngreso();
                    egreso = egreso + balanceArray.get(i).getEgreso();
                    sw = 1;
                } else {
                    if (balanceArray.get(i).getFecha().equals(fechaAux)) {
                        ingreso = ingreso + balanceArray.get(i).getIngreso();
                        egreso = egreso + balanceArray.get(i).getEgreso();
                    } else {

                        balObj = getCierre(fechaAux);

                        if (balObj != null) {
                            if (ingreso == egreso) {
                                balObj.setIngreso(0);
                                balObj.setEgreso(0);
                            } else {
                                balObj.setIngreso(0);
                                balObj.setEgreso(ingreso - egreso);
                            }

                            main.balanceDAO.modificar(balObj);
                        } else {

                            if (ingreso == egreso) {
                                balObj = new Balance(null, "*Cierre del Dia", 0, 0, fechaAux, inicio.userObj.getId(), "caja");
                            } else {
                                balObj = new Balance(null, "*Cierre del Dia", 0, ingreso - egreso, fechaAux, inicio.userObj.getId(), "caja");
                            }
                            main.balanceDAO.insertar(balObj);
                        }

                        balObjAux = getApertura(balanceArray.get(i).getFecha());

                        if (balObjAux != null) {
                            if (ingreso == egreso) {
                                balObjAux.setEgreso(0);
                                balObjAux.setIngreso(0);
                            } else {
                                balObjAux.setIngreso(ingreso - egreso);
                                balObjAux.setEgreso(0);
                            }

                            main.balanceDAO.modificar(balObjAux);
                        } else {

                            if (ingreso == egreso) {
                                balObjAux = new Balance(null, "*Apertura del Dia", 0, 0, balanceArray.get(i).getFecha(), inicio.userObj.getId(), "caja");
                            } else {
                                balObjAux = new Balance(null, "*Apertura del Dia", ingreso - egreso, 0, balanceArray.get(i).getFecha(), inicio.userObj.getId(), "caja");
                            }

                            main.balanceDAO.insertar(balObjAux);
                        }

                        ingreso = balanceArray.get(i).getIngreso() + ingreso - egreso;
                        egreso = balanceArray.get(i).getEgreso();
                        fechaAux = balanceArray.get(i).getFecha();
                    }
                }
            }
        }
    }

    public Balance getCierre(String fecha1) {

        for (int i = 0; i < main.balanceDAO.cierresArray.size(); i++) {
            if (main.balanceDAO.cierresArray.get(i).getFecha().equals(fecha1)) {
               // System.out.println("herramientas.GestorAyC.getCierre()" + main.balanceDAO.cierresArray.get(i).toString());
                return main.balanceDAO.cierresArray.get(i);
            }
        }

        return null;
    }

    public Balance getApertura(String fecha2) {
        for (int i = 0; i < main.balanceDAO.aperturasArray.size(); i++) {
            if (main.balanceDAO.aperturasArray.get(i).getFecha().equals(fecha2)) {
               // System.out.println("herramientas.GestorAyC.getApertura()" + main.balanceDAO.aperturasArray.get(i).toString());
                return main.balanceDAO.aperturasArray.get(i);
            }
        }

        return null;
    }
}
