
package ganaderiafx.modelo.pojos;

import java.util.Date;


public class Ingreso {
    private Integer idIngreso;
    private Integer cantidad;
    private String observaciones;
    private String fechaCreacion;
    private String fechaModificacion;
    private String catalogo;
    private String concepto;
    private Integer idCatalogoConcepto;

    public Ingreso() {
    }

    public Ingreso(Integer idIngreso, Integer cantidad, String observaciones, String fechaCreacion, String fechaModificacion, Integer idCatalogoConcepto) {
        this.idIngreso = idIngreso;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idCatalogoConcepto = idCatalogoConcepto;
    }

    public Ingreso(Integer idIngreso, Integer cantidad, String observaciones, String fechaCreacion, String fechaModificacion, String catalogo, String concepto) {
        this.idIngreso = idIngreso;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.catalogo = catalogo;
        this.concepto = concepto;
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

    
   
}
