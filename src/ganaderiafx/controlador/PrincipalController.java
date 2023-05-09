
package ganaderiafx.controlador;

import ganaderiafx.modelo.pojos.Usuario;
import ganaderiafx.utils.Window;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class PrincipalController implements Initializable {

    @FXML
    private BorderPane pnl_principal;
    @FXML
    private MenuItem mi_IngresoEgreso;
    @FXML
    private MenuItem mi_usuario;
    @FXML
    private MenuItem mi_catalogos;
    @FXML
    private MenuItem mi_raza;
    @FXML
    private MenuItem mi_rancho;

    Usuario usuario = null;
    @FXML
    private MenuItem mi_ingreso_historial;
    @FXML
    private MenuItem mi_egreso_historial;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(HashMap<String,Object> context){                  
        System.out.print(context);
        
    }
    public void setDataUsuario(Usuario usuario){                  
        this.usuario=usuario;
    }

    @FXML
    private void abrirlIngresoEgreso(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/MovimientoFXML.fxml"));

            Parent principal = loader.load();

            MovimientoController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirlUsuario(ActionEvent event) {
         try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/UsuarioFXML.fxml"));
            
            Parent principal = loader.load();
            
            UsuarioController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        }catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirCatalogo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/CatalogoFXML.fxml"));

            Parent principal = loader.load();

            CatalogoController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirRaza(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RazaFXML.fxml"));

            Parent principal = loader.load();

            RazaController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirRancho(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RanchoFXML.fxml"));

            Parent principal = loader.load();

            RanchoController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void abrirl_IngresoHistorial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/IngresoHistorialFXML.fxml"));

            Parent principal = loader.load();

            IngresoHistorialController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirl_EgresoHistorial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EgresoHistorialFXML.fxml"));

            Parent principal = loader.load();

            EgresoHistorialController usuario = loader.getController();
            usuario.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
