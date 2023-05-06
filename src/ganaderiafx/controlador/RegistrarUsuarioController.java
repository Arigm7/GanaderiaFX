
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Usuario;
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
import javafx.scene.layout.Pane;


public class RegistrarUsuarioController implements Initializable {

    @FXML
    private ComboBox<?> cmb_rolRegistrar;
    @FXML
    private TextField txt_nombreRegistrar;
    @FXML
    private TextField txt_apellidoPaternoRegistrar;
    @FXML
    private TextField txt_apellidoMaternoRegistrar;
    @FXML
    private TextField txt_usuarioRegistrar;
    @FXML
    private TextField txt_passwordRegistrar;
    @FXML
    private TextField txt_estatusRegistrar;
    @FXML
    private Button btn_registrarUsuario;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private Label lbl_nomUsuario_RegistrarUsuario;

    Usuario usuario = null;                            

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Usuario usuario){  
        this.usuario=usuario;
    }
    
    @FXML
    private void rolRegistrar(ActionEvent event) {
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
         Window.close(event);
    }
}
