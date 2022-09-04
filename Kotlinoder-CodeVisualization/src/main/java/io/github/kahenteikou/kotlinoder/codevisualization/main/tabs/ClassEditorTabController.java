package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassEditorTabController implements Initializable {
    @FXML
    private TextField ClassNameTxtField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogManager.getLogger("ClassEditorTabController").info("Init");
    }
}
