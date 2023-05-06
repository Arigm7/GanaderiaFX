
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class RegistrarCatalogoController implements Initializable {

    @FXML
    private Label lbl_nomUsuario_RegistrarCatalogo;
    @FXML
    private TextField txt_conceptoRegistrar;
    @FXML
    private Button btn_registrarCatalogo;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private ComboBox<?> cmb_catalogoRegistrar;

    CatalogoConcepto catalogo = null;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(CatalogoConcepto catalogo){  
        this.catalogo=catalogo;   
    }
        
    @FXML
    private void registrarCatalogo(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void catalogoRegistrar(ActionEvent event) {
    }
    
}
