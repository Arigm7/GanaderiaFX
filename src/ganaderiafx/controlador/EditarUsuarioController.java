
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.JSONException;
import org.json.JSONObject;

public class EditarUsuarioController implements Initializable {

    @FXML
    private ComboBox<String> cmb_rolEditar;
    @FXML
    private TextField txt_nombreEditar;
    @FXML
    private TextField txt_apellidoPaternoEditar;
    @FXML
    private TextField txt_apellidoMaternoEditar;
    @FXML
    private TextField txt_usuarioEditar;
    @FXML
    private TextField txt_passwordEditar;
    @FXML
    private TextField txt_estatusEditar;
    @FXML
    private Button btn_editarUsuario;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private Pane pnl_busqueda;

    private Integer[] arrayID;
    private ObservableList<Rol> comboBoxList;
    Usuario usuario = null;  
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        cmb_rolEditar.setItems(ObsnombreRoles);
    }    
    
    public void setData(Usuario usuario, Boolean isnew){ 
        this.usuario=usuario;
        this.isnew=isnew;
        this.cargarUsuario();
    }

    
    @FXML
    private void rolEditar(ActionEvent event) {
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
        
        if (this.usuario != null) {
            
            //int position = this.cmb_rolEditar.getSelectionModel().getSelectedIndex();
            
            if (this.txt_nombreEditar.getText().isEmpty()
                    || this.txt_apellidoPaternoEditar.getText().isEmpty()
                    || this.txt_apellidoMaternoEditar.getText().isEmpty()
                    || this.txt_usuarioEditar.getText().isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error al actualizar el usuario");
                alert.setHeaderText(null);
                alert.setContentText("Alguno de los campos se encuentra Vacio");
                alert.showAndWait();
            } else {

                int position = this.cmb_rolEditar.getSelectionModel().getSelectedIndex();
                //int rol = this.arrayID[position];

                if(position<=0){
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("idUsuario", usuario.getIdUsuario());
                    params.put("nombre", this.txt_nombreEditar.getText());
                    params.put("apellidoPaterno", this.txt_apellidoPaternoEditar.getText());
                    params.put("apellidoMaterno", this.txt_apellidoMaternoEditar.getText());
                    params.put("usuario", this.txt_usuarioEditar.getText());
                    params.put("password", this.txt_passwordEditar.getText());
                    params.put("estatus", this.txt_estatusEditar.getText());
                    params.put("idRol", this.usuario.getIdRol());

                   
                    Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                    alertI.setTitle("Confirmación");
                    alertI.setHeaderText(null);
                    alertI.setContentText("Seguro que desea actualizar el usuario?...");

                    alertI.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            try {
                                String respuesta = Requests.post("/usuario/actualizarUsuario/", params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("error") == false) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Informativo");
                                    alert.setHeaderText(null);
                                    alert.setContentText(dataJson.getString("mensaje"));
                                    alert.showAndWait();
                                    this.usuario = null;
                                    Window.close(event);

                                } else {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Advertencia");
                                    alert.setHeaderText(null);
                                    alert.setContentText(dataJson.getString("mensaje"));
                                    alert.showAndWait();
                                    this.usuario = null;
                                }
                            } catch (JSONException ex) {
                                Logger.getLogger(EditarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        if (response == ButtonType.CANCEL) {
                            this.usuario = null;
                        }
                    });

                }else{
                    int rol = this.arrayID[position];
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("idUsuario", usuario.getIdUsuario());
                    params.put("nombre", this.txt_nombreEditar.getText());
                    params.put("apellidoPaterno", this.txt_apellidoPaternoEditar.getText());
                    params.put("apellidoMaterno", this.txt_apellidoMaternoEditar.getText());
                    params.put("usuario", this.txt_usuarioEditar.getText());
                    params.put("password", this.txt_passwordEditar.getText());
                    params.put("estatus", this.txt_estatusEditar.getText());
                    params.put("idRol", rol);

                    if (rol == 201) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setHeaderText(null);
                        alert.setContentText("No puedes actualizar un usuario como administrador, intenta asignarle otro rol");
                        alert.showAndWait();
                    } else {

                        Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                        alertI.setTitle("Confirmación");
                        alertI.setHeaderText(null);
                        alertI.setContentText("Seguro que desea actualizar el usuario?...");

                        alertI.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                try {
                                    String respuesta = Requests.post("/usuario/actualizarUsuario/", params);

                                    JSONObject dataJson = new JSONObject(respuesta);

                                    if ((Boolean) dataJson.get("error") == false) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Informativo");
                                        alert.setHeaderText(null);
                                        alert.setContentText(dataJson.getString("mensaje"));
                                        alert.showAndWait();
                                        this.usuario = null;
                                        Window.close(event);

                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Advertencia");
                                        alert.setHeaderText(null);
                                        alert.setContentText(dataJson.getString("mensaje"));
                                        alert.showAndWait();
                                        this.usuario = null;
                                    }
                                } catch (JSONException ex) {
                                    Logger.getLogger(EditarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            if (response == ButtonType.CANCEL) {
                                Window.close(event);
                                this.cargarUsuario();
                                this.usuario = null;
                            }
                        });
                    }
                }
            }
        }else{
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Usuario...");
            alertI.showAndWait();
        }   
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
         Window.close(event);
         this.cargarUsuario();
         this.usuario=null;
    }
    
    public void cargarUsuario(){
        
        if(usuario.getIdRol()==201){
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_usuarioEditar.setText(usuario.getUsuario());
            this.txt_passwordEditar.setEditable(false);
            //this.txt_passwordEditar.setText(usuario.getPassword());
            this.txt_estatusEditar.setText(usuario.getEstatus());
            
        }else{
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_usuarioEditar.setText(usuario.getUsuario());
            //this.txt_passwordEditar.setText(usuario.getPassword());
            this.txt_estatusEditar.setText(usuario.getEstatus());
        }     
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
