
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
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
import javafx.scene.layout.Pane;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarUsuarioController implements Initializable {

    @FXML
    private ComboBox<String> cmb_rolRegistrar;
    private Integer[] arrayID;
    private ObservableList<Rol> comboBoxList;
    
    @FXML
    private TextField txt_nombreRegistrar;
    @FXML
    private TextField txt_apellidoPaternoRegistrar;
    @FXML
    private TextField txt_apellidoMaternoRegistrar;
    @FXML
    private TextField txt_usuarioRegistrar;
    @FXML
    private TextField txt_passwordRegistrar;
    @FXML
    private TextField txt_estatusRegistrar;
    @FXML
    private Button btn_registrarUsuario;
    @FXML
    private Button btn_cancelarRegistro;
    @FXML
    private Label lbl_nomUsuario_RegistrarUsuario;

    Usuario usuario = null;                            
    Rol rol = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxList = getAllRoles();

        List<String> nombreRoles = new LinkedList<String>();
        Integer idRoles[] = new Integer[comboBoxList.size()];
        int i = 0;
        for (Rol rol : comboBoxList) {
            nombreRoles.add(rol.getNombre());
            idRoles[i] = rol.getIdRol();
            i++;
        }
        this.arrayID = idRoles;
        ObservableList<String> ObsnombreRoles = FXCollections.observableArrayList(nombreRoles);
        cmb_rolRegistrar.setItems(ObsnombreRoles);
    }    

    public void setData(Usuario usuario){  
        this.usuario=usuario;
    }
    
    @FXML
    private void rolRegistrar(ActionEvent event) {
        
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
       
        if(this.txt_nombreRegistrar.getText().isEmpty() ||
                this.txt_apellidoPaternoRegistrar.getText().isEmpty() || 
                this.txt_apellidoMaternoRegistrar.getText().isEmpty() || 
                this.txt_usuarioRegistrar.getText().isEmpty() ||
                this.txt_passwordRegistrar.getText().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrar un usuario");
            alert.setHeaderText(null);
            alert.setContentText("Alguno de los campos se encuentra Vacio");
            alert.showAndWait();
        }else{
            try{
                int position = this.cmb_rolRegistrar.getSelectionModel().getSelectedIndex();
                //System.out.print(position);
                int rol = this.arrayID[position];

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", this.txt_nombreRegistrar.getText());
                params.put("apellidoPaterno", this.txt_apellidoPaternoRegistrar.getText());
                params.put("apellidoMaterno", this.txt_apellidoMaternoRegistrar.getText());
                params.put("usuario", this.txt_usuarioRegistrar.getText());
                params.put("password", this.txt_passwordRegistrar.getText());
                params.put("idRol", rol);

                if(rol==201){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText(null);
                    alert.setContentText("No puedes registrar un usuario como administrador, intenta asignarle otro rol");
                    alert.showAndWait();
                }else{
                    String respuesta = Requests.post("/usuario/registrarUsuario/", params);

                    JSONObject dataJson = new JSONObject(respuesta);

                    if ((Boolean) dataJson.get("error") == false) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informativo");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                        Window.close(event);
                     
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                    }
                }  
            }catch (JSONException ex) {
                Logger.getLogger(RegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
         Window.close(event);
    }
    
    
    private ObservableList getAllRoles(){
                
        String respuesta = Requests.get("/rol/getAllRol/");
        Gson gson = new Gson();
        
        TypeToken<List<Rol>> token = new TypeToken<List<Rol>>(){   
        };

        List<Rol> listaRoles = gson.fromJson(respuesta, token.getType());
        
        comboBoxList=FXCollections.observableArrayList(listaRoles);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
}
