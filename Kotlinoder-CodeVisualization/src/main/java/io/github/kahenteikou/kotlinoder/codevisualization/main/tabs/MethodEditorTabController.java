package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import eu.mihosoft.vrl.workflow.FlowFactory;
import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MethodEditorTabController implements Initializable {
    private MethodDeclaration md;
    @FXML
    private Pane view;
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
        ScrollPane canvas=new ScrollPane();
        canvas.setStyle("-fx-background-color: rgb(0,0,0)");
        view.getChildren().add(canvas);
        Pane root=new Pane();
        canvas.setContent(root);
        view.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));");
        rootPane=root;
        flow= FlowFactory.newFlow();
        FXSkinFactory fXSkinFactory = new FXSkinFactory(rootPane);
        flow.setSkinFactories(fXSkinFactory);
        generateNodes();
    }
    public void setMethodinfo(MethodDeclaration md){
        this.md=md;
    }
    private void generateNodes(){
        rootNode=flow.newNode();
        rootNode.setTitle("Entry Point");
        rootNode.addOutput("STRUCTFLOW");
        flow.getModel().setVisible(true);
        flow.newNode();
    }
}
