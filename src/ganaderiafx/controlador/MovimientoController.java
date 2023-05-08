
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Egreso;
import ganaderiafx.modelo.pojos.Ingreso;
import ganaderiafx.modelo.pojos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MovimientoController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarMovimiento;
    @FXML
    private Button btn_buscarMovimiento;
    @FXML
    private Button btn_limpiarMovimiento;
    @FXML
    private Button btn_nuevaIngreso;
    @FXML
    private Button btn_editarIngreso;
    @FXML
    private Button btn_activarIngreso;
    @FXML
    private Button btn_desactivarIngresos;
    @FXML
    private TableView<Ingreso> tbl_ingresos;
    @FXML
    private TableColumn<Ingreso, Integer> tcl_idIngreso;
    @FXML
    private TableColumn<Ingreso, Integer> tcl_cantidadIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_observacionesIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_fechaCreacionIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_fechaModificacionIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_catalogoIngreso;                            //FALTAAAAAAAAA
    @FXML
    private TableColumn<Ingreso, String> tcl_conceptoIngreso;                       //FALTAAAAAAAAA
    @FXML
    private Button btn_nuevaEgreso;
    @FXML
    private Button btn_editarEgreso;
    @FXML
    private Button btn_activarEgreso;
    @FXML
    private Button btn_desactivarEgreso;
    @FXML
    private TableView<Egreso> tbl_egreso;
    @FXML
    private TableColumn<Egreso, Integer> tcl_idEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_motivoEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_observacionesEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_fechaCreacionEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_fechaMotificacionEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_conceptoEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_catalogoEgreso;
    @FXML
    private Label lbl_nomUsuario_movimiento;

    Ingreso ingreso = null;
    Egreso egreso = null;
    Usuario nombreUsuario= null;

    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nomUsuario_movimiento.setText(nombreUsuario.getNombre());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTablaIngreso();
        this.cargarTablaEgreso();
    }    

    @FXML
    private void buscarMovimiento(ActionEvent event) {
    }

    @FXML
    private void limpiarMovimiento(ActionEvent event) {
        txt_buscarMovimiento.setText("");
    }

    @FXML
    private void nuevaIngreso(ActionEvent event) {
        try {
            Stage stage = new Stage();   
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarIngresoFXML.fxml")); 
            
            Parent formIngresoRegistrar = loader.load(); 
            
            RegistrarIngresoController  ctrl = loader.getController();   
            ctrl.setData(null);
               
            Scene scene = new Scene(formIngresoRegistrar);  
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Ingreso)");
            stage.setResizable(false);
            stage.show();  
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargarTablaIngreso();
    }

    @FXML
    private void editarIngreso(ActionEvent event) {
        if(this.ingreso != null){
            try {
                Stage stage = new Stage();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarIngresoFXML.fxml"));
                
                Parent formIngresoEditar = loader.load();  
                
                EditarIngresoController ctrl = loader.getController();
                ctrl.setData(this.ingreso,false);
                
                Scene scene = new Scene(formIngresoEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Ingreso)");
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Ingreso...");
            alert.showAndWait();
        }
         this.cargarTablaIngreso();
        
    }

    @FXML
    private void activarIngreso(ActionEvent event) {
    }

    @FXML
    private void desactivarIngresos(ActionEvent event) {
    }

    @FXML
    private void nuevaEgreso(ActionEvent event) {
        try {
            Stage stage = new Stage();   
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/RegistrarEgresoFXML.fxml")); 
            
            Parent formEngresoRegistrar = loader.load(); 
            
            RegistrarEgresoController  ctrl = loader.getController();   
            ctrl.setData(null);
               
            Scene scene = new Scene(formEngresoRegistrar);  
            stage.setScene(scene);
            stage.setTitle("GANADERIA (Registrar Egresos)");
            stage.setResizable(false);
            stage.show();  
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargarTablaEgreso();
    }

    @FXML
    private void editarEgreso(ActionEvent event) {
        if (this.egreso != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ganaderiafx/gui/vista/EditarEgresoFXML.fxml"));

                Parent formEgresoEditar = loader.load();

                EditarEgresoController ctrl = loader.getController();
                ctrl.setData(this.egreso, true);

                Scene scene = new Scene(formEgresoEditar);
                stage.setScene(scene);
                stage.setTitle("GANADERIA (Editar Egreso)");
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un Egreso...");
            alert.showAndWait();
        }
        this.cargarTablaIngreso();
    }

    @FXML
    private void activarEgreso(ActionEvent event) {
    }

    @FXML
    private void desactivarEgreso(ActionEvent event) {
    }
    
    public void cargarTablaIngreso(){
        String respuesta = "";
        tbl_ingresos.getItems().clear();
        
        respuesta = Requests.get("/ingreso/getAllIngreso/");
        Gson gson = new Gson();
        
        TypeToken<List<Ingreso>> token = new TypeToken<List<Ingreso>>(){   
        };

        List<Ingreso> listaIngreso = gson.fromJson(respuesta, token.getType());
        
        tcl_idIngreso.setCellValueFactory(new PropertyValueFactory<>("idIngreso"));
        tcl_cantidadIngreso.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_observacionesIngreso.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_fechaModificacionIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_catalogoIngreso.setCellValueFactory(new PropertyValueFactory<>("catalogo"));
        tcl_conceptoIngreso.setCellValueFactory(new PropertyValueFactory<>("concepto"));

        
        listaIngreso.forEach(e ->{
            tbl_ingresos.getItems().add(e);
        });
    }
    
    public void cargarTablaEgreso() {
        String respuesta = "";
        tbl_egreso.getItems().clear();

        respuesta = Requests.get("/egreso/getAllEgreso/");
        Gson gson = new Gson();

        TypeToken<List<Egreso>> token = new TypeToken<List<Egreso>>() {
        };

        List<Egreso> listaEgreso = gson.fromJson(respuesta, token.getType());

        tcl_idEgreso.setCellValueFactory(new PropertyValueFactory<>("idEgreso"));
        tcl_motivoEgreso.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observacionesEgreso.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionEgreso.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_fechaMotificacionEgreso.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_catalogoEgreso.setCellValueFactory(new PropertyValueFactory<>("catalogo"));
        tcl_conceptoEgreso.setCellValueFactory(new PropertyValueFactory<>("concepto"));

        listaEgreso.forEach(e -> {
            tbl_egreso.getItems().add(e);
        });
    }

    @FXML
    private void clickTableIngreso(MouseEvent event) {
        String respuesta ="";
        if(tbl_ingresos.getSelectionModel().getSelectedItem() != null){
            ingreso = tbl_ingresos.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void clickTableEgreso(MouseEvent event) {
        String respuesta ="";
        if(tbl_egreso.getSelectionModel().getSelectedItem() != null){
            egreso = tbl_egreso.getSelectionModel().getSelectedItem();
        }
    }
    
}
