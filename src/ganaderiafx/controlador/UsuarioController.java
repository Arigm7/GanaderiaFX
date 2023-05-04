
package ganaderiafx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class UsuarioController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarUsuario;
    @FXML
    private Button btn_buscarUsuario;
    @FXML
    private Button btn_limpiarUsuario;
    @FXML
    private Label lbl_nombreUsuarioUsuario;
    @FXML
    private Button btn_nuevaUsuario;
    @FXML
    private Button btn_editarUsuario;
    @FXML
    private Button btn_activarUsuario;
    @FXML
    private Button btn_desactivarUsuario;
    @FXML
    private TableView<?> tbl_usuario;
    @FXML
    private TableColumn<?, ?> tcl_idUsuario;
    @FXML
    private TableColumn<?, ?> tcl_nombreUsuario;
    @FXML
    private TableColumn<?, ?> tcl_apellidoPaterno;
    @FXML
    private TableColumn<?, ?> tcl_apellidoMaterno;
    @FXML
    private TableColumn<?, ?> tcl_usuarioUsuario;
    @FXML
    private TableColumn<?, ?> tcl_password;
    @FXML
    private TableColumn<?, ?> tcl_estatusUsuario;
    @FXML
    private TableColumn<?, ?> tcl_rolUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarUsuario(ActionEvent event) {
    }

    @FXML
    private void limpiarUsuario(ActionEvent event) {
    }

    @FXML
    private void nuevaUsuario(ActionEvent event) {

    }

    @FXML
    private void editarUsuario(ActionEvent event) {
    }

    @FXML
    private void activarUsuario(ActionEvent event) {
    }

    @FXML
    private void desactivarUsuario(ActionEvent event) {
    }
    
}
