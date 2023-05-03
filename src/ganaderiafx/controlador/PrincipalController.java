
package ganaderiafx.controlador;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class PrincipalController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void setData(HashMap<String,Object> context){          //crear este metodo en todos los controladores
        
        System.out.print(context);
    }
}
