
package ganaderiafx.modelo.pojos;

public class CatalogoConcepto {
    private Integer idCatalogoConcepto;
    private String catalogo;
    private String concepto;
    private String estatus;

    public CatalogoConcepto() {
    }

    public CatalogoConcepto(Integer idCatalogoConcepto, String catalogo, String concepto, String estatus) {
        this.idCatalogoConcepto = idCatalogoConcepto;
        this.catalogo = catalogo;
        this.concepto = concepto;
        this.estatus = estatus;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
