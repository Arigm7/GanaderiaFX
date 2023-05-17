
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.modelo.pojos.Ingreso;
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


public class EditarIngresoController implements Initializable {

    @FXML
    private TextField txt_cantidadEditar;
    @FXML
    private Button btn_editarIngreso;
    @FXML
    private Button btn_cancelarIngreso;
    @FXML
    private TextArea txt_observacionesEditar;
    @FXML
    private ComboBox<String> cmb_ranchoEditar;
    @FXML
    private ComboBox<String> cmb_conceptoEditar;

    private Integer[] arrayIDConcepto;
    private ObservableList<CatalogoConcepto> comboBoxListConcepto;

    private Integer[] arrayIDRancho;
    private ObservableList<Rancho> comboBoxListRancho;

    Ingreso ingreso = null;
    Boolean isnew=false;
    String cantidad="";
    String id="";
    Usuario nombreUsuario = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarConcepto();
        this.cargarRancho();
    }    
    
    public void setData(Ingreso ingreso, Boolean isnew){ 
        this.ingreso=ingreso;
        this.isnew=isnew;
        this.cargarIngreso();
    }
    
    public void setDataUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @FXML
    private void editarIngreso(ActionEvent event) {

        int positionConcepto = this.cmb_conceptoEditar.getSelectionModel().getSelectedIndex();
        int positionRancho = this.cmb_ranchoEditar.getSelectionModel().getSelectedIndex();
        VentanaAlert alert = new VentanaAlert();

        if (this.txt_cantidadEditar.getText().isEmpty()
                || !this.txt_cantidadEditar.getText().substring(0, 1).matches("[0-9]*")
                || this.txt_observacionesEditar.getText().isEmpty()
                || positionConcepto <= -1
                || positionRancho <= -1) {

            alert.warning("Campos Vacios", "Alguno de los campos se encuentra Vacio O la cantidad tiene caracter");
        } else {

            Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
            alertI.setTitle("Confirmación");
            alertI.setHeaderText(null);
            alertI.setContentText("Seguro que desea actualizar el ingreso?...");

            alertI.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {
                    try {
                        int concepto = this.arrayIDConcepto[positionConcepto];
                        int rancho = this.arrayIDRancho[positionRancho];

                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idIngreso", this.ingreso.getIdIngreso());
                        params.put("cantidad", this.txt_cantidadEditar.getText());
                        params.put("observaciones", this.txt_observacionesEditar.getText());
                        params.put("idCatalogoConcepto", concepto);
                        params.put("idRancho", rancho);
                        params.put("idUsuario", this.nombreUsuario.getIdUsuario());

                        String respuesta = Requests.post("/ingreso/actualizarIngreso/", params);

                        JSONObject dataJson = new JSONObject(respuesta);

                        if ((Boolean) dataJson.get("error") == false) {
                            alert.information("Informativo", dataJson.getString("mensaje"));
                            this.ingreso = null;
                            Window.close(event);
                        } else {
                            alert.warning("Advertencia", dataJson.getString("mensaje"));
                            this.ingreso = null;
                            Window.close(event);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(EditarIngresoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.ingreso = null;
                    Window.close(event);
                }
            });

        }
    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
        Window.close(event);
        this.ingreso=null;
    }
    
    public void cargarIngreso(){
        cantidad = Integer.toString(ingreso.getCantidad());
        this.txt_cantidadEditar.setText(cantidad);
        this.txt_observacionesEditar.setText(ingreso.getObservaciones());        
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
        cmb_conceptoEditar.setItems(Obsnombre);
    }

    private ObservableList getAllConcepto() {

        String respuesta = Requests.get("/catalogoConcepto/getAllCatalogoIngreso/");
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
        cmb_ranchoEditar.setItems(Obsnombre);
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
