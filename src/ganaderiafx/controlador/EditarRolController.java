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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class EditarRolController implements Initializable {

    @FXML
    private TextField txt_nombreRolEditar;
    @FXML
    private TextField txt_estatusRolEditar;
    @FXML
    private Button btn_editarRol;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Label lbl_nomUsuario_EditarRol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarRol(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
    }
    
}
