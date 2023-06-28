package modelo;

public class CreditosPagos {

    Integer id;
    String fecha;
    Integer idDeudas;
    Integer pago;
    Integer idUsuarios;

    public CreditosPagos(Integer id, String fecha, Integer idDeudas, Integer pago, Integer idUsuarios) {
        this.id = id;
        this.idDeudas = idDeudas;
        this.fecha = fecha;
        this.pago = pago;
        this.idUsuarios = idUsuarios;
    }

    public CreditosPagos(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeudasPagos{" + "id=" + id + ", fecha=" + fecha + ", idDeudas=" + idDeudas + ", pago=" + pago + ", idUsuarios=" + idUsuarios + '}';
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

    public Integer getIdDeudas() {
        return idDeudas;
    }

    public void setIdDeudas(Integer idDeudas) {
        this.idDeudas = idDeudas;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
