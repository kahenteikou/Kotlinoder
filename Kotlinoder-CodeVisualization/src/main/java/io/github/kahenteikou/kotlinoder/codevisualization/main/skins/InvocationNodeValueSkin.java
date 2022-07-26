package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS;
import io.github.kahenteikou.kotlinoder.instrumentation.Invocation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;

public class InvocationNodeValueSkin extends NodeSkinBase{
    public InvocationNodeValueSkin(FXSkinFactory skinFactory, VNode model, VFlow controller) {
        super(skinFactory, model, controller);
    }
    @FXML
    public TextField targetTextField;
    @FXML
    public TextField methodNameTextField;

    @Override
    protected Node CreateView() {
        LogManager.getLogger().info("InvocationNodeValueSkin CreateView!");
        Node retNode=null;
        FXMLLoader loader=new FXMLLoader(this.getClass().getResource("InvocationNodeValuekun.fxml"));
        try{
            loader.setController(this);
            loader.load();
            retNode=loader.getRoot();
            targetTextField.setText(((Invocation)(getModel().getValueObject().getValue())).getVariableName());
            targetTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(!newValue){
                        Invocation invokkun=(Invocation)(getModel().getValueObject().getValue());
                        if(invokkun != null){
                            invokkun.setVariableName(targetTextField.getText());
                            LogManager.getLogger().info("Changing target name");
                        }
                    }
                }
            });
            methodNameTextField.setText(((Invocation)(getModel().getValueObject().getValue())).getMethodName());
            methodNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(!newValue){
                        Invocation invokkun=(Invocation)(getModel().getValueObject().getValue());
                        if(invokkun != null){
                            invokkun.setMethodName(methodNameTextField.getText());
                            LogManager.getLogger().info("Changing method name");
                        }
                    }
                }
            });
        }catch (Exception e){
            LogManager.getLogger().error("error!",e);
        }
        return retNode;
    }
}
