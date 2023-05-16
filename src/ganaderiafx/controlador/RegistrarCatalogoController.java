
package ganaderiafx.controlador;

import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.utils.Window;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarCatalogoController implements Initializable {

    @FXML
    private TextField txt_conceptoRegistrar;
    @FXML
    private Button btn_registrarCatalogo;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private ComboBox<String> cmb_catalogoRegistrar;

    CatalogoConcepto catalogo = null;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.catalogos();
    }    

    public void setData(CatalogoConcepto catalogo){  
        this.catalogo=catalogo;   
    }
        
    @FXML
    private void registrarCatalogo(ActionEvent event) {
        int position = this.cmb_catalogoRegistrar.getSelectionModel().getSelectedIndex();
        if(this.txt_conceptoRegistrar.getText().isEmpty() 
                || position<=-1){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar un rancho");
            alert.setHeaderText(null);
            alert.setContentText("Alguno de los campos se encuentra Vacio");
            alert.showAndWait();
        }else{

            try {
                String verificacion = null;
                String data = "0";
                HashMap<String, Object> buscar = new LinkedHashMap<>();
                String catalogo = "";
                switch (position) {
                    case 0:
                        catalogo = "Ingreso";
                        break;
                    default:
                        catalogo = "Egreso";
                        break;    
                }
                buscar.put("concepto", this.txt_conceptoRegistrar.getText());
                buscar.put("catalogo", catalogo);

                verificacion = Requests.post("/catalogoConcepto/catalogoId/", buscar);
                JSONObject resPrimerPost = new JSONObject(verificacion);
                data = (String) resPrimerPost.get("mensaje");

                if (data.equals("0")) {

                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("catalogo", catalogo);
                    params.put("concepto", this.txt_conceptoRegistrar.getText());

                    String respuesta = Requests.post("/catalogoConcepto/registrarCatalogo/", params);

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
                    alert.setContentText("El catálogo ya esta registrado...");
                    alert.showAndWait();
                }

            } catch (JSONException ex) {
                Logger.getLogger(RegistrarCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Window.close(event);
    }

    
    private void catalogos(){
        
        List<String> nombre = new LinkedList<String>();
        nombre.add("Ingreso");
        nombre.add("Egreso");
        
        ObservableList<String> ObsnombreRoles = FXCollections.observableArrayList(nombre);
        this.cmb_catalogoRegistrar.setItems(ObsnombreRoles);
        
    }
}
