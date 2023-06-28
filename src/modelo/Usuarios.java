package modelo;

public class Usuarios {

    Integer id;
    String nombre;
    String usuario;
    String pass;
    String rango;

    public Usuarios(Integer id, String nombre, String usuario, String pass, String rango) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.rango = rango;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", pass=" + pass + ", rango=" + rango + "+'}'";
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
    
    

    

}
