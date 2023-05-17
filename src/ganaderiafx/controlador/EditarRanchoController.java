
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Rancho;
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
import org.json.JSONException;
import org.json.JSONObject;

public class EditarRanchoController implements Initializable {

    @FXML
    private TextField txt_nombreRanchoEditar;
    @FXML
    private TextField txt_coloniaRanchoEditar;
    @FXML
    private Button btn_editarRancho;
    @FXML
    private Button btn_cancelarEditar;
    @FXML
    private TextField txt_numRanchoEditar;
    @FXML
    private TextField txt_calleRanchoEditar;
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
        
        this.cargarRancho();
    }
    @FXML
    private void editarRancho(ActionEvent event) {
        int position = this.cmb_vaquero.getSelectionModel().getSelectedIndex();
        VentanaAlert alert = new VentanaAlert();
        if (this.txt_nombreRanchoEditar.getText().isEmpty()
                || this.txt_coloniaRanchoEditar.getText().isEmpty()
                || this.txt_numRanchoEditar.getText().isEmpty() || !this.txt_numRanchoEditar.getText().substring(0, 1).matches("[0-9]*")
                || this.txt_calleRanchoEditar.getText().isEmpty()
                || position <= -1) {

            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacios");
        } else {

            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar el rancho?...");

            alertI.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    try {
                        int usuario = this.arrayID[position];
                        
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idRancho", this.rancho.getIdRancho());
                        params.put("nombre", this.txt_nombreRanchoEditar.getText());
                        params.put("colonia", this.txt_coloniaRanchoEditar.getText());
                        params.put("calle", this.txt_calleRanchoEditar.getText());
                        params.put("numExt", this.txt_numRanchoEditar.getText());
                        params.put("idUsuario", usuario);
                        
                        String respuesta = Requests.post("/rancho/actualizarRancho/", params);
                        
                        JSONObject dataJson = new JSONObject(respuesta);
                        
                        if ((Boolean) dataJson.get("error") == false) {
                            alert.information("Informativo", dataJson.getString("mensaje"));
                            this.rancho = null;
                            Window.close(event);
                            
                        } else {
                            alert.warning("Advertencia", dataJson.getString("mensaje"));
                            this.rancho = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarRanchoController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (response == ButtonType.CANCEL) {
                    this.rancho = null;
                    Window.close(event);
                }
            });
        }
    }

    @FXML
    private void cancelarEditar(ActionEvent event) {
        Window.close(event);
        this.rancho=null;
    }
    
        
    public void cargarRancho(){
        String numero = Integer.toString(rancho.getNumExt());
        this.txt_nombreRanchoEditar.setText(rancho.getNombre());
        this.txt_coloniaRanchoEditar.setText(rancho.getColonia());
        this.txt_numRanchoEditar.setText(numero);
        this.txt_calleRanchoEditar.setText(rancho.getCalle());
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

    private ObservableList getAllRoles() {

        String respuesta = Requests.get("/usuario/getAllUsersActivo/");
        Gson gson = new Gson();

        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>() {
        };

        List<Usuario> lista = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(lista);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
}
