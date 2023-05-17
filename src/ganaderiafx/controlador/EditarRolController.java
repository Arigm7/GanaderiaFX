
package ganaderiafx.controlador;

import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Rol;
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


public class EditarRolController implements Initializable {

    @FXML
    private TextField txt_nombreRolEditar;
    @FXML
    private TextField txt_estatusRolEditar;
    @FXML
    private Button btn_editarRol;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private Pane pnl_busqueda;

    Rol rol = null;
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Rol rol, Boolean isnew){  
        this.rol=rol;
        this.isnew=isnew;
        this.cargarRol();
    }

    @FXML
    private void editarRol(ActionEvent event) {
        
        VentanaAlert alert = new VentanaAlert();

        if (this.txt_nombreRolEditar.getText().isEmpty()) {
            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacios");
        } else {
            
            HashMap<String, Object> params = new LinkedHashMap<>();
            params.put("idRol", this.rol.getIdRol());
            params.put("nombre", this.txt_nombreRolEditar.getText());
            params.put("estatus", this.rol.getEstatus());

            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar el rol?...");

            alertI.showAndWait().ifPresent(response -> {
                
                if (response == ButtonType.OK) {
                    try {
                        if (this.rol.getIdRol() != 201) {
                            String respuesta = Requests.post("/rol/actualizarRol/", params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {
                                alert.information("Informativo", dataJson.getString("mensaje"));
                                this.rol = null;
                                Window.close(event);

                            } else {
                                alert.warning("Advertencia", dataJson.getString("mensaje"));
                                this.rol = null;
                                Window.close(event);
                            }
                        } else {
                            alert.warning("Advertencia","Solo personal autorizado puede editar este rol...");
                            this.rol = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarRolController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (response == ButtonType.CANCEL) {
                    this.rol = null;
                    Window.close(event);
                }
            });
        }

    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
    }
    
    public void cargarRol(){     
        this.txt_nombreRolEditar.setText(rol.getNombre());
        this.txt_estatusRolEditar.setText(rol.getEstatus());
    }
}
