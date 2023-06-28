package modelo;

public class Datos {

    Integer id;
    String atributo;
    String valor;

    public Datos(Integer id, String atributo, String valor) {
        this.id = id;
        this.atributo = atributo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Datos{" + "id=" + id + ", atributo=" + atributo + ", valor=" + valor + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
