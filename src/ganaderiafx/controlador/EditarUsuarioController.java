
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Rol;
import ganaderiafx.modelo.pojos.Usuario;
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
        
        VentanaAlert alert = new VentanaAlert();
        
        if (this.usuario != null) {

            int position = this.cmb_rolEditar.getSelectionModel().getSelectedIndex();

            if (this.txt_nombreEditar.getText().isEmpty()
                    || this.txt_apellidoPaternoEditar.getText().isEmpty()
                    || this.txt_apellidoMaternoEditar.getText().isEmpty()
                    || this.txt_usuarioEditar.getText().isEmpty()
                    || position <= -1) {
                
                alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacios");
            } else {
                
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

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Confirmación");
                alertI.setHeaderText(null);
                alertI.setContentText("Seguro que desea actualizar el usuario?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getIdRol() != 201) {
                                String respuesta = Requests.post("/usuario/actualizarUsuario/", params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("error") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.usuario = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.usuario = null;
                                    Window.close(event);
                                }
                            } else {
                                alert.warning("Advertencia","Solo personal autorizado puede editar este usuario...");
                                this.usuario = null;
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.usuario = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un Usuario...");
        }
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
        this.cargarUsuario();
        this.usuario = null;
    }

    public void cargarUsuario() {

        if (this.usuario.getIdRol() != 201) {
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_usuarioEditar.setText(usuario.getUsuario());
            this.txt_estatusEditar.setText(usuario.getEstatus());
        } else {
            this.txt_nombreEditar.setText(usuario.getNombre());
            this.txt_apellidoPaternoEditar.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaternoEditar.setText(usuario.getApellidoMaterno());
            this.txt_estatusEditar.setText(usuario.getEstatus());
        }
    }

    private ObservableList getAllRoles() {

        String respuesta = Requests.get("/rol/getAllRolActivo/");
        Gson gson = new Gson();

        TypeToken<List<Rol>> token = new TypeToken<List<Rol>>() {
        };

        List<Rol> listaRoles = gson.fromJson(respuesta, token.getType());
        
        comboBoxList=FXCollections.observableArrayList(listaRoles);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
}
