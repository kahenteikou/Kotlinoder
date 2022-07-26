package io.github.kahenteikou.kotlinoder.Controllers;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.fx.VCanvas;
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
    private Pane MainPane;
    private Pane rootPane;
    private VCanvas canvas;
    VFlow workflow;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas=new VCanvas();
        Pane root=(Pane)canvas.getContent();
        MainPane.getChildren().add(root);
        rootPane=root;
    }
    @FXML
    public void File_Open_OnAction(ActionEvent event){

    }
    @FXML
    public void File_Quit_OnAction(ActionEvent event){
        Platform.exit();
    }

}
