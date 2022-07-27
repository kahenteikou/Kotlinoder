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
import jfxtras.labs.scene.layout.ScalableContentPane;

public class MainWindowController  implements Initializable {
    @FXML
    private ScalableContentPane MainPane;
    private ScalableContentPane rootPane;
    private VCanvas canvas;
    VFlow workflow;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        canvas=new VCanvas();
        Pane root=(Pane)canvas.getContent();
        MainPane.getChildren().add(canvas);*/
        rootPane=MainPane;
        workflow= FlowFactory.newFlow();
        VNode node1=workflow.newNode();
        node1.setTitle("Node1");
        node1.setWidth(300);
        node1.setHeight(200);

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
