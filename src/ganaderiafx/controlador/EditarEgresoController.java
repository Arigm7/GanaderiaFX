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
public class EditarEgresoController implements Initializable {

    @FXML
    private TextField txt_motivoEditar;
    @FXML
    private TextField txt_conceptoEditar;
    @FXML
    private Button btn_editarEgreso;
    @FXML
    private Button btn_cancelarEgreso;
    @FXML
    private DatePicker dpk_fechaModificacion;
    @FXML
    private TextArea txt_observacionesEditar;
    @FXML
    private Label lbl_nomUsuario_EditarEgreso;
    @FXML
    private TextField txt_idEgreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarEgreso(ActionEvent event) {
    }

    @FXML
    private void cancelarEgreso(ActionEvent event) {
    }
    
}
