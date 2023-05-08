
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


public class RegistrarRanchoController implements Initializable {

    @FXML
    private Label lbl_nomUsuario_Rancho;
    @FXML
    private TextField txt_nombreRancho;
    @FXML
    private TextField txt_coloniaRancho;
    @FXML
    private Button btn_editarRancho;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private TextField txt_numRancho;
    @FXML
    private TextField txt_estatusRancho;
    @FXML
    private TextField txt_calleRancho;

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
    }
    @FXML
    private void editarRancho(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
    }
    
}
