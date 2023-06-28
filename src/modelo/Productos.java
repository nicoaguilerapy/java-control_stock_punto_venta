package modelo;

public class Productos {

    Integer id;
    String codigo;
    String nombre;
    Integer valor1;
    Integer valor2;
    Integer valor3;
    String estado;
    boolean ilimitado;
    Integer iva;
    Integer idUsuarios;

    public Productos(Integer id, String codigo, String nombre, Integer valor1, Integer valor2, Integer valor3, String estado, boolean ilimitado, Integer iva, Integer idUsuarios) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
        this.estado = estado;
        this.ilimitado = ilimitado;
        this.iva = iva;
        this.idUsuarios = idUsuarios;
    }

    public Productos(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Productos{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", valor1=" + valor1 + ", valor2=" + valor2 + ", valor3=" + valor3 + ", estado=" + estado + ", ilimitado=" + ilimitado + ", iva=" + iva + ", idUsuarios=" + idUsuarios + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }

    public Integer getValor2() {
        return valor2;
    }

    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    public Integer getValor3() {
        return valor3;
    }

    public void setValor3(Integer valor3) {
        this.valor3 = valor3;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isIlimitado() {
        return ilimitado;
    }

    public void setIlimitado(boolean ilimitado) {
        this.ilimitado = ilimitado;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    
    
}
