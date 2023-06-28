package modelo;

public class Pedidos {

    Integer id;
    String pedido;
    String detalles;
    String estado;
    String fecha;
    Integer idUsuarios;

    public Pedidos(Integer id, String pedido, String detalles, String estado, String fecha, Integer idUsuarios) {
        this.id = id;
        this.pedido = pedido;
        this.detalles = detalles;
        this.estado = estado;
        this.fecha = fecha;
        this.idUsuarios = idUsuarios;
    }

    public Pedidos(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "id=" + id + ", pedido=" + pedido + ", detalles=" + detalles + ", estado=" + estado + ", fecha=" + fecha + ", idUsuarios=" + idUsuarios + '}';
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
