
package ganaderiafx.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class Window {
    public static void close(ActionEvent event){
       //Me devuelve el elemento al que hice click
        Node source = (Node) event.getSource();
        //Me devuelve la ventana donde se encuentra el elemento
        Stage stage = (Stage) source.getScene().getWindow();
        
        stage.close();
    }
    public static Stage getStageByEvent(ActionEvent event){
        Node source = (Node) event.getSource();
        return(Stage) source.getScene().getWindow();
    }
    public static Stage getStageByNode(Node node){
        return(Stage) node.getScene().getWindow();
    }
}
