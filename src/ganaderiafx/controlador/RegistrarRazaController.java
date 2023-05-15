
package ganaderiafx.controlador;

import com.google.gson.Gson;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.modelo.pojos.Usuario;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarRazaController implements Initializable {

    @FXML
    private TextField txt_nombreRazaRegistrar;
    @FXML
    private TextField txt_estatusRazaRegistrar;
    @FXML
    private Button btn_registrarRaza;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private Label lbl_nomUsuario_RegistrarRaza;

    Raza raza = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    public void setData(Raza raza){  
        this.raza=raza;        
    }
    
    @FXML
    private void registrarRaza(ActionEvent event) {
        if (this.txt_nombreRazaRegistrar.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar una raza");
            alert.setHeaderText(null);
            alert.setContentText("El campo esta vacio");
            alert.showAndWait();
        } else {
            try {
                String verificacion = null;
                String v = "0";
                HashMap<String, Object> buscar = new LinkedHashMap<>();
                buscar.put("nombre", this.txt_nombreRazaRegistrar.getText());
                verificacion = Requests.post("/raza/razaId/", buscar);
                
                JSONObject dataJsonV = new JSONObject(verificacion);
                System.out.println("LO QUE HAY"+dataJsonV.getString("mensaje"));
                
                if (dataJsonV.getString("mensaje").equals("0")) {
                    
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_nombreRazaRegistrar.getText());
                    
                    String respuesta = Requests.post("/raza/registrarRaza/", params);
                    
                    JSONObject dataJson = new JSONObject(respuesta);
                    
                    if ((Boolean) dataJson.get("error") == false) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informativo");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                        Window.close(event);
                        
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                    }
                } else {                                      
                    
                    Alert alertV = new Alert(Alert.AlertType.WARNING);
                    alertV.setTitle("Error al registrar una raza");
                    alertV.setHeaderText(null);
                    alertV.setContentText("La raza ya esta registrada...");
                    alertV.showAndWait();
                    
                }
            } catch (JSONException ex) {
                Logger.getLogger(RegistrarRazaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
         Window.close(event);
    }  
    
 
}
