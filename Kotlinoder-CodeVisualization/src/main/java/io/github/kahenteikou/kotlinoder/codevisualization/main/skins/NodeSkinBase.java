package io.github.kahenteikou.kotlinoder.codevisualization.main.skins;

import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VFlowModel;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXFlowNodeSkinBase;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane;
import javafx.geometry.Insets;
import javafx.scene.Node;

public abstract class NodeSkinBase extends FXFlowNodeSkinBase {
    public NodeSkinBase(FXSkinFactory skinFactory,
                        VNode model, VFlow controller){
        super(skinFactory, model, controller);
    }
    protected abstract Node CreateView();
    @Override
    public void updateView() {
        super.updateView();
        if(getModel() instanceof VFlowModel){
            return;
        }
        if(getModel().getValueObject().getValue()==null){
            return;
        }
        Node view=CreateView();
        if(view!=null){
            ScalableContentPane spane=new ScalableContentPane();
            spane.setPadding(new Insets(10));
            
        }
    }
}
