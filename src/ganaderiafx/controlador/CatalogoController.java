
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.CatalogoConcepto;
import ganaderiafx.modelo.pojos.Rol;
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


public class CatalogoController implements Initializable {

    @FXML
    private Pane pnl_categoria;
    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Button btn_nuevaCatalogo;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_activarCatalogo;
    @FXML
    private Button btn_desactivarCatalogo;
    @FXML
    private TableView<CatalogoConcepto> tbl_catalogo;
    @FXML
    private TableColumn<CatalogoConcepto, Integer> tcl_idCatalogo;
    @FXML
    private TableColumn<CatalogoConcepto, String> tcl_catalogo;
    @FXML
    private TableColumn<CatalogoConcepto, String> tcl_conceptoCatalogo;
    @FXML
    private TableColumn<CatalogoConcepto, String> tbl_estatusCatalogo;
    @FXML
    private Button btn_nuevaRol;
    @FXML
    private Button btn_editarRol;
    @FXML
    private Button btn_activarRol;
    @FXML
    private Button btn_desactivarRol;
    @FXML
    private TableView<Rol> tbl_rol;
    @FXML
    private TableColumn<Rol, Integer> tcl_idRol;
    @FXML
    private TableColumn<Rol, String> tcl_nombre;
    @FXML
    private TableColumn<Rol, String> tcl_estatus;
    @FXML
    private Label lbl_nombreUsuarioCatalogo;
    @FXML
    private TextField txt_buscarId;
    @FXML
    private Button btn_buscarId;
    @FXML
    private Button btn_limpiarId;
    
