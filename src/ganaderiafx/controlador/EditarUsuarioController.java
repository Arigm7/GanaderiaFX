
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Usuario;
import ganaderiafx.utils.Window;
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

    Usuario usuario = null;                            
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Usuario usuario, Boolean isnew){ 
        this.usuario=usuario;
        this.isnew=isnew;
        this.cargarUsuario();
    }
    
    @FXML
    private void rolEditar(ActionEvent event) {
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
         Window.close(event);
         this.cargarUsuario();
    }
    
    public void cargarUsuario(){
        
        lbl_nombreUsuarioEditar.setText(usuario.getNombre());
        
        if(usuario.getIdRol()==201){
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_usuarioEditar.setText(usuario.getUsuario());
            //this.txt_passwordEditar.setText(usuario.getPassword());
            this.txt_estatusEditar.setText(usuario.getEstatus());
            
        }else{
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_usuarioEditar.setText(usuario.getUsuario());
            //this.txt_passwordEditar.setText(usuario.getPassword());
            this.txt_estatusEditar.setText(usuario.getEstatus());
        }
        
        
    }
}
