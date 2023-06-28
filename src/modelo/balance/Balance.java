package modelo.balance;

public class Balance {

    Integer id;
    String concepto;
    Integer ingreso;
    Integer egreso;
    String fecha;
    Integer IdUsuarios;
    String tipo;

    public Balance(Integer id, String concepto, Integer ingreso, Integer egreso, String fecha, Integer IdUsuarios, String tipo) {
        this.id = id;
        this.concepto = concepto;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.fecha = fecha;
        this.IdUsuarios = IdUsuarios;
        this.tipo = tipo;
    }

    public Balance(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Balance{" + "id=" + id + ", concepto=" + concepto + ", ingreso=" + ingreso + ", egreso=" + egreso + ", fecha=" + fecha + ", IdUsuarios=" + IdUsuarios + ", tipo=" + tipo + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getIngreso() {
        return ingreso;
    }

    public void setIngreso(Integer ingreso) {
        this.ingreso = ingreso;
    }

    public Integer getEgreso() {
        return egreso;
    }

    public void setEgreso(Integer egreso) {
        this.egreso = egreso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdUsuarios() {
        return IdUsuarios;
    }

    public void setIdUsuarios(Integer IdUsuarios) {
        this.IdUsuarios = IdUsuarios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