    CatalogoConcepto catalogo = null;
    Rol rol = null;
    Usuario nombreUsuario=null;
    
    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nombreUsuarioCatalogo.setText(nombreUsuario.getNombre());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTablaCatalogo();
        this.cargarTablaRol();
    }    

    @FXML
    private void nuevaCatalogo(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarCatalogoFXML.fxml"));

            Parent formCatalogoRegistrar = loader.load();

            RegistrarCatalogoController ctrl = loader.getController();
            ctrl.setData(null);

            Scene scene = new Scene(formCatalogoRegistrar);
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Catalogo)");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargarTablaCatalogo();
    }

    @FXML
    private void editarCatalogo(ActionEvent event) {
        if (this.catalogo != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarCatalogoFXML.fxml"));

                Parent formCatalogoEditar = loader.load();  //nodo raiz

                EditarCatalogoController ctrl = loader.getController();
                ctrl.setData(this.catalogo, false);

                Scene scene = new Scene(formCatalogoEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Catalogo)");
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Catalogo...");
            alert.showAndWait();
        }
        this.cargarTablaCatalogo();
    }

    @FXML
    private void activarCatalogo(ActionEvent event) {

        if (this.catalogo != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que desea activar el catálogo?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idCatalogoConcepto", this.catalogo.getIdCatalogoConcepto());

                        String respuesta = Requests.post("/catalogoConcepto/actualizarEstatus/", params);

                        String estado = this.catalogo.getEstatus();

                        if ("Inactivo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.catalogo = null;
                                this.cargarTablaCatalogo();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.catalogo = null;
                                this.cargarTablaCatalogo();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El catálogo ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.catalogo = null;
                            this.cargarTablaCatalogo();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.catalogo = null;
                    this.cargarTablaCatalogo();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Catálogo...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarCatalogo(ActionEvent event) {

        if (this.catalogo != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que desea desactivar el catálogo?...");

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    try {
                        HashMap<String, Object> params = new LinkedHashMap<>();
                        params.put("idCatalogoConcepto", this.catalogo.getIdCatalogoConcepto());

                        String respuesta = Requests.post("/catalogoConcepto/eliminarCatalogo/", params);

                        String estado = this.catalogo.getEstatus();

                        if ("Activo".equals(estado)) {

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("error") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.catalogo = null;
                                this.cargarTablaCatalogo();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.catalogo = null;
                                this.cargarTablaCatalogo();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El catálogo ya esta Inactivo...");
                            alertInactivo.showAndWait();
                            this.catalogo = null;
                            this.cargarTablaCatalogo();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.catalogo = null;
                    this.cargarTablaCatalogo();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Catálogo...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void nuevaRol(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarRolFXML.fxml"));

            Parent formRolRegistrar = loader.load();  //nodo raiz

            RegistrarRolController ctrl = loader.getController();
            ctrl.setData(null);

            Scene scene = new Scene(formRolRegistrar);
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Rol)");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargarTablaRol();
    }

    @FXML
    private void editarRol(ActionEvent event) {
        if (this.rol != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarRolFXML.fxml"));

                Parent formRolEditar = loader.load();  //nodo raiz

                EditarRolController ctrl = loader.getController();
                ctrl.setData(this.rol, false);

                Scene scene = new Scene(formRolEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Rol)");
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Rol...");
            alert.showAndWait();
        }
        this.cargarTablaRol();
    }

    @FXML
    private void activarRol(ActionEvent event) {
        
        if (this.rol != null) {

            if (this.rol.getIdRol() != 201) {
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText(null);
                alert.setContentText("¿Seguro que desea activar el rol?...");

                alert.showAndWait().ifPresent(response -> {

                    if (response == ButtonType.OK) {

                        try {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idRol", this.rol.getIdRol());

                            String respuesta = Requests.post("/rol/actualizarEstatus/", params);

                            String estado = this.rol.getEstatus();

                            if ("Inactivo".equals(estado)) {

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("error") == false) {

                                    Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                    alertC.setTitle("Informativo");
                                    alertC.setHeaderText(null);
                                    alertC.setContentText(dataJson.getString("mensaje"));
                                    alertC.showAndWait();
                                    this.rol = null;
                                    this.cargarTablaRol();

                                } else {
                                    Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                    alertN.setTitle("Informativo");
                                    alertN.setHeaderText(null);
                                    alertN.setContentText(dataJson.getString("mensaje"));
                                    alertN.showAndWait();
                                    this.rol = null;
                                    this.cargarTablaRol();
                                }
                            } else {
                                Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                                alertInactivo.setTitle("Informativo");
                                alertInactivo.setHeaderText(null);
                                alertInactivo.setContentText("El rol ya esta Activo...");
                                alertInactivo.showAndWait();
                                this.rol = null;
                                this.cargarTablaRol();
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.rol = null;
                        this.cargarTablaRol();
                    }
                });
            }else{
                Alert alertI = new Alert(Alert.AlertType.WARNING);
                alertI.setTitle("Advertencia");
                alertI.setHeaderText(null);
                alertI.setContentText("El rol de Administrador no es puede modificar...");
                alertI.showAndWait();
            }
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Rol...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarRol(ActionEvent event) {                         
    
        if (this.rol != null) {

            if (this.rol.getIdRol() != 201) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText(null);
                alert.setContentText("¿Seguro que desea desactivar el rol?...");

                alert.showAndWait().ifPresent(response -> {

                    if (response == ButtonType.OK) {

                        try {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idRol", this.rol.getIdRol());

                            String respuesta = Requests.post("/rol/eliminarRol/", params);

                            String estado = this.rol.getEstatus();

                            if ("Activo".equals(estado)) {

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("error") == false) {

                                    Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                    alertC.setTitle("Informativo");
                                    alertC.setHeaderText(null);
                                    alertC.setContentText(dataJson.getString("mensaje"));
                                    alertC.showAndWait();
                                    this.rol = null;
                                    this.cargarTablaRol();

                                } else {
                                    Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                    alertN.setTitle("Informativo");
                                    alertN.setHeaderText(null);
                                    alertN.setContentText(dataJson.getString("mensaje"));
                                    alertN.showAndWait();
                                    this.rol = null;
                                    this.cargarTablaRol();
                                }
                            } else {
                                Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                                alertInactivo.setTitle("Informativo");
                                alertInactivo.setHeaderText(null);
                                alertInactivo.setContentText("El rol ya esta Inactivo...");
                                alertInactivo.showAndWait();
                                this.rol = null;
                                this.cargarTablaRol();
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(CatalogoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.rol = null;
                        this.cargarTablaRol();
                    }
                });
            } else{
                Alert alertI = new Alert(Alert.AlertType.WARNING);
                alertI.setTitle("Advertencia");
                alertI.setHeaderText(null);
                alertI.setContentText("El rol de Administrador no es puede modificar...");
                alertI.showAndWait();
            }

        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Debe seleccionar un Rol...");
            alertI.showAndWait();
        }
    }

    
    public void cargarTablaCatalogo(){
        String respuesta = "";
        tbl_catalogo.getItems().clear();
        
        respuesta = Requests.get("/catalogoConcepto/getAllCatalogo/");
        Gson gson = new Gson();
        
        TypeToken<List<CatalogoConcepto>> token = new TypeToken<List<CatalogoConcepto>>(){   
        };

        List<CatalogoConcepto> listaCatalogo = gson.fromJson(respuesta, token.getType());
        
        tcl_idCatalogo.setCellValueFactory(new PropertyValueFactory<>("idCatalogoConcepto"));
        tcl_catalogo.setCellValueFactory(new PropertyValueFactory<>("catalogo"));
        tcl_conceptoCatalogo.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tbl_estatusCatalogo.setCellValueFactory(new PropertyValueFactory<>("estatus"));
      
        
        listaCatalogo.forEach(e ->{
            tbl_catalogo.getItems().add(e);
        });
    }
    
    public void cargarTablaRol(){
        String respuesta = "";
        tbl_rol.getItems().clear();
        
        respuesta = Requests.get("/rol/getAllRol/");
        Gson gson = new Gson();
        
        TypeToken<List<Rol>> token = new TypeToken<List<Rol>>(){   
        };

        List<Rol> listaRol = gson.fromJson(respuesta, token.getType());
        
        tcl_idRol.setCellValueFactory(new PropertyValueFactory<>("idRol"));
        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        
        
        listaRol.forEach(e ->{
            tbl_rol.getItems().add(e);
        });
    }

    @FXML
    private void clickTableCatalogo(MouseEvent event) {
        String respuesta ="";
        if(tbl_catalogo.getSelectionModel().getSelectedItem() != null){
            catalogo = tbl_catalogo.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void clickTableRol(MouseEvent event) {
        String respuesta ="";
        if(tbl_rol.getSelectionModel().getSelectedItem() != null){
            rol = tbl_rol.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void buscarId(ActionEvent event) {                                  //FALTAAAAAAAAAA
    }

    @FXML
    private void limpiarId(ActionEvent event) {
        txt_buscarId.setText("");
        this.cargarTablaCatalogo();
        this.cargarTablaRol();
    }
}
