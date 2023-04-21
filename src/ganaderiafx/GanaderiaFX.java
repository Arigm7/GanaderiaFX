
package ganaderiafx;

import java.net.InetAddress;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ganaderiafx.utils.JavaUtils;


public class GanaderiaFX extends Application {
    
    HashMap<String,Object> context = new HashMap<String,Object> ();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("/ganaderiafx/gui/vista/InicioSesionFXML.fxml"));
        
       Scene scene = new Scene(login);
        
        this.context.put("ip",InetAddress.getLocalHost());
        this.context.put("mac",JavaUtils.getMAC());
        
        stage.setUserData(this.context);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
