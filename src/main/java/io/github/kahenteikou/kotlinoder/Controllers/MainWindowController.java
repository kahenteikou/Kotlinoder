package io.github.kahenteikou.kotlinoder.Controllers;

import eu.mihosoft.vrl.workflow.FlowFactory;
import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import eu.mihosoft.vrl.workflow.fx.VCanvas;
import eu.mihosoft.vrl.workflow.io.WorkflowIO;
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
        MainPane.getChildren().add(canvas);
        rootPane=root;
        workflow= FlowFactory.newFlow();
        workflow.newNode().setTitle("title");
        workflow.newNode().setTitle("title");
        workflow.newNode().setTitle("title");
        workflow.newNode().setTitle("title");
        updateUI();
    }
    @FXML
    public void File_Open_OnAction(ActionEvent event){

    }
    @FXML
    public void File_Quit_OnAction(ActionEvent event){
        Platform.exit();
    }
    private void updateUI(){
        rootPane.getChildren().clear();
        workflow.getModel().setVisible(true);

        FXSkinFactory skinFactory = new FXSkinFactory(rootPane);

        workflow.setSkinFactories(skinFactory);
    }
}
