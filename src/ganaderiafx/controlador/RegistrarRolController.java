
package ganaderiafx.controlador;

import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Rol;
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
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarRolController implements Initializable {

    @FXML
    private TextField txt_estatusRegistrar;
    @FXML
    private Button btn_registrarRol;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private TextField txt_nombreRegistro;

    Rol rol = null;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setData(Rol rol){  
        this.rol=rol;   
    }
    
    @FXML
    private void registrarRol(ActionEvent event) {

        if (this.txt_nombreRegistro.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar un ingreso");
            alert.setHeaderText(null);
            alert.setContentText("Alguno de los campos se encuentra Vacio");
            alert.showAndWait();
        } else {

            try {

                String postVerificacion = null;
                String rol = "0";
                HashMap<String, Object> buscar = new LinkedHashMap<>();
                buscar.put("nombre", this.txt_nombreRegistro.getText());

                postVerificacion = Requests.post("/rol/rolId/", buscar);
                JSONObject data = new JSONObject(postVerificacion);
                rol = (String) data.get("mensaje");

                if (rol.equals("0")) {
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_nombreRegistro.getText());

                    String respuesta = Requests.post("/rol/registrarRol/", params);

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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El rol ya esta registrado...");
                    alert.showAndWait();
                }
            } catch (JSONException ex) {
                Logger.getLogger(RegistrarRolController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Window.close(event);
    }
    
}
