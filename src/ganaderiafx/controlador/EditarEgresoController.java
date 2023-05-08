/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Egreso;
import ganaderiafx.utils.Window;
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
    private TextArea txt_observacionesEditar;
    @FXML
    private Label lbl_nomUsuario_EditarEgreso;
    @FXML
    private TextField txt_idEgreso;

    Egreso egreso = null;
    Boolean isnew=false;
    String id = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Egreso egreso, Boolean isnew){ 
        this.egreso=egreso;
        this.isnew=isnew;
        this.cargarEgreso();
    }

    @FXML
    private void editarEgreso(ActionEvent event) {
    }

    @FXML
    private void cancelarEgreso(ActionEvent event) {
        Window.close(event);
        this.cargarEgreso();
    }
    
    public void cargarEgreso(){
        id = Integer.toString(egreso.getIdEgreso());
        this.txt_idEgreso.setText(id);
        this.txt_conceptoEditar.setText(egreso.getConcepto());
        this.txt_observacionesEditar.setText(egreso.getObservaciones());
        this.txt_motivoEditar.setText(egreso.getMotivo());
        
    }
}
    