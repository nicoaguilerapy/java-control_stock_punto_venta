package modelo.persona;

public class Clientes extends Personas {

    public Clientes(Integer id, String CINRUC, String nombres, String apellidos, String direccion, String fecha, String celtel, String correo, Integer idUsuarios, String ciudad) {
        super(id, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, idUsuarios, ciudad);
        this.tipo = "clientes";
    }

    public Clientes(Integer id) {
        super(id);
        this.tipo = "clientes";
    }

}
