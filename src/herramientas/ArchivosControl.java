package herramientas;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class ArchivosControl {

    public ArchivosControl() {

    }

    public void copiar(String origen, String destino) {

        Path FROM = Paths.get(origen);
        Path TO = Paths.get(destino);
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        try {
            Files.copy(FROM, TO, options);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void mover(String origen, String destino) {
        Path origenPath = FileSystems.getDefault().getPath(origen);
        Path destinoPath = FileSystems.getDefault().getPath(destino);

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void eliminar(String origen) {
        File file = new File(origen);
        try {
            file.delete();
        } catch (Exception e) {
        }
    }
}