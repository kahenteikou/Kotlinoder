package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;
import io.github.kahenteikou.kotlinoder.instrumentation.Modifier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassEditorTabController implements Initializable {
    @FXML
    private TextField ClassNameTxtField;
    @FXML
    private ComboBox<String> VisiblyModificationTypeComboBox;
    @FXML
    private TitledPane CLSInfoTitledPane;
    private ClassDeclaration cd___;
    private Modifier currentMod;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogManager.getLogger("ClassEditorTabController").info("Init");
        ClassNameTxtField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(cd___ != null){
                        cd___.setName(ClassNameTxtField.getText());
                        LogManager.getLogger("CLassEditorTabController").info("Changing class name");
                    }
                }
            }
        });
        VisiblyModificationTypeComboBox.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if(!newValue){
                            if(cd___ != null){
                                cd___.getClassModifiers().getModifiers().remove(observable)
                                LogManager.getLogger("CLassEditorTabController").info("Changing class modifier");
                            }
                        }
                    }
                }
        );
    }
    @FXML
    private void VisiblyModificationTypeComboBox_OnAction(ActionEvent event){

        LogManager.getLogger("ClassEditorTabController").info("ComboAction");
    }
    public void setClsInfo(ClassDeclaration cd){
        cd___=cd;
        ClassNameTxtField.setText(cd.getName());
        for(Modifier modkun :cd.getClassModifiers().getModifiers()){
            switch(modkun){
                case PRIVATE:
                    VisiblyModificationTypeComboBox.setValue("private");
                    currentMod=Modifier.PRIVATE;
                    break;
                case PUBLIC:
                    VisiblyModificationTypeComboBox.setValue("public");
                    currentMod=Modifier.PUBLIC;
                    break;
                case PROTECTED:
                    VisiblyModificationTypeComboBox.setValue("protected");
                    currentMod=Modifier.PROTECTED;
                    break;

            }
        }
        CLSInfoTitledPane.setExpanded(true);


    }
}
