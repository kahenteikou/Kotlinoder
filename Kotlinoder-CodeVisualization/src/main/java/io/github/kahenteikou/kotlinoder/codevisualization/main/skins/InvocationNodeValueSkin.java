package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InvocationNodeValueSkin extends NodeSkinBase{
    public InvocationNodeValueSkin(FXSkinFactory skinFactory, VNode model, VFlow controller) {
        super(skinFactory, model, controller);
    }

    @Override
    protected Node CreateView() {
        Label retLabel=new Label("Hello World");
        retLabel.setFont(new Font(30));
        retLabel.setAlignment(Pos.CENTER);
        return retLabel;
    }
}
