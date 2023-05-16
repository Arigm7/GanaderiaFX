
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class EditarRazaController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_nombreRazaEditar;
    @FXML
    private TextField txt_estatusRazaEditar;
    @FXML
    private Button btn_editarRaza;
    @FXML
    private Button btn_cancelarEditar;

    Raza raza = null;                            
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(Raza raza, Boolean isnew){
        this.raza=raza;
        this.isnew=isnew;
        this.cargarRaza();
    }

    @FXML
    private void editarRaza(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
        this.cargarRaza();
    }
    
    public void cargarRaza(){
        this.txt_nombreRazaEditar.setText(raza.getNombre());
        this.txt_estatusRazaEditar.setText(raza.getEstatus());
    }
}
