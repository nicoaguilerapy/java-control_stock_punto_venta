package modelo;

public class CreditosVentas {

    Integer id;
    String fecha;
    Integer idDeudas;
    Integer idVentas;
    Integer idUsuarios;

    public CreditosVentas(Integer id, String fecha, Integer idDeudas, Integer idVentas, Integer idUsuarios) {
        this.id = id;
        this.idDeudas = idDeudas;
        this.fecha = fecha;
        this.idVentas = idVentas;
        this.idUsuarios = idUsuarios;
    }

    public CreditosVentas(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeudasVentas{" + "id=" + id + ", fecha=" + fecha + ", idDeudas=" + idDeudas + ", idVentas=" + idVentas + ", idUsuarios=" + idUsuarios + '}';
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fech) {
        this.fecha = fech;
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

    public Integer getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Integer idVentas) {
        this.idVentas = idVentas;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

}
