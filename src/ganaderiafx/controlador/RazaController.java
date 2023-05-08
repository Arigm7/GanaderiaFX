
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Raza;
import ganaderiafx.modelo.pojos.Usuario;
import java.io.IOException;
import java.net.URL;
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
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                stage.show();
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
    }

    @FXML
    private void activarRaza(ActionEvent event) {
    }

    @FXML
    private void desactivarRaza(ActionEvent event) {
    }

    @FXML
    private void limpiarRaza(ActionEvent event) {
        txt_buscarRaza.setText("");
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
