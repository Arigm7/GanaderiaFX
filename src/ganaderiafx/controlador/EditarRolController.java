
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Rol;
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

    Rol rol = null;
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Rol rol, Boolean isnew){  
        this.rol=rol;
        this.isnew=isnew;
        this.cargarRol();
    }

    @FXML
    private void editarRol(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
    }
    
    public void cargarRol(){
        
        this.txt_nombreRolEditar.setText(rol.getNombre());
        this.txt_estatusRolEditar.setText(rol.getEstatus());

    }
}
