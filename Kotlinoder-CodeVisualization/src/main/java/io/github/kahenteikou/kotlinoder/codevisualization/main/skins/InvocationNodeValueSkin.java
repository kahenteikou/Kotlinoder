package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS;
import io.github.kahenteikou.kotlinoder.instrumentation.Invocation;
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

    @Override
    protected Node CreateView() {
        LogManager.getLogger().info("InvocationNodeValueSkin CreateView!");
        Node retNode=null;
        FXMLLoader loader=new FXMLLoader(this.getClass().getResource("InvocationNodeValuekun.fxml"));
        try{
            loader.setController(this);
            loader.load();
            retNode=loader.getRoot();
            targetTextField.setText("New Method");
        }catch (Exception e){
            LogManager.getLogger().error("error!",e);
        }
        return retNode;
    }
}
