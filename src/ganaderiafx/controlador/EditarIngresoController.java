/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class EditarIngresoController implements Initializable {

    @FXML
    private Label lbl_nomUsuario_EditarIngreso;
    @FXML
    private TextField txt_cantidadEditar;
    @FXML
    private TextField txt_conceptoEditar;
    @FXML
    private Button btn_editarIngreso;
    @FXML
    private Button btn_cancelarIngreso;
    @FXML
    private DatePicker dpk_fechaModificacion;
    @FXML
    private TextArea txt_observacionesEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarIngreso(ActionEvent event) {
    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
    }
    
}
