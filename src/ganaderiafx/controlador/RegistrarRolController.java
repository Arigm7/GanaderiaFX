
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Rol;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class RegistrarRolController implements Initializable {

    @FXML
    private TextField txt_estatusRegistrar;
    @FXML
    private Button btn_registrarRol;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private TextField txt_nombreRegistro;
    @FXML
    private Label lbl_nomUsuario_RegistrarRol;

    Rol rol = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Rol rol){  
        this.rol=rol;   
    }
    
    @FXML
    private void registrarRol(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Window.close(event);
    }
    
}
