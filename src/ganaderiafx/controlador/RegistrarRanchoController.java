
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarRanchoController implements Initializable {

    @FXML
    private TextField txt_nombreRancho;
    @FXML
    private TextField txt_coloniaRancho;
    @FXML
    private TextField txt_numRancho;
    @FXML
    private TextField txt_calleRancho;
    @FXML
    private Button btn_registrarRancho;
    @FXML
    private Button btn_cancelar;
    @FXML
    private ComboBox<String> cmb_vaquero;

    Rancho rancho = null;
    private Integer[] arrayID;
    private ObservableList<Usuario> comboBoxList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vaquero();
    }    

    public void setData(Rancho rancho){  
        this.rancho=rancho;        
    }

    
    @FXML
    private void registrarRancho(ActionEvent event) {
       int position = this.cmb_vaquero.getSelectionModel().getSelectedIndex();
        if(this.txt_nombreRancho.getText().isEmpty() ||
                this.txt_coloniaRancho.getText().isEmpty() || 
                this.txt_numRancho.getText().isEmpty() || !this.txt_numRancho.getText().substring(0, 1).matches("[0-9]*")|| 
                this.txt_calleRancho.getText().isEmpty()||
                position<=-1){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar un rancho");
            alert.setHeaderText(null);
            alert.setContentText("Alguno de los campos se encuentra Vacio O el número del rancho es un caracter");
            alert.showAndWait();
        }else{
            
            try {
                String postVerificacion = null;
                String estaRancho = "0";
                HashMap<String, Object> buscar = new LinkedHashMap<>();
                buscar.put("nombre", this.txt_nombreRancho.getText());
               
                postVerificacion = Requests.post("/rancho/ranchoId/", buscar);
                JSONObject resPrimerPost = new JSONObject(postVerificacion);
                estaRancho = (String) resPrimerPost.get("mensaje");
                
                if(estaRancho.equals("0")){
                    
                    int usuario = this.arrayID[position];
                
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_nombreRancho.getText());
                    params.put("colonia", this.txt_coloniaRancho.getText());
                    params.put("calle", this.txt_calleRancho.getText());
                    params.put("numExt", this.txt_numRancho.getText());
                    params.put("idUsuario", usuario);

                    String respuesta = Requests.post("/rancho/registrarRancho/", params);

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

                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El nombre del rancho ya esta registrado...");
                    alert.showAndWait();
                }
            } catch (JSONException ex) {   
                Logger.getLogger(RegistrarRanchoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
         Window.close(event);
    }
    
        
    private ObservableList getAllRoles(){
                
        String respuesta = Requests.get("/usuario/getAllUsersActivo/");
        Gson gson = new Gson();
        
        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>(){   
        };

        List<Usuario> lista = gson.fromJson(respuesta, token.getType());
        
        comboBoxList=FXCollections.observableArrayList(lista);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
    
    private String verificar(){
        String v = "0";
        try {
            String verificacion = null;
            
            HashMap<String, Object> buscar = new LinkedHashMap<>();
            buscar.put("nombre", this.txt_nombreRancho.getText());
            
            verificacion = Requests.post("/rancho/ranchoId/", buscar);
            
            JSONObject dataJsonV = new JSONObject(verificacion);
            
            v = dataJsonV.getString("mensaje");
        } catch (JSONException ex) {
            Logger.getLogger(RegistrarRanchoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public void vaquero() {
        comboBoxList = getAllRoles();

        List<String> nombreRoles = new LinkedList<String>();
        Integer idRoles[] = new Integer[comboBoxList.size()];
        int i = 0;
        for (Usuario usuario : comboBoxList) {
            nombreRoles.add(usuario.getNombre());
            idRoles[i] = usuario.getIdUsuario();
            i++;
        }
        this.arrayID = idRoles;
        ObservableList<String> ObsnombreRoles = FXCollections.observableArrayList(nombreRoles);
        cmb_vaquero.setItems(ObsnombreRoles);
    }
}
