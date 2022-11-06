package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import io.github.kahenteikou.kotlinoder.instrumentation.Invocation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;

public class InvocationNodeValueSkin extends NodeSkinBase{
    public InvocationNodeValueSkin(FXSkinFactory skinFactory, VNode model, VFlow controller) {
        super(skinFactory, model, controller);
        LogManager.getLogger().info("InvocationNodeValueSkin new");
    }

    @Override
    protected Node CreateView() {
        Label retLabel=new Label(((Invocation)(getModel().getValueObject().getValue())).getVariableName());
        retLabel.setFont(new Font(30));
        retLabel.setAlignment(Pos.CENTER);
        return retLabel;
    }
}
