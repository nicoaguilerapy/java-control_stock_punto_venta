package modelo;

public class ComprasDetalles {

    Integer id;
    String Concepto;
    Integer cantidad;
    Integer costo;
    Integer idCompras;

    public ComprasDetalles(Integer id, String Concepto, Integer cantidad, Integer costo, Integer idCompras) {
        this.id = id;
        this.Concepto = Concepto;
        this.cantidad = cantidad;
        this.costo = costo;
        this.idCompras = idCompras;
    }

    public ComprasDetalles(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ComprasDetalles{" + "id=" + id + ", Concepto=" + Concepto + ", cantidad=" + cantidad + ", costo=" + costo + ", idCompras=" + idCompras + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
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

    public Integer getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(Integer idCompras) {
        this.idCompras = idCompras;
    }

}
