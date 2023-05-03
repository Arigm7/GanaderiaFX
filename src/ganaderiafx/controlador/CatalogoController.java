
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


public class CatalogoController implements Initializable {

    @FXML
    private Pane pnl_categoria;
    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Button btn_buscarCatalogo;
    @FXML
    private Button btn_limpiarCatalogo;
    @FXML
    private Button btn_nuevaCatalogo;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_activarCatalogo;
    @FXML
    private Button btn_desactivarCatalogo;
    @FXML
    private TableView<?> tbl_catalogo;
    @FXML
    private TableColumn<?, ?> tcl_idCatalogo;
    @FXML
    private TableColumn<?, ?> tcl_catalogo;
    @FXML
    private TableColumn<?, ?> tcl_conceptoCatalogo;
    @FXML
    private TableColumn<?, ?> tbl_estatusCatalogo;
    @FXML
    private Button btn_nuevaRol;
    @FXML
    private Button btn_editarRol;
    @FXML
    private Button btn_activarRol;
    @FXML
    private Button btn_desactivarRol;
    @FXML
    private TableView<?> tbl_rol;
    @FXML
    private TableColumn<?, ?> tcl_idRol;
    @FXML
    private TableColumn<?, ?> tcl_nombre;
    @FXML
    private TableColumn<?, ?> tcl_estatus;
    @FXML
    private TextField txt_buscarCatalogo;
    @FXML
    private Label lbl_nombreUsuarioCatalogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void nuevaCatalogo(ActionEvent event) {
    }

    @FXML
    private void editarCatalogo(ActionEvent event) {
    }

    @FXML
    private void activarCatalogo(ActionEvent event) {
    }

    @FXML
    private void desactivarCatalogo(ActionEvent event) {
    }

    @FXML
    private void nuevaRol(ActionEvent event) {
    }

    @FXML
    private void editarRol(ActionEvent event) {
    }

    @FXML
    private void activarRol(ActionEvent event) {
    }

    @FXML
    private void desactivarRol(ActionEvent event) {
    }

    @FXML
    private void buscarCatalogo(ActionEvent event) {
    }

    @FXML
    private void limpiarCatalogo(ActionEvent event) {
    }
    
}
