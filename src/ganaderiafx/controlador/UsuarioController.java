
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Usuario;
import ganaderiafx.utils.Window;
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


public class UsuarioController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarUsuario;
    @FXML
    private Button btn_buscarUsuario;
    @FXML
    private Button btn_limpiarUsuario;
    @FXML
    private Label lbl_nombreUsuarioUsuario;
    @FXML
    private Button btn_nuevaUsuario;
    @FXML
    private Button btn_editarUsuario;
    @FXML
    private Button btn_activarUsuario;
    @FXML
    private Button btn_desactivarUsuario;
    @FXML
    private TableView<Usuario> tbl_usuario;
    @FXML
    private TableColumn<Usuario, Integer> tcl_idUsuario;
    @FXML
    private TableColumn<Usuario, String> tcl_nombreUsuario;
    @FXML
    private TableColumn<Usuario, String> tcl_apellidoPaterno;
    @FXML
    private TableColumn<Usuario, String> tcl_apellidoMaterno;
    @FXML
    private TableColumn<Usuario, String> tcl_usuarioUsuario;
    @FXML
    private TableColumn<Usuario, String> tcl_password;
    @FXML
    private TableColumn<Usuario, String> tcl_estatusUsuario;
    @FXML
    private TableColumn<Usuario, Integer> tcl_rolUsuario;

    Usuario usuario = null;
    Usuario nombreUsuario=null;
    
    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nombreUsuarioUsuario.setText(nombreUsuario.getNombre());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.cargarTabla();
    }    

    @FXML
    private void buscarUsuario(ActionEvent event) {                         
        String buscar = this.txt_buscarUsuario.getText();

        String respuesta = "";
        tbl_usuario.getItems().clear();

        respuesta = Requests.get("/usuario/getUsuarioById/"+buscar);
        Gson gson = new Gson();

        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>() {
        };

        List<Usuario> listaUsuario = gson.fromJson(respuesta, token.getType());
              
        tcl_idUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        tcl_nombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_usuarioUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcl_estatusUsuario.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_rolUsuario.setCellValueFactory(new PropertyValueFactory<>("idRol"));
        
        listaUsuario.forEach(e ->{
            tbl_usuario.getItems().add(e);
        });
                 
    }

    @FXML
    private void limpiarUsuario(ActionEvent event) {
        txt_buscarUsuario.setText("");
        this.cargarTabla();
    }

    @FXML
    private void nuevaUsuario(ActionEvent event) {
        try {
            Stage stage = new Stage();   
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarUsuarioFXML.fxml")); 
            
            Parent formUsuarioRegistrar = loader.load(); 
            
            RegistrarUsuarioController ctrl = loader.getController();   
            ctrl.setData(null);
               
            Scene scene = new Scene(formUsuarioRegistrar);  
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Usuario)");
            stage.setResizable(false);
            stage.show();  
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargarTabla();
        this.usuario=null;
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
        if(this.usuario != null){
            try {
                Stage stage = new Stage();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarUsuarioFXML.fxml"));
                
                Parent formUsuarioEditar = loader.load();  
                
                EditarUsuarioController ctrl = loader.getController();
                ctrl.setData(this.usuario,false);
                
                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Usuario)");
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Usuario...");
            alert.showAndWait();
        }
         this.cargarTabla();
         this.usuario=null;
    }

    @FXML
    private void activarUsuario(ActionEvent event) {                        
        if (this.usuario != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que desea activar el usuario?...");
           

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idUsuario", this.usuario.getIdUsuario());
                        String respuesta = Requests.post("/usuario/actualizarEstatus/", params);

                        String estado = this.usuario.getEstatus();
                        
                        if ("Inactivo".equals(estado)) {
                            
                            JSONObject dataJson = new JSONObject(respuesta);
                            
                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.usuario = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.usuario = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El usuario ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.usuario = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.usuario = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Usuario...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarUsuario(ActionEvent event) {                    
        if(this.usuario != null){
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que desea desactivar el usuario?...");
            //alert.showAndWait();
            
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    
                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idUsuario",this.usuario.getIdUsuario());
                        String respuesta = Requests.post("/usuario/eliminarUsuario/", params);
                        
                        String estado = this.usuario.getEstatus();
                        if("Activo".equals(estado)){
                            
                            JSONObject dataJson = new JSONObject(respuesta);
                            
                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.usuario = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.usuario = null;
                                this.cargarTabla();
                            }
                        } else{
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El usuario ya esta Inactivo...");
                            alertInactivo.showAndWait();
                            this.usuario = null;
                            this.cargarTabla();
                        }                      
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.usuario = null;
                    this.cargarTabla();
                }
            });
        }else{
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Usuario...");
            alertI.showAndWait();
        }
        //this.cargarTabla();
    }
    
    public void cargarTabla(){
        String respuesta = "";
        tbl_usuario.getItems().clear();
        
        respuesta = Requests.get("/usuario/getAllUsers/");
        Gson gson = new Gson();
        
        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>(){   
        };

        List<Usuario> listaUsuario = gson.fromJson(respuesta, token.getType());
        
        tcl_idUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        tcl_nombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_usuarioUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcl_estatusUsuario.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_rolUsuario.setCellValueFactory(new PropertyValueFactory<>("idRol"));
        
        listaUsuario.forEach(e ->{
            tbl_usuario.getItems().add(e);
        });
    }

    @FXML
    private void clickTable(MouseEvent event) {
        String respuesta ="";
        if(tbl_usuario.getSelectionModel().getSelectedItem() != null){
            usuario = tbl_usuario.getSelectionModel().getSelectedItem();
        }
    }
}
