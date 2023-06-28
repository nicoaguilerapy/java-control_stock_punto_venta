package modelo;

public class VentasDetalles {

    Integer id;
    Integer idVentas;
    Integer idProductos;
    Integer cantidad;
    Integer iva5;
    Integer iva10;
    Integer precio;
    Integer subtotal;

    public VentasDetalles(Integer id, Integer idVentas, Integer idProductos, Integer cantidad, Integer iva5, Integer iva10, Integer precio, Integer subtotal) {
        this.id = id;
        this.idVentas = idVentas;
        this.idProductos = idProductos;
        this.cantidad = cantidad;
        this.iva5 = iva5;
        this.iva10 = iva10;
        this.subtotal = subtotal;
        this.precio = precio;
    }

    public VentasDetalles(Integer idProductos, Integer cantidad) {
        this.idProductos = idProductos;
        this.cantidad = cantidad;
    }

    public VentasDetalles(Integer idVentas) {
        this.idVentas = idVentas;
    }

    @Override
    public String toString() {
        return "DetallesVentas{" + "idVentas=" + idVentas + '}';
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getIva5() {
        return iva5;
    }

    public void setIva5(Integer iva5) {
        this.iva5 = iva5;
    }

    public Integer getIva10() {
        return iva10;
    }

    public void setIva10(Integer iva10) {
        this.iva10 = iva10;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Integer idVentas) {
        this.idVentas = idVentas;
    }

    public Integer getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Integer idProductos) {
        this.idProductos = idProductos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

}
