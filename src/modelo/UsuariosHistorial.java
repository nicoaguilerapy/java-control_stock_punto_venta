
package modelo;

public class UsuariosHistorial {
    
    Integer id;
    Integer idUsuarios;
    String fecha;
    String hora;
    String ip;
    String host;

    public UsuariosHistorial(Integer id, Integer idUsuarios, String fecha, String hora, String ip, String host) {
        this.id = id;
        this.idUsuarios = idUsuarios;
        this.fecha = fecha;
        this.hora = hora;
        this.ip = ip;
        this.host = host;
    }

    @Override
    public String toString() {
        return "UsuariosHistorial{" + "id=" + id + ", idUsuarios=" + idUsuarios + ", fecha=" + fecha + ", hora=" + hora + ", ip=" + ip + ", host=" + host + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
    
}
