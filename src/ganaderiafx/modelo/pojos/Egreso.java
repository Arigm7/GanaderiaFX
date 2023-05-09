
package ganaderiafx.modelo.pojos;

import java.util.Date;


public class Egreso {
    
    private Integer idEgreso;
    private String motivo;
    private String observaciones;
    private String fechaCreacion;
    private String fechaModificacion;
    private String catalogo;
    private String concepto;
    private String estatus;
    private String rancho;
    private String usuario;
    private Integer idCatalogoConcepto;

    public Egreso() {
    }

    public Egreso(Integer idEgreso, String motivo, String observaciones, String fechaCreacion, String fechaModificacion, Integer idCatalogoConcepto) {
        this.idEgreso = idEgreso;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idCatalogoConcepto = idCatalogoConcepto;
    }

    public Egreso(Integer idEgreso, String motivo, String observaciones, String fechaCreacion, String fechaModificacion, String catalogo, String concepto, String estatus, String rancho, String usuario) {
        this.idEgreso = idEgreso;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.catalogo = catalogo;
        this.concepto = concepto;
        this.estatus = estatus;
        this.rancho = rancho;
        this.usuario = usuario;
    }

    public Integer getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdCatalogoConcepto() {
        return idCatalogoConcepto;
    }

    public void setIdCatalogoConcepto(Integer idCatalogoConcepto) {
        this.idCatalogoConcepto = idCatalogoConcepto;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getRancho() {
        return rancho;
    }

    public void setRancho(String rancho) {
        this.rancho = rancho;
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
    
    
    
}
