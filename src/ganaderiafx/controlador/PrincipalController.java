
package ganaderiafx.controlador;

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(HashMap<String,Object> context){          //crear este metodo en todos los controladores
        
        System.out.print(context);
    }

    @FXML
    private void abrirlIngresoEgreso(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/MovimientoFXML.fxml"));
            
            Parent principal = loader.load();
            
            pnl_principal.setCenter(principal);
 
        }catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    @FXML
    private void abrirlUsuario(ActionEvent event) {
         try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/UsuarioFXML.fxml"));
            
            Parent principal = loader.load();
            
            pnl_principal.setCenter(principal);
 
        }catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    @FXML
    private void abrirCatalogo(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/CatalogoFXML.fxml"));
            
            Parent principal = loader.load();
            
            pnl_principal.setCenter(principal);
 
        }catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    @FXML
    private void abrirRaza(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RazaFXML.fxml"));
            
            Parent principal = loader.load();
            
            pnl_principal.setCenter(principal);
 
        }catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
