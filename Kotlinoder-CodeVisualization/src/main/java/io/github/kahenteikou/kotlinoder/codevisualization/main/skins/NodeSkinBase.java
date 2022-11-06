package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXFlowNodeSkinBase;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;

public abstract class NodeSkinBase extends FXFlowNodeSkinBase {
    public NodeSkinBase(FXSkinFactory skinFactory,
                        VNode model, VFlow controller){
        super(skinFactory, model, controller);
    }
}
