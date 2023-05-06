
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegistrarRazaController implements Initializable {

    @FXML
    private TextField txt_nombreRazaRegistrar;
    @FXML
    private TextField txt_estatusRazaRegistrar;
    @FXML
    private Button btn_registrarRaza;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private Label lbl_nomUsuario_RegistrarRaza;

    Raza raza = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Raza raza){  
        this.raza=raza;        
    }
    
    @FXML
    private void registrarRaza(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
         Window.close(event);
    }  
}
