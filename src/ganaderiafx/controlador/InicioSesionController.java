
package ganaderiafx.controlador;

import com.google.gson.Gson;
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
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
                
                String data ="";
                
                HashMap<String,Object> params = new LinkedHashMap<>();
                params.put("usuario", this.txt_usuario.getText());
                params.put("password", this.txt_password.getText());
                
                data = Requests.post("/sesion/login/", params);
                
                if(!data.isEmpty()){
                    System.out.println("Data;"+data);
                
                    JSONObject dataJson = new JSONObject(data); //convertimos el string en objeto json

                    if((Boolean)dataJson.get("error") == false){ //validar si encontro algo en la base de datos

                        Stage stage = Window.getStageByEvent(event);
                        
                        Gson gson = new Gson();
                        Usuario user = gson.fromJson(dataJson.get("respuesta").toString(), Usuario.class); //guarda la informacion del usuario que inicio sesion
                        
                        HashMap<String,Object> context = new HashMap<String,Object> ();             //se agregro
                        context.put("mac",JavaUtils.getMAC());
                        context.put("usuario", user);                                                  //se inserto el objeto
                        context.put("ip",InetAddress.getLocalHost());

                        //Se carga la nueva interfaz fxml
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/PrincipalFXML.fxml"));

                        //Se obtiene el nodo padre que contienes los nodos secundarios de la interfaz fxml
                        Parent principal = loader.load();

                        //Se obtiene la instancia del controlador asociado a la interfaz fxml
                        PrincipalController ctrl = loader.getController();

                        //setData es un metodo que esta declarado en la clase PrincipalController
                        //Al metodo setData se le pasa como parametro el contexto
                        ctrl.setData(context);
                        
                        //Se crea una nueva escena con la interfaz principal.fxml
                        Scene scene = new Scene(principal);

                        //Insertamos la nueva escena en el escenario actual
                        //Se puede crear otro escenario para insertar la nueva escena (opcional)
                        stage.setScene(scene);

                        //A la nueva escena se le agrega un titulo
                        stage.setTitle("GANADERIA (Sistema de Administración de Villa Ganadera)");
                        //Propiedad para no maximizar la escena
                        stage.setResizable(false);
                        stage.getIcons().add(new Image("/ganaderiafx/gui/img/logo.png"));
                        //Se muestra la escena que previamente se creo
                        stage.show();
                    }else{
                        this.lbl_mensaje.setText(dataJson.getString("mensaje"));
                    }
                }else{
                    this.lbl_mensaje.setText("Intentelo mas tarde");
                }
            } catch (JSONException ex) {
                Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
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
