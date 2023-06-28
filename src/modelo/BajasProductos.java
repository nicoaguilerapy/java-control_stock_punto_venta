package modelo;

public class BajasProductos {

    Integer id;
    String fecha;
    String motivo;
    Integer cantidad;
    Integer idProducto;
    Integer idUsuarios;

    public BajasProductos(Integer id, String fecha, String motivo, Integer cantidad, Integer idProducto, Integer idUsuarios) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.idUsuarios = idUsuarios;
    }

    public BajasProductos(Integer idProducto, Integer cantidad) {
        this.cantidad = cantidad;
        this.idProducto = idProducto;
    }

    public BajasProductos(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BajasProductos{" + "id=" + id + ", fecha= " + fecha + " motivo=" + motivo + ", cantidad=" + cantidad + ", idProducto=" + idProducto + ", idUsuarios=" + idUsuarios + '}';
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
