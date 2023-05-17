
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.modelo.pojos.Egreso;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;

public class EditarEgresoController implements Initializable {

    @FXML
    private TextField txt_motivoEditar;
    @FXML
    private Button btn_editarEgreso;
    @FXML
    private Button btn_cancelarEgreso;
    @FXML
    private TextArea txt_observacionesEditar;
    @FXML
    private ComboBox<String> cmb_rancho;
    @FXML
    private ComboBox<String> cmb_concepto;
    
    private Integer[] arrayIDConcepto;
    private ObservableList<CatalogoConcepto> comboBoxListConcepto;

    private Integer[] arrayIDRancho;
    private ObservableList<Rancho> comboBoxListRancho;

    Egreso egreso = null;
    Boolean isnew = false;
    String id = "";
    Usuario nombreUsuario = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarConcepto();
        this.cargarRancho();
    }
    
    public void setData(Egreso egreso, Boolean isnew){ 
        this.egreso=egreso;
        this.isnew=isnew;
        this.cargarEgreso();
    }

    public void setDataUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
        
    @FXML
    private void editarEgreso(ActionEvent event) {
        
        int positionConcepto = this.cmb_concepto.getSelectionModel().getSelectedIndex();
        int positionRancho = this.cmb_rancho.getSelectionModel().getSelectedIndex();
        VentanaAlert alert = new VentanaAlert();
        
        if (this.txt_motivoEditar.getText().isEmpty()
                || this.txt_observacionesEditar.getText().isEmpty()
                || positionConcepto <= -1
                || positionRancho <= -1) {

            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacio");
        } else {
            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar el egreso?...");

            alertI.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {
                    try {

                        int concepto = this.arrayIDConcepto[positionConcepto];
                        int rancho = this.arrayIDRancho[positionRancho];

                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idEgreso", this.egreso.getIdEgreso());
                        params.put("motivo", this.txt_motivoEditar.getText());
                        params.put("observaciones", this.txt_observacionesEditar.getText());
                        params.put("idCatalogoConcepto", concepto);
                        params.put("idRancho", rancho);
                        params.put("idUsuario", this.nombreUsuario.getIdUsuario());

                        String respuesta = Requests.post("/egreso/actualizarEgreso/", params);

                        JSONObject dataJson = new JSONObject(respuesta);

                        if ((Boolean) dataJson.get("error") == false) {
                            alert.information("Informativo", dataJson.getString("mensaje"));
                            this.egreso = null;
                            Window.close(event);
                        } else {
                            alert.warning("Advertencia", dataJson.getString("mensaje"));
                            this.egreso = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarEgresoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.egreso = null;
                    Window.close(event);
                }
            });
        }
    }

    @FXML
    private void cancelarEgreso(ActionEvent event) {
        Window.close(event);
        this.egreso=null;
    }

    public void cargarEgreso() {
        this.txt_observacionesEditar.setText(egreso.getObservaciones());
        this.txt_motivoEditar.setText(egreso.getMotivo());
    }

    private void cargarConcepto() {
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

    private ObservableList getAllConcepto() {

        String respuesta = Requests.get("/catalogoConcepto/getAllCatalogoEgreso/");
        Gson gson = new Gson();

        TypeToken<List<CatalogoConcepto>> token = new TypeToken<List<CatalogoConcepto>>() {
        };

        List<CatalogoConcepto> listaC = gson.fromJson(respuesta, token.getType());

        comboBoxListConcepto = FXCollections.observableArrayList(listaC);
        System.out.print(comboBoxListConcepto);
        return comboBoxListConcepto;
    }

    private void cargarRancho() {
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

    private ObservableList getAllRancho() {

        String respuesta = Requests.get("/rancho/getAllRanchoActivo/");
        Gson gson = new Gson();

        TypeToken<List<Rancho>> token = new TypeToken<List<Rancho>>() {
        };

        List<Rancho> listaR = gson.fromJson(respuesta, token.getType());

        comboBoxListRancho = FXCollections.observableArrayList(listaR);
        System.out.print(comboBoxListRancho);
        return comboBoxListRancho;
    }
}
    