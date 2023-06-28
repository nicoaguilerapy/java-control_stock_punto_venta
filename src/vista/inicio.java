package vista;

import herramientas.Archivo;
import herramientas.ArchivosControl;
import Gestores.GestorAyC;
import Gestores.GestorFechas;
import Gestores.GestorConexion;
import Gestores.GestorReportes;
import Gestores.GestorInformes;
import Gestores.GestorScripts;
import herramientas.Tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Licencias;
import modelo.Usuarios;

public class inicio {

    public static Connection con = null;
    public static String licencia;
    public static Licencias lic = null;
    public static String path = "licencia.txt";
    public static Archivo licArchivo;

    public static ArchivosControl ac;
    public static GestorFechas gFechas;
    public static GestorConexion gConexion;
    public static GestorReportes gReportes;
    public static GestorInformes gInformes;
    public static GestorScripts gScripts;
    public static GestorAyC gAyC;
    public static Tools tools;
    public static Usuarios userObj;

    public static DecimalFormat formatID = new DecimalFormat("000000");
    public static DecimalFormat format = new DecimalFormat("###,###.##");

    public static String plataRed = "MIMEUHNHNJA7ATA";
    public static String plataLocal = "MIME9AYQOIQIF7B";

    public static String serverRed = "192.168.0.100";
    public static String serverLocal = "localhost";

    public static String db = "miempre";
    public static Jlogin login;
    public static String ver = "4.12";
    public static String version = " MiEmpre " + ver;

    public static String fechaDMY;
    public static String fechaYMD;

    public static void main(String[] args) {

        //iniciar gestores
        licArchivo = new Archivo(path);
        ac = new ArchivosControl();
        gFechas = new GestorFechas();
        gReportes = new GestorReportes();
        gInformes = new GestorInformes();
        gAyC = new GestorAyC();

        tools = new Tools();

        fechaDMY = gFechas.getFechaActualBarras();
        fechaYMD = gFechas.invertir(fechaDMY);

        gConexion = new GestorConexion();
        gConexion.comprobar();
        empezarPrograma();

    }

    public static void empezarPrograma() {
        main main = new main();

        try {
            login = new Jlogin();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        } catch (Exception e) {
            System.out.println("Error al Iniciar ventana Login");
            System.out.println("Mensaje: " + e.getMessage());
        }

        main.start();
    }

    public static void restartApplication() throws IOException, URISyntaxException {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        /* is it a jar file? */
        if (!currentJar.getName().endsWith(".jar")) {
            return;
        }

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }

}
