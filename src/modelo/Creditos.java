package modelo;

public class Creditos {

    Integer id;
    Integer idClientes;
    String estado;
    Integer idUsuarios;

    public Creditos(Integer id, Integer idClientes, String estado, Integer idUsuarios) {
        this.id = id;
        this.idClientes = idClientes;
        this.estado = estado;
        this.idUsuarios = idUsuarios;
    }

    public Creditos(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deudas{" + "id=" + id + ", idClientes=" + idClientes + ", estado=" + estado + ", idUsuarios=" + idUsuarios + '}';
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
