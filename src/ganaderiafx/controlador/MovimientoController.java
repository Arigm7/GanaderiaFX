
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


public class MovimientoController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarMovimiento;
    @FXML
    private Button btn_buscarMovimiento;
    @FXML
    private Button btn_limpiarMovimiento;
    @FXML
    private Button btn_nuevaIngreso;
    @FXML
    private Button btn_editarIngreso;
    @FXML
    private Button btn_activarIngreso;
    @FXML
    private Button btn_desactivarIngresos;
    @FXML
    private TableView<?> tbl_ingresos;
    @FXML
    private TableColumn<?, ?> tcl_idIngreso;
    @FXML
    private TableColumn<?, ?> tcl_cantidadIngreso;
    @FXML
    private TableColumn<?, ?> tcl_observacionesIngreso;
    @FXML
    private TableColumn<?, ?> tcl_fechaCreacionIngreso;
    @FXML
    private TableColumn<?, ?> tcl_fechaModificacionIngreso;
    @FXML
    private TableColumn<?, ?> tcl_catalogoIngreso;
    @FXML
    private TableColumn<?, ?> tcl_conceptoIngreso;
    @FXML
    private Button btn_nuevaEgreso;
    @FXML
    private Button btn_editarEgreso;
    @FXML
    private Button btn_activarEgreso;
    @FXML
    private Button btn_desactivarEgreso;
    @FXML
    private TableView<?> tbl_egreso;
    @FXML
    private TableColumn<?, ?> tcl_idEgreso;
    @FXML
    private TableColumn<?, ?> tcl_motivoEgreso;
    @FXML
    private TableColumn<?, ?> tcl_observacionesEgreso;
    @FXML
    private TableColumn<?, ?> tcl_fechaCreacionEgreso;
    @FXML
    private TableColumn<?, ?> tcl_fechaMotificacionEgreso;
    @FXML
    private TableColumn<?, ?> tcl_conceptoEgreso;
    @FXML
    private Label lbl_nomUsuario_movimiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarMovimiento(ActionEvent event) {
    }

    @FXML
    private void limpiarMovimiento(ActionEvent event) {
    }

    @FXML
    private void nuevaIngreso(ActionEvent event) {
    }

    @FXML
    private void editarIngreso(ActionEvent event) {
    }

    @FXML
    private void activarIngreso(ActionEvent event) {
    }

    @FXML
    private void desactivarIngresos(ActionEvent event) {
    }

    @FXML
    private void nuevaEgreso(ActionEvent event) {
    }

    @FXML
    private void editarEgreso(ActionEvent event) {
    }

    @FXML
    private void activarEgreso(ActionEvent event) {
    }

    @FXML
    private void desactivarEgreso(ActionEvent event) {
    }
    
}
