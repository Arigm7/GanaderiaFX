
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class EditarCatalogoController implements Initializable {

    @FXML
    private TextField txt_conceptoEditar;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private ComboBox<?> cmb_catalogoEditar;

    CatalogoConcepto catalogo = null;
    Boolean isnew=false;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(CatalogoConcepto catalogo, Boolean isnew){  
        this.catalogo=catalogo;
        this.isnew=isnew;
        this.cargarCatalogo();
    }
    
    @FXML
    private void editarCatalogo(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
    }

    
    public void cargarCatalogo(){                                   //FALTAAAAAAAAA
        
        this.txt_conceptoEditar.setText(catalogo.getConcepto());
        
      

    }
}
