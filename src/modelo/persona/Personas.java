package modelo.persona;

public class Personas {

    Integer id;
    String CINRUC;
    String nombres;
    String apellidos;
    String direccion;
    String fecha;
    String celtel;
    String correo;
    Integer idUsuarios;
    String ciudad;
    String tipo;

    public Personas(Integer id, String CINRUC, String nombres, String apellidos, String direccion, String fecha, String celtel, String correo, Integer idUsuarios, String ciudad) {
        this.id = id;
        this.CINRUC = CINRUC;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha = fecha;
        this.celtel = celtel;
        this.correo = correo;
        this.idUsuarios = idUsuarios;
        this.ciudad = ciudad;
    }

    public Personas(Integer id, String CINRUC, String nombres, String apellidos, String direccion, String fecha, String celtel, String correo, Integer idUsuarios, String ciudad, String tipo) {
        this.id = id;
        this.CINRUC = CINRUC;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha = fecha;
        this.celtel = celtel;
        this.correo = correo;
        this.idUsuarios = idUsuarios;
        this.ciudad = ciudad;
        this.tipo = tipo;
    }


    public Personas(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", CINRUC=" + CINRUC + ", nombres=" + nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", fecha=" + fecha + ", celtel=" + celtel + ", correo=" + correo + ", idUsuarios=" + idUsuarios + ", ciudad=" + ciudad + ", tipo=" + tipo + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCINRUC() {
        return CINRUC;
    }

    public void setCINRUC(String CINRUC) {
        this.CINRUC = CINRUC;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCeltel() {
        return celtel;
    }

    public void setCeltel(String celtel) {
        this.celtel = celtel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreCompleto(){
        return nombres+" "+apellidos;
    }
}
