package modelo;

public class Compras {

    Integer id;
    String facturanum;
    String fecha;
    Integer total;
    Integer iva;
    String estado;
    String tipo;
    Integer idProveedor;
    Integer idUsuarios;

    public Compras(Integer id, String facturanum, String fecha, Integer total, Integer iva, String estado, String tipo, Integer idProveedor, Integer idUsuarios) {
        this.id = id;
        this.facturanum = facturanum;
        this.fecha = fecha;
        this.total = total;
        this.iva = iva;
        this.estado = estado;
        this.tipo = tipo;
        this.idProveedor = idProveedor;
        this.idUsuarios = idUsuarios;
    }

    @Override
    public String toString() {
        return "Compras{" + "id=" + id + ", facturanum=" + facturanum + ", fecha=" + fecha + ", total=" + total + ", iva=" + iva + ", estado=" + estado + ", tipo=" + tipo + ", idProveedor=" + idProveedor + ", idUsuarios=" + idUsuarios + '}';
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacturanum() {
        return facturanum;
    }

    public void setFacturanum(String facturanum) {
        this.facturanum = facturanum;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
