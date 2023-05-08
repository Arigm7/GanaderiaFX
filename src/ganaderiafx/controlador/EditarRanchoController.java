/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Rancho;
import ganaderiafx.utils.Window;
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
public class EditarRanchoController implements Initializable {

    @FXML
    private TextField txt_nombreRanchoEditar;
    @FXML
    private TextField txt_coloniaRanchoEditar;
    @FXML
    private Button btn_editarRancho;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private TextField txt_numRanchoEditar;
    @FXML
    private TextField txt_estatusRanchoEditar;
    @FXML
    private TextField txt_usuarioRanchoEditar;
    @FXML
    private TextField txt_calleRanchoEditar;
    @FXML
    private Label lbl_nomUsuario_EditarRancho;

    Rancho rancho = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Rancho rancho){
        this.rancho=rancho;
        
        this.cargarRancho();
    }
    @FXML
    private void editarRancho(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
        this.cargarRancho();
    }
    
        
    public void cargarRancho(){
        String numero = Integer.toString(rancho.getNumExt());
        this.txt_nombreRanchoEditar.setText(rancho.getNombre());
        this.txt_coloniaRanchoEditar.setText(rancho.getColonia());
        this.txt_numRanchoEditar.setText(numero);
        this.txt_estatusRanchoEditar.setText(rancho.getEstatus());
        this.txt_usuarioRanchoEditar.setText(rancho.getUsuario());
        this.txt_calleRanchoEditar.setText(rancho.getCalle());
    }
}
