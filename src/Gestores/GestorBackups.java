package Gestores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import javax.swing.JOptionPane;
import vista.inicio;

public class GestorBackups extends Thread {

    @Override
    public void run() {
        try {
            createBackup();
        } catch (Exception e) {

        }
        yield();
    }

    public void createBackup() {
        try {

            CodeSource codeSource = GestorBackups.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


            
            String dbName = "miempre";
            String dbUser = "backup";
            String dbPass = "7ccc2ed53aa71e53842f9d5176bdef76";

       
            String folderPath = jarDir + "\\backup";


            File f1 = new File(folderPath);
            f1.mkdir();


            String savePath = "\"" + jarDir + "\\backup\\" + "backup"+inicio.gFechas.getFechaActual()+".sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --databases " + dbName + " -r " + savePath;
            
            System.out.println("herramientas.GestorBackups.createBackup()"+executeCmd);

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Creada con éxito", "Copia de Seguridad", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error, copia incompleta o detenida", "Copia de Seguridad", JOptionPane.ERROR_MESSAGE);
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
           JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage(), "Copia de Seguridad", JOptionPane.ERROR_MESSAGE);
        }
    }

}
