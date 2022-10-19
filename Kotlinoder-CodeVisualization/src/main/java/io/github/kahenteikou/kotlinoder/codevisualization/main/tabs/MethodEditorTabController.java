package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import eu.mihosoft.vrl.workflow.VFlow;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LogManager.getLogger("MethodEditorTabController").info("Init");
    }
    public void setMethodinfo(MethodDeclaration md){
        this.md=md;
    }
}
