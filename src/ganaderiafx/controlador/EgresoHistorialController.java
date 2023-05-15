/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Egreso;
import ganaderiafx.modelo.pojos.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class EgresoHistorialController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarEgreso;
    @FXML
    private Button btn_buscarEgreso;
    @FXML
    private Button btn_limpiarEgreso;
    @FXML
    private Label lbl_nomUsuario_egresoH;
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
    private TableColumn<Egreso, String> tcl_catalogoEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_conceptoEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_estatusEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_ranchoEgreso;
    @FXML
    private TableColumn<Egreso, String> tcl_usuarioEgreso;

    Egreso egreso = null;
    Usuario nombreUsuario= null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }    
    
    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nomUsuario_egresoH.setText(nombreUsuario.getNombre());
    }

    @FXML
    private void buscar(ActionEvent event) {
        
        String buscar = this.txt_buscarEgreso.getText();

        String respuesta = "";
       
        tbl_egreso.getItems().clear();

        respuesta = Requests.get("/egreso/getHistorialById/"+buscar);
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
        tcl_ranchoEgreso.setCellValueFactory(new PropertyValueFactory<>("rancho"));
        tcl_estatusEgreso.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioEgreso.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaEgreso.forEach(e -> {
            tbl_egreso.getItems().add(e);
        });
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txt_buscarEgreso.setText("");
        this.cargarTabla();
    }

    @FXML
    private void clickTableEgreso(MouseEvent event) {
    }
    
    public void cargarTabla() {
        String respuesta = "";
        tbl_egreso.getItems().clear();

        respuesta = Requests.get("/egreso/getAllEgresoHistorial/");
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
        tcl_ranchoEgreso.setCellValueFactory(new PropertyValueFactory<>("rancho"));
        tcl_estatusEgreso.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioEgreso.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaEgreso.forEach(e -> {
            tbl_egreso.getItems().add(e);
        });
    }
}
