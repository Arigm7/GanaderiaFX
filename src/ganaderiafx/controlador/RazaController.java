
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.modelo.pojos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;


public class RazaController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarRaza;
    @FXML
    private Button btn_buscarRaza;
    @FXML
    private Button btn_limpiarRaza;
    @FXML
    private Label lbl_nombreUsuarioRaza;
    @FXML
    private Button btn_nuevaRaza;
    @FXML
    private Button btn_editarRaza;
    @FXML
    private Button btn_activarRaza;
    @FXML
    private Button btn_desactivarRaza;
    @FXML
    private TableView<Raza> tbl_raza;
    @FXML
    private TableColumn<Raza, Integer> tcl_idRaza;
    @FXML
    private TableColumn<Raza, String> tcl_nombreRaza;
    @FXML
    private TableColumn<Raza, String> tcl_estatusRaza;

    Raza raza = null;
    Usuario nombreUsuario=null;
    
    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nombreUsuarioRaza.setText(nombreUsuario.getNombre());
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }    

    @FXML
    private void buscarRaza(ActionEvent event) {   
        String buscar = this.txt_buscarRaza.getText();

        String respuesta = "";
        tbl_raza.getItems().clear();

        respuesta = Requests.get("/raza/getRazaById/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Raza>> token = new TypeToken<List<Raza>>() {
        };

        List<Raza> listaR = gson.fromJson(respuesta, token.getType());

        tcl_idRaza.setCellValueFactory(new PropertyValueFactory<>("idRaza"));
        tcl_nombreRaza.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusRaza.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        listaR.forEach(e -> {
            tbl_raza.getItems().add(e);
        });
    }          

    @FXML
    private void nuevaRaza(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarRazaFXML.fxml"));

            Parent formRazaRegistrar = loader.load();

            RegistrarRazaController ctrl = loader.getController();
            ctrl.setData(null);

            Scene scene = new Scene(formRazaRegistrar);
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Raza)");
            stage.setResizable(false);
            stage.showAndWait();
            this.cargarTabla();
            this.raza = null;
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.cargarTabla();
        this.raza=null;
    }

    @FXML
    private void editarRaza(ActionEvent event) {
        if (this.raza != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarRazaFXML.fxml"));

                Parent formRazaEditar = loader.load(); 

                EditarRazaController ctrl = loader.getController();
                ctrl.setData(this.raza, false);

                Scene scene = new Scene(formRazaEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Raza)");
                stage.setResizable(false);
                stage.showAndWait();
                this.cargarTabla();
                this.raza = null;
            } catch (IOException ex) {
                Logger.getLogger(RazaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar una Raza...");
            alert.showAndWait();
        }
        this.raza=null;
    }

    @FXML
    private void activarRaza(ActionEvent event) {
        
        if (this.raza != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que desea activar la raza?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {
                    
                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idRaza", this.raza.getIdRaza());
                        String respuesta = Requests.post("/raza/actualizarEstatus/", params);

                        String estado = this.raza.getEstatus();

                        if ("Inactivo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.raza = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.raza = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("La raza ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.raza = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.raza = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar una Raza...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarRaza(ActionEvent event) {
  
        if (this.raza != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que desea desactivar la raza?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idRaza", this.raza.getIdRaza());
                        String respuesta = Requests.post("/raza/eliminarRaza/", params);

                        String estado = this.raza.getEstatus();

                        if ("Activo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.raza = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.raza = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("La raza ya esta Inactiva...");
                            alertInactivo.showAndWait();
                            this.raza = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.raza = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar una Raza...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void limpiarRaza(ActionEvent event) {
        txt_buscarRaza.setText("");
        this.cargarTabla();
    }
    
    public void cargarTabla(){
        String respuesta = "";
        tbl_raza.getItems().clear();
        
        respuesta = Requests.get("/raza/getAllRaza/");
        Gson gson = new Gson();
        
        TypeToken<List<Raza>> token = new TypeToken<List<Raza>>(){   
        };

        List<Raza> listaRaza = gson.fromJson(respuesta, token.getType());
        
        tcl_idRaza.setCellValueFactory(new PropertyValueFactory<>("idRaza"));
        tcl_nombreRaza.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusRaza.setCellValueFactory(new PropertyValueFactory<>("estatus"));
      
        
        listaRaza.forEach(e ->{
            tbl_raza.getItems().add(e);
        });
    }

    @FXML
    private void clickTable(MouseEvent event) {
        String respuesta ="";
        if(tbl_raza.getSelectionModel().getSelectedItem() != null){
            raza = tbl_raza.getSelectionModel().getSelectedItem();
        }
    }
}
