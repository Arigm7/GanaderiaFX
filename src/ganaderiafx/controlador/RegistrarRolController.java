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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alex
 */
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarRol(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
    }
    
}
