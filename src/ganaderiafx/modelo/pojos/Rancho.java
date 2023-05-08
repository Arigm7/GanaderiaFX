
package ganaderiafx.modelo.pojos;

public class Rancho {
    private Integer idRancho;
    private String nombre;
    private String colonia;
    private String calle;
    private Integer numExt;
    private String estatus;
    private String usuario;
    private Integer idUsuario;

    public Rancho() {
    }

    public Rancho(Integer idRancho, String nombre, String colonia, String calle, Integer numExt, String estatus, Integer idUsuario) {
        this.idRancho = idRancho;
        this.nombre = nombre;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.estatus = estatus;
        this.idUsuario = idUsuario;
    }

    public Rancho(Integer idRancho, String nombre, String colonia, String calle, Integer numExt, String estatus, String usuario) {
        this.idRancho = idRancho;
        this.nombre = nombre;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.estatus = estatus;
        this.usuario = usuario;
    }

    public Integer getIdRancho() {
        return idRancho;
    }

    public void setIdRancho(Integer idRancho) {
        this.idRancho = idRancho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumExt() {
        return numExt;
    }

    public void setNumExt(Integer numExt) {
        this.numExt = numExt;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
