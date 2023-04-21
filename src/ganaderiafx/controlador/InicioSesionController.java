
package ganaderiafx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ganaderiafx.utils.Window;
import ganaderiafx.api.requests.Requests;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import org.json.JSONException;
import org.json.JSONObject;


public class InicioSesionController implements Initializable {

    @FXML
    private TextField txt_usuario;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_iniciarSesion;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Label lbl_mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        
        if(this.validar()){
            
            try {
                this.lbl_mensaje.setText(""); //borrar la etiqueta
                System.out.print(Window.getStageByEvent(event).getUserData());//mostrar la mac y ip
                
                String data ="";
                
                HashMap<String,Object> params = new LinkedHashMap<>();
                params.put("usuario", this.txt_usuario.getText());
                params.put("password", this.txt_password.getText());
                data = Requests.post("/sesion/login/", params);
                
                System.out.println("Data;"+data);
                
                JSONObject dataJson = new JSONObject(data); //convertimos el string en objeto json
                JSONObject respuestaJson = new JSONObject(dataJson.get("respuesta").toString());
                System.out.println(respuestaJson.getString("nombre"));
                System.out.println(respuestaJson.get("apellidoPaterno").toString());
                //System.out.println(dataJson.get("respuesta"));
                //System.out.println(dataJson.get("error"));
                
            } catch (JSONException ex) {
                Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            this.lbl_mensaje.setText("El usuario y contraseña son requeridos...");
            
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
    private boolean validar(){
        
        boolean valido = false;
        
        if(!this.txt_usuario.getText().isEmpty() && !this.txt_password.getText().isEmpty()){
            valido= true;
        }
        
        return valido;
    }
    
}
