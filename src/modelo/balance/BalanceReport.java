package modelo.balance;

public class BalanceReport {

    String fecha;
    String concepto;
    String ingreso;
    String egreso;

    public BalanceReport(String fecha, String concepto, String ingreso, String egreso) {
        this.fecha = fecha;
        this.concepto = concepto;
        this.ingreso = ingreso;
        this.egreso = egreso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

   

}
