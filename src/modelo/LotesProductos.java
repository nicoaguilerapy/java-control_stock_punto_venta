package modelo;

public class LotesProductos {

    Integer id;
    String fecha;
    String facturanum;
    String estado;
    Integer gastototal;
    Integer ivacredito;
    Integer idProveedor;
    Integer idUsuarios;

    public LotesProductos(Integer id, String fecha, String facturanum, String estado, Integer gastototal, Integer ivacredito, Integer idProveedor, Integer idUsuarios) {
        this.id = id;
        this.fecha = fecha;
        this.facturanum = facturanum;
        this.estado = estado;
        this.gastototal = gastototal;
        this.ivacredito = ivacredito;
        this.idProveedor = idProveedor;
        this.idUsuarios = idUsuarios;
    }

    @Override
    public String toString() {
        return "LotesProductos{" + "id=" + id + ", fecha=" + fecha + ", facturanum=" + facturanum + ", estado=" + estado + ", gastototal=" + gastototal + ", ivacredito=" + ivacredito + ", idProveedor=" + idProveedor + ", idUsuarios=" + idUsuarios + '}';
    }

    public LotesProductos(Integer id) {
        this.id = id;
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

    public String getFacturanum() {
        return facturanum;
    }

    public void setFacturanum(String facturanum) {
        this.facturanum = facturanum;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getGastototal() {
        return gastototal;
    }

    public void setGastototal(Integer gastototal) {
        this.gastototal = gastototal;
    }

    public Integer getIvacredito() {
        return ivacredito;
    }

    public void setIvacredito(Integer ivacredito) {
        this.ivacredito = ivacredito;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
