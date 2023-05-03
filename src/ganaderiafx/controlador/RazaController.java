
package ganaderiafx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class RazaController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarRaza;
    @FXML
    private Button btn_buscarRaza;
    @FXML
    private Button btn_limpiarRaza;
    @FXML
    private Label lbl_nombreUsuarioRaza;
    @FXML
    private Button btn_nuevaRaza;
    @FXML
    private Button btn_editarRaza;
    @FXML
    private Button btn_activarRaza;
    @FXML
    private Button btn_desactivarRaza;
    @FXML
    private TableView<?> tbl_raza;
    @FXML
    private TableColumn<?, ?> tcl_idRaza;
    @FXML
    private TableColumn<?, ?> tcl_nombreRaza;
    @FXML
    private TableColumn<?, ?> tcl_estatusRaza;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarRaza(ActionEvent event) {
    }


    @FXML
    private void nuevaRaza(ActionEvent event) {
    }

    @FXML
    private void editarRaza(ActionEvent event) {
    }

    @FXML
    private void activarRaza(ActionEvent event) {
    }

    @FXML
    private void desactivarRaza(ActionEvent event) {
    }

    @FXML
    private void limpiarRaza(ActionEvent event) {
    }
    
}
