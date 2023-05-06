/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.modelo.pojos;

/**
 *
 * @author Alex
 */
public class Rol {
    private Integer idRol;
    private String nombre;
    private String estatus;

    public Rol() {
    }

    public Rol(Integer idRol, String nombre, String estatus) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
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
