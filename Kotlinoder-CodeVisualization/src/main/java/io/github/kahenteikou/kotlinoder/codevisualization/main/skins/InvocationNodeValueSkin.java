package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS;
import io.github.kahenteikou.kotlinoder.instrumentation.Invocation;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;

public class InvocationNodeValueSkin extends NodeSkinBase{
    public InvocationNodeValueSkin(FXSkinFactory skinFactory, VNode model, VFlow controller) {
        super(skinFactory, model, controller);
    }

    @Override
    protected Node CreateView() {
        LogManager.getLogger().info("InvocationNodeValueSkin CreateView!");

        FXMLLoader loader=new FXMLLoader(this.getClass().getResource("InvoAcationNodeValuekun.fxml"));
        try{
            loader.load();
        }catch (Exception e){
            LogManager.getLogger().error("error!",e);
        }
        Label retLabel=new Label(((Invocation)(getModel().getValueObject().getValue())).getVariableName());
        retLabel.setFont(new Font(30));
        retLabel.setAlignment(Pos.CENTER);
        return retLabel;
    }
}
