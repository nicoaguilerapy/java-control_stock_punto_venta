package modelo.persona;

public class Proveedores extends Personas {

    String empresaNombre;

    public Proveedores(Integer id, String CINRUC, String nombres, String apellidos, String direccion, String fecha, String celtel, String correo, Integer idUsuarios, String ciudad, String empresaNombre) {
        super(id, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, idUsuarios, ciudad);
        this.empresaNombre = empresaNombre;
        this.tipo = "proveedores";
    }

    public Proveedores(Integer id) {
        super(id);
        this.tipo = "proveedores";
    }

    @Override
    public String toString() {
        return super.toString() + "Proveedores{" + "empresaNombre=" + empresaNombre + '}';
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

}
