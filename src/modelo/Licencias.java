package modelo;

public class Licencias {

    Integer id;
    String nombre;
    String licencia;
    String url;
    String db;
    String usuario;
    String pass;
    Integer categoria;
    Integer estado;
    String fecha;
    

    public Licencias(Integer id, String nombre, String licencia, String url, String db, String usuario, String pass, Integer categoria, Integer estado, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.licencia = licencia;
        this.url = url;
        this.db = db;
        this.usuario = usuario;
        this.pass = pass;
        this.categoria = categoria;
        this.estado = estado;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Licencias{" + "id=" + id + ", nombre=" + nombre + ", licencia=" + licencia + ", url=" + url + ", db=" + db + ", usuario=" + usuario + ", pass=" + pass + ", categoria=" + categoria + ", estado=" + estado + ", fecha=" + fecha + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

        
    

    
 }
