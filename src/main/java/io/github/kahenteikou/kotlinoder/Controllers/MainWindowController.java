package io.github.kahenteikou.kotlinoder.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController  implements Initializable {
    @FXML
    private Button Button1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    public void Button1_OnAction(ActionEvent event){
        System.out.println("Button1_OnAction");
    }
}
