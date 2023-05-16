
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.modelo.pojos.Ingreso;
import ganaderiafx.modelo.pojos.Rancho;
import ganaderiafx.modelo.pojos.Rol;
import ganaderiafx.modelo.pojos.Usuario;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarIngresoController implements Initializable {

    @FXML
    private TextField txt_cantidadRegistrar;
    @FXML
    private Button btn_registrarIngreso;
    @FXML
    private Button btn_cancelarIngreso;
    @FXML
    private TextArea txt_observacionesRegistro;

   
    @FXML
    private ComboBox<String> cmb_concepto;
    @FXML
    private ComboBox<String> cmb_rancho;

    private Integer[] arrayIDConcepto;
    private ObservableList<CatalogoConcepto> comboBoxListConcepto;
    
    private Integer[] arrayIDRancho;  
    private ObservableList<Rancho> comboBoxListRancho;
    
    Ingreso ingreso = null;
    Usuario nombreUsuario = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarConcepto();
        this.cargarRancho();
    }    
    
    public void setData(Ingreso ingreso){  
        this.ingreso=ingreso;
    }

    public void setDataUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    @FXML
    private void registrarIngreso(ActionEvent event) {
        
        int positionConcepto = this.cmb_concepto.getSelectionModel().getSelectedIndex();
        int positionRancho = this.cmb_rancho.getSelectionModel().getSelectedIndex();
        
        if (this.txt_cantidadRegistrar.getText().isEmpty()
                || !this.txt_cantidadRegistrar.getText().substring(0, 1).matches("[0-9]*")
                || this.txt_observacionesRegistro.getText().isEmpty()
                || positionConcepto <= -1
                || positionRancho <= -1) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar un ingreso");
            alert.setHeaderText(null);
            alert.setContentText("Alguno de los campos se encuentra Vacio O la cantidad tiene carater");
            alert.showAndWait();
        } else {

            try {
                
                int concepto = this.arrayIDConcepto[positionConcepto];
                int rancho = this.arrayIDRancho[positionRancho];

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("cantidad", this.txt_cantidadRegistrar.getText());
                params.put("observaciones", this.txt_observacionesRegistro.getText());
                params.put("idCatalogoConcepto", concepto);
                params.put("idRancho", rancho);
                params.put("idUsuario", this.nombreUsuario.getIdUsuario());
                

                String respuesta = Requests.post("/ingreso/registrarIngreso/", params);

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

            } catch (JSONException ex) {
                Logger.getLogger(RegistrarIngresoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
        Window.close(event);
    }
    
    private void cargarConcepto(){
        comboBoxListConcepto = getAllConcepto();
        List<String> nombreConcepto = new LinkedList<String>();
        Integer idConcepto[] = new Integer[comboBoxListConcepto.size()];
        int i = 0;
        for (CatalogoConcepto con : comboBoxListConcepto) {
            nombreConcepto.add(con.getConcepto());
            idConcepto[i] = con.getIdCatalogoConcepto();
            i++;
        }
        this.arrayIDConcepto = idConcepto;
        ObservableList<String> Obsnombre = FXCollections.observableArrayList(nombreConcepto);
        cmb_concepto.setItems(Obsnombre);
    }
    
    private ObservableList getAllConcepto(){
                
        String respuesta = Requests.get("/catalogoConcepto/getAllCatalogoIngreso/");
        Gson gson = new Gson();
        
        TypeToken<List<CatalogoConcepto>> token = new TypeToken<List<CatalogoConcepto>>(){   
        };

        List<CatalogoConcepto> listaC = gson.fromJson(respuesta, token.getType());
        
        comboBoxListConcepto=FXCollections.observableArrayList(listaC);
        System.out.print(comboBoxListConcepto);
        return comboBoxListConcepto;
    }
    
    
    private void cargarRancho(){
        comboBoxListRancho = getAllRancho();
        List<String> nombreRancho = new LinkedList<String>();
        Integer idRancho[] = new Integer[comboBoxListRancho.size()];
        int i = 0;
        for (Rancho ran : comboBoxListRancho) {
            nombreRancho.add(ran.getNombre());
            idRancho[i] = ran.getIdRancho();
            i++;
        }
        this.arrayIDRancho = idRancho;
        ObservableList<String> Obsnombre = FXCollections.observableArrayList(nombreRancho);
        cmb_rancho.setItems(Obsnombre);
    }
    
    private ObservableList getAllRancho(){
                
        String respuesta = Requests.get("/rancho/getAllRanchoActivo/");
        Gson gson = new Gson();
        
        TypeToken<List<Rancho>> token = new TypeToken<List<Rancho>>(){   
        };

        List<Rancho> listaR = gson.fromJson(respuesta, token.getType());
        
        comboBoxListRancho=FXCollections.observableArrayList(listaR);
        System.out.print(comboBoxListRancho);
        return comboBoxListRancho;
    }
}
