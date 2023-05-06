
package ganaderiafx.modelo.pojos;

import java.util.Date;


public class Ingreso {
    private Integer idIngreso;
    private Integer cantidad;
    private String observaciones;
    private Date fechaCreacion;
    private Date fechaModficacion;
    private Integer idCatalogoConcepto;

    public Ingreso() {
    }

    public Ingreso(Integer idIngreso, Integer cantidad, String observaciones, Date fechaCreacion, Date fechaModficacion, Integer idCatalogoConcepto) {
        this.idIngreso = idIngreso;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.fechaModficacion = fechaModficacion;
        this.idCatalogoConcepto = idCatalogoConcepto;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModficacion() {
        return fechaModficacion;
    }

    public void setFechaModficacion(Date fechaModficacion) {
        this.fechaModficacion = fechaModficacion;
    }

    public Integer getIdCatalogoConcepto() {
        return idCatalogoConcepto;
    }

    public void setIdCatalogoConcepto(Integer idCatalogoConcepto) {
        this.idCatalogoConcepto = idCatalogoConcepto;
    }
    
    
    
}
