/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Ingreso;
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
    private TextArea txt_observacionesEditar;

    Ingreso ingreso = null;
    Boolean isnew=false;
    String cantidad="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Ingreso ingreso, Boolean isnew){ 
        this.ingreso=ingreso;
        this.isnew=isnew;
        this.cargarIngreso();
    }

    @FXML
    private void editarIngreso(ActionEvent event) {
    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
        Window.close(event);
        this.cargarIngreso();
    }
    
    public void cargarIngreso(){
        
       
        cantidad = Integer.toString(ingreso.getCantidad());
        this.txt_cantidadEditar.setText(cantidad);
        this.txt_conceptoEditar.setText(ingreso.getConcepto());
        this.txt_observacionesEditar.setText(ingreso.getObservaciones());
        
    }
}
