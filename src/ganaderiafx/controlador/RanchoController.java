/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Rancho;
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

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class RanchoController implements Initializable {

    @FXML
    private TableView<Rancho> tbl_rancho;
    @FXML
    private TableColumn<Rancho, String> tcl_nombreRancho;
    @FXML
    private TableColumn<Rancho, String> tcl_coloniaRacho;
    @FXML
    private TableColumn<Rancho, String> tcl_calleRancho;
    @FXML
    private TableColumn<Rancho, Integer> tcl_numExtRancho;
    @FXML
    private TableColumn<Rancho, String> tcl_estatusRancho;
    @FXML
    private TableColumn<Rancho, String> tcl_usuarioRancho;
    @FXML
    private Button btn_nuevaRancho;
    @FXML
    private Button btn_editarRancho;
    @FXML
    private Button btn_activarRancho;
    @FXML
    private Button btn_desactivarRancho;
    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarRancho;
    @FXML
    private Button btn_buscarRancho;
    @FXML
    private Button btn_limpiarRancho;
    @FXML
    private Label lbl_nombreUsuarioRancho;

    Rancho rancho = null;
    Usuario nombreUsuario = null;

    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nombreUsuarioRancho.setText(nombreUsuario.getNombre());
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }    

    @FXML
    private void clickTable(MouseEvent event) {
        String respuesta ="";
        if(tbl_rancho.getSelectionModel().getSelectedItem() != null){
            rancho = tbl_rancho.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void nuevaRancho(ActionEvent event) {
        try {
            Stage stage = new Stage();   
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarRanchoFXML.fxml")); 
            
            Parent formRanchoRegistrar = loader.load(); 
            
            RegistrarRanchoController ctrl = loader.getController();   
            ctrl.setData(null);
               
            Scene scene = new Scene(formRanchoRegistrar);  
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Rancho)");
            stage.setResizable(false);
            stage.showAndWait();
            this.rancho = null;
            this.cargarTabla();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarRancho(ActionEvent event) {
        if(this.rancho != null){
            try {
                Stage stage = new Stage();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarRanchoFXML.fxml"));
                
                Parent formRanchoEditar = loader.load();  
                
                EditarRanchoController ctrl = loader.getController();
                ctrl.setData(this.rancho);
                
                Scene scene = new Scene(formRanchoEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Rancho)");
                stage.setResizable(false);
                stage.showAndWait();
                this.rancho = null;
                this.cargarTabla();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Rancho...");
            alert.showAndWait();
        }
         
    }

    @FXML
    private void activarRancho(ActionEvent event) {
         if (this.rancho != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que desea activar el rancho?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {
                    
                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idRancho", this.rancho.getIdRancho());
                        
                        String respuesta = Requests.post("/rancho/actualizarEstatus/", params);

                        String estado = this.rancho.getEstatus();

                        if ("Inactivo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.rancho = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.rancho = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El rancho ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.rancho = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(RanchoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.rancho = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Rancho...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarRancho(ActionEvent event) {
 
        if (this.rancho != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que desea desactivar el rancho?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idRancho", this.rancho.getIdRancho());
         
                        String respuesta = Requests.post("/rancho/eliminarRancho/", params);

                        String estado = this.rancho.getEstatus();

                        if ("Activo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.rancho = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.rancho = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El rancho ya esta Inactivo...");
                            alertInactivo.showAndWait();
                            this.rancho = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(RanchoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.rancho = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Rancho...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void buscarRancho(ActionEvent event) {
        
        String buscar = this.txt_buscarRancho.getText();
        
        String respuesta = "";
        tbl_rancho.getItems().clear();

        respuesta = Requests.get("/rancho/getRanchoById/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Rancho>> token = new TypeToken<List<Rancho>>() {
        };

        List<Rancho> listaR = gson.fromJson(respuesta, token.getType());
        
        
        tcl_nombreRancho.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_coloniaRacho.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        tcl_calleRancho.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tcl_numExtRancho.setCellValueFactory(new PropertyValueFactory<>("numExt"));
        tcl_estatusRancho.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioRancho.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        
        listaR.forEach(e ->{
            tbl_rancho.getItems().add(e);
        });
    }

    @FXML
    private void limpiarRancho(ActionEvent event) {
        txt_buscarRancho.setText("");
        this.cargarTabla();
    }
    
    public void cargarTabla(){
        String respuesta = "";
        tbl_rancho.getItems().clear();
        
        respuesta = Requests.get("/rancho/getAllRancho/");
        Gson gson = new Gson();
        
        TypeToken<List<Rancho>> token = new TypeToken<List<Rancho>>(){   
        };

        List<Rancho> listaRancho = gson.fromJson(respuesta, token.getType());
        
        
        tcl_nombreRancho.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_coloniaRacho.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        tcl_calleRancho.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tcl_numExtRancho.setCellValueFactory(new PropertyValueFactory<>("numExt"));
        tcl_estatusRancho.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioRancho.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        
        listaRancho.forEach(e ->{
            tbl_rancho.getItems().add(e);
        });
    }
}
