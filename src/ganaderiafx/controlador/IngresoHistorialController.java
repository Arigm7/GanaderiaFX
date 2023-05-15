/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganaderiafx.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ganaderiafx.api.requests.Requests;
import ganaderiafx.modelo.pojos.Ingreso;
import ganaderiafx.modelo.pojos.Usuario;
import java.net.URL;
import java.util.HashMap;
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
public class IngresoHistorialController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private TextField txt_buscarIngreso;
    @FXML
    private Button btn_buscarIngreso;
    @FXML
    private Button btn_limpiarIngreso;
    @FXML
    private Label lbl_nomUsuario_ingresoH;
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
    private TableColumn<Ingreso, String> tcl_catalogoIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_conceptoIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_estatusIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_ranchoIngreso;
    @FXML
    private TableColumn<Ingreso, String> tcl_usuarioIngreso;

    Usuario nombreUsuario= null;
    Ingreso ingreso = null;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }    
    
    
    public void setData(Usuario nombreUsuario){                  
        this.nombreUsuario=nombreUsuario;
        this.lbl_nomUsuario_ingresoH.setText(nombreUsuario.getNombre());
    }

    @FXML
    private void buscar(ActionEvent event) {
        String buscar = this.txt_buscarIngreso.getText();

        String respuesta = "";
       
        tbl_ingresos.getItems().clear();

        respuesta = Requests.get("/ingreso/getHistorialById/"+buscar);
        Gson gson = new Gson();
        TypeToken<List<Ingreso>> token = new TypeToken<List<Ingreso>>() {
        };

        List<Ingreso> listaIngreso = gson.fromJson(respuesta, token.getType());
        
        
        //System.out.println("A VER "+listaIngreso);
        
        tcl_idIngreso.setCellValueFactory(new PropertyValueFactory<>("idIngreso"));
        tcl_cantidadIngreso.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_observacionesIngreso.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_fechaModificacionIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_catalogoIngreso.setCellValueFactory(new PropertyValueFactory<>("catalogo"));
        tcl_conceptoIngreso.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tcl_ranchoIngreso.setCellValueFactory(new PropertyValueFactory<>("rancho"));
        tcl_estatusIngreso.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioIngreso.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaIngreso.forEach(e -> {
            tbl_ingresos.getItems().add(e);
        });
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txt_buscarIngreso.setText("");
        this.cargarTabla();
    }

    @FXML
    private void clickTableIngreso(MouseEvent event) {
        String respuesta ="";
        if(tbl_ingresos.getSelectionModel().getSelectedItem() != null){
            ingreso = tbl_ingresos.getSelectionModel().getSelectedItem();
        }
    }
    
     public void cargarTabla(){
        String respuesta = "";
        tbl_ingresos.getItems().clear();
        
        respuesta = Requests.get("/ingreso/getAllIngresoHistorial/");
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
        tcl_ranchoIngreso.setCellValueFactory(new PropertyValueFactory<>("rancho"));
        tcl_estatusIngreso.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_usuarioIngreso.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        
        listaIngreso.forEach(e ->{
            tbl_ingresos.getItems().add(e);
        });
    }
}
