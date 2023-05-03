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

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class EditarCatalogoController implements Initializable {

    @FXML
    private TextField txt_conceptoEditar;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private ComboBox<?> cmb_catalogoEditar;
    @FXML
    private Label lbl_nomUsuario_EditarCatalogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarCatalogo(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
    }

    @FXML
    private void catalogoEditar(ActionEvent event) {
    }
    
}
