
package ganaderiafx.controlador;

import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.utils.VentanaAlert;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;


public class EditarCatalogoController implements Initializable {

    @FXML
    private TextField txt_conceptoEditar;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private ComboBox<String> cmb_catalogoEditar;

    CatalogoConcepto catalogo = null;
    Boolean isnew=false;
    String catalogoCmb = "";
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.catalogos();
    }    

    public void setData(CatalogoConcepto catalogo, Boolean isnew){  
        this.catalogo=catalogo;
        this.isnew=isnew;
        this.cargarCatalogo();
    }
    
    @FXML
    private void editarCatalogo(ActionEvent event) {
        
        int position = this.cmb_catalogoEditar.getSelectionModel().getSelectedIndex();
        VentanaAlert alert = new VentanaAlert();
        
        if(this.txt_conceptoEditar.getText().isEmpty() 
                || position<=-1){
            
            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacio");
        }else{
            String verificacion = null;
            String data = "0";
            HashMap<String, Object> buscar = new LinkedHashMap<>();
            
            switch (position) {
                case 0:
                    catalogoCmb = "Ingreso";
                    break;
                default:
                    catalogoCmb = "Egreso";
                    break;
            }
            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar el egreso?...");
            alertI.showAndWait().ifPresent(response -> {
                
                if (response == ButtonType.OK) {
                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idCatalogoConcepto", this.catalogo.getIdCatalogoConcepto());
                        params.put("catalogo", catalogoCmb);
                        params.put("concepto", this.txt_conceptoEditar.getText());
                        
                        String respuesta = Requests.post("/catalogoConcepto/actualizarCatalogo/", params);
                        
                        JSONObject dataJson = new JSONObject(respuesta);
                        
                        if ((Boolean) dataJson.get("error") == false) {
                            alert.information("Informativo", dataJson.getString("mensaje"));
                            this.catalogo = null;
                            Window.close(event);
                            
                        } else {
                            alert.warning("Advertencia", dataJson.getString("mensaje"));
                            this.catalogo = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.catalogo = null;
                    Window.close(event);
                }
            });
        }
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
    }

    
    public void cargarCatalogo(){                                   //FALTAAAAAAAAA       
        this.txt_conceptoEditar.setText(catalogo.getConcepto());
    }
    
    private void catalogos(){
        
        List<String> nombre = new LinkedList<String>();
        nombre.add("Ingreso");
        nombre.add("Egreso");
        
        ObservableList<String> ObsnombreRoles = FXCollections.observableArrayList(nombre);
        this.cmb_catalogoEditar.setItems(ObsnombreRoles);
        
    }
}
