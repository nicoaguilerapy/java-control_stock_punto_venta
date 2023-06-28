package modelo;

public class LotesDetalles {

    Integer id;
    Integer cantidad;
    Integer costo;
    Integer idLote;
    Integer idProducto;
    Integer idUsuarios;

    public LotesDetalles(Integer id, Integer cantidad, Integer costo, Integer idLote, Integer idProducto, Integer idUsuarios) {
        this.id = id;
        this.cantidad = cantidad;
        this.costo = costo;
        this.idProducto = idProducto;
        this.idLote = idLote;
        this.idUsuarios = idUsuarios;
    }

    public LotesDetalles(Integer idProducto, Integer cantidad) {
        this.cantidad = cantidad;
        this.idProducto = idProducto;
    }

    public LotesDetalles(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LotesDetalles{" + "id=" + id + ", cantidad=" + cantidad + ", costo=" + costo + ", idLote=" + idLote + ", idProducto=" + idProducto + ", idUsuarios=" + idUsuarios + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
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

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

}
