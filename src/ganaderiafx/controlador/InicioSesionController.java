
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
import ganaderiafx.modelo.pojos.Usuario;
import ganaderiafx.utils.JavaUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private void iniciarSesion(ActionEvent event) throws UnknownHostException {
        
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
                
                if((Boolean)dataJson.get("error") == false){ //validar si encontro algo en la base de datos
                    
                    JSONObject respuestaJson = new JSONObject(dataJson.get("respuesta").toString());
                    
                    Usuario u = new Usuario();
                    
                    u.setIdUsuario(respuestaJson.getInt("idUsuario"));              //los parametros que necesites 
                    u.setUsuario(respuestaJson.getString("usuario"));
                   // u.setIdRol(respuestaJson.getInt("idRol"));
                    
                    HashMap<String,Object> context = new HashMap<String,Object> ();             //se agregro
                    context.put("mac",JavaUtils.getMAC());
                    context.put("usuario", u);                                                  //se inserto el objeto
                    context.put("ip",InetAddress.getLocalHost());
                    
                    //System.out.println(respuestaJson);
                    //System.out.println(respuestaJson.getString("nombre"));
                    //System.out.println(respuestaJson.getInt("idRol"));
                    //this.lbl_mensaje.setText(respuestaJson.getString("nombre"));  ---------------------> //para ponerlo en un label o campo de texto  
                }else{
                    //System.out.print(dataJson.get("mensaje").toString());
                    this.lbl_mensaje.setText(dataJson.getString("mensaje"));
                    
                }
                
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
