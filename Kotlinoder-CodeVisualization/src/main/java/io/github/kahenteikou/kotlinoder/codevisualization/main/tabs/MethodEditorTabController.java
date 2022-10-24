package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import eu.mihosoft.vrl.workflow.*;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import eu.mihosoft.vrl.workflow.fx.FXValueSkinFactory;
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import io.github.kahenteikou.kotlinoder.instrumentation.Scope;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MethodEditorTabController implements Initializable {
    private MethodDeclaration md;
    /*@FXML
    private Pane view;*/
    @FXML
    private ScrollPane canvas;
    private Pane rootPane;
    private VFlow flow;
    private VNode rootNode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LogManager.getLogger("MethodEditorTabController").info("Init");
        /*ScalableContentPane canvas = new ScalableContentPane();

        canvas.setStyle("-fx-background-color: rgb(0,0,0)");
        canvas.setMaxScaleX(1.0);
        canvas.setMaxScaleY(1.0);
        canvas.setMinScaleX(1.0);
        canvas.setMinScaleY(1.0);
        view.getChildren().add(canvas);*/
        //ScrollPane canvas=new ScrollPane();
        //canvas.setStyle("-fx-background-color: rgb(0,0,0)");
        //view.getChildren().add(canvas);
        Pane root=new Pane();
        canvas.setContent(root);
        //view.setStyle("-fx-background-color: rgb(0,0,0)");
        //root.setStyle("-fx-background-color: rgb(0,0,0)");
        //view.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));");
        rootPane=root;
        flow= FlowFactory.newFlow();
        FXValueSkinFactory fXSkinFactory = new FXValueSkinFactory(rootPane);
        flow.setSkinFactories(fXSkinFactory);
    }
    public void setMethodinfo(MethodDeclaration md){
        this.md=md;

        generateNodes();
        flow.setVisible(true);
    }
    private void generateNodes(){
        rootNode=flow.newNode();
        rootNode.setTitle("Entry Point");
        rootNode.getVisualizationRequest().set(VisualizationRequest.KEY_DISABLE_EDITING,true);
        //rootNode.addInput("control");
        Connector cn1=rootNode.addOutput("control");

        rootNode.setWidth(115);
        rootNode.setHeight(60);
        //flow.getModel().setVisible(true);
        /*VNode node2=flow.newNode();
        Connector cn2=node2.addInput("control");
        //rootNode.setMainOutput(cn1);
        //node2.setMainInput(cn2);
        //VFlow subflow = flow.newSubFlow();
        //flow.connect(cn1,cn2);
         */



    }
    private VFlow scopeToFlow(Scope scope, VFlow parent){
        VFlow resultFlow=parent.newSubFlow();
        return resultFlow;
    }
}
