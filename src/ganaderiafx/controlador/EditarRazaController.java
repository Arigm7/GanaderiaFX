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
public class EditarRazaController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Label lbl_nomUsuario_EditarRaza;
    @FXML
    private TextField txt_nombreRazaEditar;
    @FXML
    private TextField txt_estatusRazaEditar;
    @FXML
    private Button btn_editarRaza;
    @FXML
    private Button btn_cancelarEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarRaza(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
    }
    
}
