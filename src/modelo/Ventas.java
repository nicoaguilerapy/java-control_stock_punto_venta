package modelo;

public class Ventas {

    Integer id;
    String facturanum;
    String fecha;
    String fechaPago;
    Integer idClientes;
    Integer total;
    String estado;
    String formaPago;
    Integer descuento = 0;
    Integer idVendedor;
    Integer idCobrador;

    public Ventas(Integer id, String facturanum, String fecha, String fechaPago, Integer idClientes, Integer total, String estado, String formaPago, Integer descuento, Integer idVendedor, Integer idCobrador) {
        this.id = id;
        this.facturanum = facturanum;
        this.fechaPago = fechaPago;
        this.fecha = fecha;
        this.idClientes = idClientes;
        this.total = total;
        this.estado = estado;
        this.formaPago = formaPago;
        this.idVendedor = idVendedor;
        this.idCobrador = idCobrador;
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Ventas{" + "id=" + id + ", facturanum=" + facturanum + ", fecha=" + fecha + ", fechaPago=" + fechaPago + ", idClientes=" + idClientes + ", total=" + total + ", estado=" + estado + ", formaPago=" + formaPago + ", descuento=" + descuento + ", idVendedor=" + idVendedor + ", idCobrador=" + idCobrador + '}';
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getIdCobrador() {
        return idCobrador;
    }

    public void setIdCobrador(Integer idCobrador) {
        this.idCobrador = idCobrador;
    }

    public Ventas(Integer id) {
        this.id = id;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFacturanum() {
        return facturanum;
    }

    public void setFacturanum(String facturanum) {
        this.facturanum = facturanum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getTotalACobrar() {
        return (this.total - this.descuento);
    }

    public Integer getIvaACobrar() {
        return (this.total - this.descuento) / 11;
    }

}
