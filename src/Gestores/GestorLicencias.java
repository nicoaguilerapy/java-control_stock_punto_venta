package Gestores;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import modelo.Licencias;
import static vista.inicio.licArchivo;

public class GestorLicencias {

    public static String licencia = "";
    private static String plataRed = "MIMEUHNHNJA7ATA";
    private static String plataLocal = "MIME9AYQOIQIF7B";

    private static String serverRed = "192.168.0.100";
    private static String serverLocal = "localhost";

    private static String db = "miempre";

    public Licencias licObj = null;

    public GestorLicencias() {

        if (!licArchivo.exist()) {
            licencia = JOptionPane.showInputDialog("Inserte su licencia");
            licArchivo.crear(licencia);
        } else {
            licencia = licArchivo.leer();
        }

        if (licencia.equals("")) {
            licencia = JOptionPane.showInputDialog("Inserte su licencia");
            licArchivo.crear(licencia);
        }

        if (licencia.equals(plataLocal)) {
            licObj = new Licencias(null, "miempre", plataLocal, serverLocal, db, "root", "xP*#9CFua3&K", 2, 1, "");
            System.out.println(licObj.toString());
        } else {

            if (licencia.equals(plataRed)) {
                
                String ipConectar = "";
                int sw = 0;

                while (sw == 0) {
                    ipConectar = JOptionPane.showInputDialog("Ingresar el IP a conectar");
                    try {
                        System.out.println("herramientas.GestorLicencias.<init>() IP:"+ipConectar);
                        if (ipConectar.contains("192.168.")) {
                            sw = 1;
                        }
                    } catch (NumberFormatException e) {
                    }
                }

                licObj = new Licencias(null, "miempre", plataRed, ipConectar, db, "root", "xP*#9CFua3&K", 2, 1, "");
                System.out.println(licObj.toString());
            } else {
                System.out.println("Error de Licencia");
            }
        }
    }

}
