package io.github.kahenteikou.kotlinoder.codevisualization.main.tabs;

import eu.mihosoft.vrl.workflow.*;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import eu.mihosoft.vrl.workflow.fx.FXValueSkinFactory;
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane;
import io.github.kahenteikou.kotlinoder.instrumentation.*;
import io.github.kahenteikou.kotlinoder.instrumentation.invokes.IInvokeAndStatement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class MethodEditorTabController implements Initializable {
    private MethodDeclaration md;
    /*@FXML
    private Pane view;*/
    @FXML
    private ScrollPane canvas;
    private Pane rootPane;
    private VFlow flow;
    private VNode rootNode;
    private HashMap<CodeEntity,VNode> invocationNodes= new HashMap<CodeEntity,VNode>();
    private HashMap<String,Connector> variableConnectors=new HashMap<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LogManager.getLogger("MethodEditorTabController").info("Init");
        /*ScalableContentPane canvas = new ScalableContentPane();

        canvas.setStyle("-fx-background-color: rgb(0,0,0)");
        canvas.setMaxScaleX(1.0);
        canvas.setMaxScaleY(1.0);
        canvas.setMinScaleX(1.0);
        canvas.setMinScaleY(1.0);
        view.getChildren().add(canvas);*/
        //ScrollPane canvas=new ScrollPane();
        //canvas.setStyle("-fx-background-color: rgb(0,0,0)");
        //view.getChildren().add(canvas);
        Pane root=new Pane();
        canvas.setContent(root);
        //view.setStyle("-fx-background-color: rgb(0,0,0)");
        //root.setStyle("-fx-background-color: rgb(0,0,0)");
        //view.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));");
        rootPane=root;
        flow= FlowFactory.newFlow();
        FXValueSkinFactory fXSkinFactory = new FXValueSkinFactory(rootPane);
        flow.setSkinFactories(fXSkinFactory);
    }
    public void setMethodinfo(MethodDeclaration md){
        this.md=md;

        generateNodes();
        flow.setVisible(true);
    }
    private void generateNodes(){
        rootNode=flow.newNode();
        rootNode.setTitle("Entry Point");
        rootNode.getVisualizationRequest().set(VisualizationRequest.KEY_DISABLE_EDITING,true);
        //rootNode.addInput("control");
        rootNode.setMainOutput(rootNode.addOutput("control"));

        rootNode.setWidth(115);
        rootNode.setHeight(60);
        //flow.getModel().setVisible(true);
        /*VNode node2=flow.newNode();
        Connector cn2=node2.addInput("control");
        //rootNode.setMainOutput(cn1);
        //node2.setMainInput(cn2);
        //VFlow subflow = flow.newSubFlow();
        //flow.connect(cn1,cn2);
         */
        scopeToFlowFirst(md,flow);

    }
    private VFlow scopeToFlow(Scope scope, VFlow parent){
        return scopeToFlow(scope,parent,null);
    }
    private VFlow scopeToFlow(Scope scope, VFlow parent,VNode prevkun){
        VFlow resultFlow=parent.newSubFlow();
        FXValueSkinFactory fXSkinFactory = new FXValueSkinFactory(rootPane);
        resultFlow.setSkinFactories(fXSkinFactory);
        invocationNodes.put(scope,resultFlow.getModel());
        String title=String.format("%s %s(): %s X",scope.getType(),scope.getName(),scope.getId());
        resultFlow.getModel().setTitle(title);
        VNode prevNode=null;
        if(prevkun != null) prevNode=prevkun;
        Boolean isClassOrScript=scope.getType()== ScopeType.CLASS||scope.getType()==ScopeType.COMPILATION_UNIT
                || scope.getType()==ScopeType.NONE;
        for(IInvokeAndStatement i:scope.getControlFlow().getCallObjects()){
            if(i instanceof Invocation){
                VNode n=null;
                if(((Invocation) i).isScope() &&!isClassOrScript ){
                    ScopeInvocation si=(ScopeInvocation) i;
                    n=scopeToFlow(si.getScope(),resultFlow).getModel();
                }else{
                    n=resultFlow.newNode();
                    n.setTitle("%s.%s():%s".formatted(((Invocation)i).getVariableName(),((Invocation)i).getMethodName(),
                            ((Invocation)i).getId()));
                    invocationNodes.put((Invocation)i,n);
                }
                n.setMainInput(n.addInput("control"));
                n.setMainOutput(n.addOutput("control"));
                if (prevNode != null) {
                    resultFlow.connect(prevNode, n, "control");
                }
                for(Variable v:((Invocation) i).getArguments()){
                    if(v!=null){
                        Connector input=n.addInput("data");
                        LogManager.getLogger("MethodEditorTabController").info(" > Write Connector: ");
                        variableConnectors.put(getVariableId(n,v),input);
                    }
                }
                if(!(((Invocation) i).isVoid())){
                    Connector output=n.addOutput("data");
                    Variable v = scope.getVariable(((Invocation) i).getReturnValueName());
                    LogManager.getLogger("MethodEditorTabController").info(" > Write Connector: ");
                    variableConnectors.put(getVariableId(n, v),output);
                }
                n.setWidth(400.0);
                n.setHeight(100.0);
                LogManager.getLogger("MethodEditorTabController").info("Node: %s".formatted(((Invocation) i).getCode()));
                prevNode = n;
            }
        }
        dataFlowToFlow(scope,resultFlow);
        return resultFlow;
    }
    private VFlow scopeToFlowFirst(Scope scope,VFlow parent){
        //VFlow resultFlow=parent.newSubFlow();
        VNode prevNode=null;
        Boolean isClassOrScript=scope.getType()== ScopeType.CLASS||scope.getType()==ScopeType.COMPILATION_UNIT
                || scope.getType()==ScopeType.NONE;
        for(IInvokeAndStatement i:scope.getControlFlow().getCallObjects()){
            if(i instanceof Invocation){
                VNode n=null;
                if(((Invocation) i).isScope() &&!isClassOrScript ){
                    ScopeInvocation si=(ScopeInvocation) i;
                    n=scopeToFlow(si.getScope(),parent).getModel();
                }else{
                    n=parent.newNode();
                    n.setTitle("%s.%s():%s".formatted(((Invocation)i).getVariableName(),((Invocation)i).getMethodName(),
                            ((Invocation)i).getId()));
                    invocationNodes.put((Invocation)i,n);
                }
                n.setMainInput(n.addInput("control"));
                n.setMainOutput(n.addOutput("control"));
                if (prevNode != null) {
                    parent.connect(prevNode, n, "control");
                }else{
                    parent.connect(rootNode,n,"control");
                }
                for(Variable v:((Invocation) i).getArguments()){
                    if(v!=null){
                        Connector input=n.addInput("data");
                        LogManager.getLogger("MethodEditorTabController").info(" > Write Connector: ");
                        variableConnectors.put(getVariableId(n,v),input);
                    }
                }
                if(!(((Invocation) i).isVoid())){
                    Connector output=n.addOutput("data");
                    Variable v = scope.getVariable(((Invocation) i).getReturnValueName());
                    LogManager.getLogger("MethodEditorTabController").info(" > Write Connector: ");
                    variableConnectors.put(getVariableId(n, v),output);
                }
                n.setWidth(400.0);
                n.setHeight(100.0);
                LogManager.getLogger().info("Node: %s".formatted(((Invocation) i).getCode()));
                prevNode = n;
            }
        }
        dataFlowToFlow(scope,parent);
        return parent;
    }
    private void dataFlowToFlow(Scope scope,VFlow parent){
        DataFlow dataFlow=scope.getDataFlow();
        dataFlow.create(scope.getControlFlow());
        for(IInvokeAndStatement i :scope.getControlFlow().getCallObjects()){
            if(i instanceof Invocation){
                List<DataRelation> relations=dataFlow.getRelationsForReceiver((Invocation) i);
                LogManager.getLogger("MethodEditorTabController").info("relations: %s".formatted(relations.size()));
                for(DataRelation dataRelation:relations){
                    VNode sender=invocationNodes.get(dataRelation.getSender());
                    VNode receiver=invocationNodes.get(dataRelation.getReceiver());
                    LogManager.getLogger("MethodEditorTabController").info(
                            "Sender: %s, receiver: %s".formatted(sender.getId(),receiver.getId())
                    );
                    String retValName=dataRelation.getReceiver().getReturnValueName();
                    LogManager.getLogger("MethodEditorTabController").info(
                            " --> sender: %s".formatted(retValName)
                    );
                    //Connector senderConnector=getVariableById

                }
            }
        }
    }
    private static String getVariableId(VNode n,Variable v){
        String id="%s:%s".formatted(n.getId(),v);
        LogManager.getLogger("id: %s".formatted(id));
        return id;
    }
    private static String getVariableId(VNode n,String v){
        String id="%s,%s".formatted(n.getId(),v);
        LogManager.getLogger("id: %s".formatted(id));
        return id;
    }
    private Connector getVariableById(VNode n,String vName){
        return variableConnectors.get(getVariableId(n,vName));
    }
}
