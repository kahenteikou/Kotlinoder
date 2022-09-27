package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;
import io.github.kahenteikou.kotlinoder.instrumentation.Modifier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassEditorTabController implements Initializable {
    @FXML
    private TextField ClassNameTxtField;
    @FXML
    private ComboBox<String> VisiblyModificationTypeComboBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogManager.getLogger("ClassEditorTabController").info("Init");
    }
    @FXML
    private void VisiblyModificationTypeComboBox_OnAction(ActionEvent event){

        LogManager.getLogger("ClassEditorTabController").info("ComboAction");
    }
    public void setClsInfo(ClassDeclaration cd){
        ClassNameTxtField.setText(cd.getName());
        for(Modifier modkun :cd.getClassModifiers().getModifiers()){
            switch(modkun){
                case PRIVATE -> VisiblyModificationTypeComboBox.setValue("private");
                case PUBLIC -> VisiblyModificationTypeComboBox.setValue("public");
                case PROTECTED -> VisiblyModificationTypeComboBox.setValue("protected");

            }
        }
    }
}
