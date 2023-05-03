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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class EditarUsuarioController implements Initializable {

    @FXML
    private ComboBox<?> cmb_rolEditar;
    @FXML
    private TextField txt_nombreEditar;
    @FXML
    private TextField txt_apellidoPaternoEditar;
    @FXML
    private TextField txt_apellidoMaternoEditar;
    @FXML
    private TextField txt_usuarioEditar;
    @FXML
    private TextField txt_passwordEditar;
    @FXML
    private TextField txt_estatusEditar;
    @FXML
    private Button btn_editarUsuario;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Label lbl_nombreUsuarioEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rolEditar(ActionEvent event) {
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
    }
    
}
