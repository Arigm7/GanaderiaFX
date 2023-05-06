
package ganaderiafx.modelo.pojos;


public class Raza {
    private Integer idRaza;
    private String nombre;
    private String estatus;

    public Raza() {
    }

    public Raza(Integer idRaza, String nombre, String estatus) {
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
