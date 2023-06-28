package herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Archivo {

    private String resultado = "", aux = "";
    private String[] sql;
    private File file;
    private String path;

    private FileWriter fw = null;
    private BufferedWriter bw = null;

    private FileReader fr = null;
    private BufferedReader br = null;

    public Archivo(String path) {
        this.path = path;
    }

    public boolean exist() {

        try {
            file = new File(path);
        } catch (Exception e) {
            System.out.println("No existe el archivo");
        }

        return file.exists();

    }

    public File File() {

        return file;

    }

    public boolean crear(String texto) {

        try {
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error al crear archivo");
            return false;
        }

    }

    public boolean agregar(String texto) {

        try {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(texto);
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error al crear archivo");
            return false;
        }

    }

    public String leer() {

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            while (br.ready() == true) {

                aux = br.readLine();
                resultado = resultado + aux;
            }

            System.out.println("Archivo leido: " + resultado);

            return resultado;
        } catch (IOException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
        return resultado;
    }

}
