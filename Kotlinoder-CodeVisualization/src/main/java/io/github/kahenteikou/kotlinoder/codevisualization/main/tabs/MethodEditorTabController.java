package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MethodEditorTabController implements Initializable {
    private MethodDeclaration md;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LogManager.getLogger("MethodEditorTabController").info("Init");
    }
    public void setMethodinfo(MethodDeclaration md){
        this.md=md;
    }
}
