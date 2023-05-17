
package ganaderiafx.controlador;

import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.utils.VentanaAlert;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.JSONException;
import org.json.JSONObject;


public class EditarRazaController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_nombreRazaEditar;
    @FXML
    private TextField txt_estatusRazaEditar;
    @FXML
    private Button btn_editarRaza;
    @FXML
    private Button btn_cancelarEditar;

    Raza raza = null;                            
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(Raza raza, Boolean isnew){
        this.raza=raza;
        this.isnew=isnew;
        this.cargarRaza();
    }

    @FXML
    private void editarRaza(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();

        if (this.txt_nombreRazaEditar.getText().isEmpty()) {
            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacios");
        } else {
            
            HashMap<String, Object> params = new LinkedHashMap<>();
            params.put("idRaza", this.raza.getIdRaza());
            params.put("nombre", this.txt_nombreRazaEditar.getText());
            params.put("estatus", this.raza.getEstatus());

            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar la raza?...");

            alertI.showAndWait().ifPresent(response -> {
                
                if (response == ButtonType.OK) {
                    try {
                        String respuesta = Requests.post("/raza/actualizarRaza/", params);

                        JSONObject dataJson = new JSONObject(respuesta);

                        if ((Boolean) dataJson.get("error") == false) {
                            alert.information("Informativo", dataJson.getString("mensaje"));
                            this.raza = null;
                            Window.close(event);

                        } else {
                            alert.warning("Advertencia", dataJson.getString("mensaje"));
                            this.raza = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarRazaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.raza = null;
                    Window.close(event);
                }
            });
        }
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
        this.raza = null;
    }
    
    public void cargarRaza(){
        this.txt_nombreRazaEditar.setText(raza.getNombre());
        this.txt_estatusRazaEditar.setText(raza.getEstatus());
    }
}
