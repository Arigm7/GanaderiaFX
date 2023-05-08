/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Ingreso;
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
public class RegistrarIngresoController implements Initializable {

    @FXML
    private TextField txt_cantidadRegistrar;
    @FXML
    private TextField txt_conceptoRegistrar;
    @FXML
    private Button btn_registrarIngreso;
    @FXML
    private Button btn_cancelarIngreso;
    @FXML
    private Label lbl_nomUsuario_RegistrarIngreso;
    @FXML
    private TextArea txt_observacionesRegistro;

    Ingreso ingreso = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Ingreso ingreso){  
        this.ingreso=ingreso;
    }

    @FXML
    private void registrarIngreso(ActionEvent event) {
    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
    }
    
}
