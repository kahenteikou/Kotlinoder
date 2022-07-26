package io.github.kahenteikou.kotlinoder.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController  implements Initializable {
    @FXML
    private Button Button1;
    @FXML
    private Pane MainPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    public void Button1_OnAction(ActionEvent event){
        System.out.println("Button1_OnAction");
    }
    @FXML
    public void File_Open_OnAction(ActionEvent event){

    }
    @FXML
    public void File_Quit_OnAction(ActionEvent event){
        Platform.exit();
    }

}
