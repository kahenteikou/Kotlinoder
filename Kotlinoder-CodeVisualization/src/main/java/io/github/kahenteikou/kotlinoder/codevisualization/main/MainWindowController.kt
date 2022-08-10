package io.github.kahenteikou.kotlinoder.codevisualization.main

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiManager
import com.intellij.testFramework.LightVirtualFile
import eu.mihosoft.vrl.workflow.Connector
import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.VNode
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane
import io.github.kahenteikou.kotlinoder.instrumentation.*
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import org.apache.logging.log4j.LogManager
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.logging.Logger
import kotlin.collections.HashMap

class MainWindowController : Initializable {
    private var currentDocument:File?=null
    @FXML
    lateinit var editor: TextArea
    @FXML
    lateinit var view:Pane
    lateinit private var rootPane:Pane
    lateinit private var flow:VFlow
    private var invocationNodes:MutableMap<CodeEntity, VNode> = HashMap()
    private var variableConnectors:MutableMap<String,Connector> = HashMap()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Init...")
        var canvas=ScalableContentPane()
        canvas.style="-fx-background-color: rgb(0,0,0)"
        canvas.maxScaleX=1.0
        canvas.maxScaleY=1.0
        view.children.add(canvas)
        var root:Pane=Pane()
        canvas.content=root
        root.style="-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));"
        rootPane=root
        flow=FlowFactory.newFlow()
    }
    @FXML
    fun onKeyTyped(event: KeyEvent){

    }
    @FXML
    fun onLoadAction(e:ActionEvent){
        loadTextFile(null)
    }
    @FXML
    fun onSaveAction(e:ActionEvent){

    }
    @FXML
    fun onSaveAsAction(e:ActionEvent){

    }
    @FXML
    fun onCloseAction(e:ActionEvent){

    }
    private fun loadTextFile(f: File?){
        try{
            if(f==null){
                var mdFilt:FileChooser.ExtensionFilter = FileChooser.ExtensionFilter("Kotlin files (*.kt)", "*.kt")
                var allFsFilt=FileChooser.ExtensionFilter("All files (*.*)", "*.*")
                var chooser=FileChooser()
                chooser.title="Open kotlin file"
                chooser.extensionFilters.add(mdFilt)
                chooser.extensionFilters.add(allFsFilt)
                currentDocument=chooser.showOpenDialog(this.rootPane.scene.window).absoluteFile
            }else{
                currentDocument=f
            }
            editor.text=String(Files.readAllBytes(Paths.get(currentDocument!!.absolutePath)),Charsets.UTF_8)
            updateView()
        }catch (ex:IOException){
            LogManager.getLogger("Launcher").error(ex.toString())
        }
    }
    private fun updateView(){
        if(rootPane==null){
            LogManager.getLogger("Launcher").error("UI NOT READY!")
            return
        }

        val config= CompilerConfiguration()
        var dispos= Disposer.newDisposable()
        val env= KotlinCoreEnvironment.createForProduction(
            dispos,
            config,
            EnvironmentConfigFiles.JVM_CONFIG_FILES
        )
        var ksfactory = KtPsiFactory(env.project)

        //var psif= ksfactory.createFile(renderer.render(scope))
        var buffile= LightVirtualFile(currentDocument!!.name, KotlinFileType.INSTANCE,editor.getText())
        var psif= PsiManager.getInstance(env.project).findFile(buffile) as KtFile
        var parserkun = KotlinCodeVisitor(psif, VisualCodeBuilder_Impl())
        parserkun.parse(psif)
        flow.clear()
        flow.setSkinFactories()
        flow.model.isVisible=true
        if(parserkun.getrootScope()==null){
            LogManager.getLogger("Launcher").error("No Scope!")
            return
        }
        println(parserkun.getrootScope()!!.toString())
        var renderer: CompilationUnitRenderer = CompilationUnitRenderer(
            ClassDeclarationRenderer(
                MethodDeclarationRenderer(
                    InvocationCodeRenderer()
                )
            )
        )
        scopeToFlow(parserkun.getrootScope()!!,flow)
        var fxFact:FXSkinFactory= FXSkinFactory(rootPane)
        flow.setSkinFactories(fxFact)


    }
    fun dataFlowToFlow(scope:Scope,parent: VFlow){
        var dataFlow:DataFlow=scope.getDataFlow()
        dataFlow.create(scope.getControlFlow())
        for(i:Invocation in scope.getControlFlow().getInvocations()){
            var relations:MutableList<DataRelation> = dataFlow.getRelationsForReceiver(i)
            println("relations: ${relations.size}")
            for(dataRelation:DataRelation in relations){
                var sender:VNode= invocationNodes[dataRelation.getSender()]!!
                var receiver:VNode= invocationNodes[dataRelation.getReceiver()]!!
                println("SENDER: ${sender.id}, receiver: ${receiver.id}")
                var retValName:String=dataRelation.getSender().getReturnValueName()
                
            }
        }
    }
    fun scopeToFlow(scope:Scope,parent:VFlow):VFlow{
        var isClassOrScript:Boolean=
            scope.getType()==ScopeType.CLASS||scope.getType()==ScopeType.COMPILATION_UNIT
                    || scope.getType()==ScopeType.NONE
        var resultflow:VFlow=parent.newSubFlow()
        invocationNodes[scope] = resultflow.model
        var title="${scope.getType()} ${scope.getName()}(): ${scope.getId()}"
        if(isClassOrScript){
            resultflow.model.width=550.0
            resultflow.model.height=800.0
            resultflow.isVisible = true
        }else{
            resultflow.model.width=400.0
            resultflow.model.height=300.0
        }
        resultflow.model.title=title
        println("Title: $title, ${scope.getType()}")
        var prevNode:VNode?=null
        for(i:Invocation in scope.getControlFlow().getInvocations()){
            lateinit var n:VNode
            if(i.isScope()&&!isClassOrScript){
                val sI:ScopeInvocation=i as ScopeInvocation
                n=scopeToFlow(sI.getScope(),resultflow).model
            }else{
                n=resultflow.newNode()
                var newTitle="${i.getVariableName()!!}.${i.getMethodName()}():${i.getId()}"
                n.title=newTitle
                invocationNodes[i] = n
            }
            n.setMainInput(n.addInput("control"))
            n.setMainOutput(n.addOutput("control"))
            if(prevNode!=null){
                resultflow.connect(prevNode,n,"control")
            }
            for(v:Variable? in i.getArguments()){
                if(v!=null){
                    var input:Connector=n.addInput("data")
                    println(" > Write Connector: ")
                    variableConnectors[getVariableId(n,v)]=input
                }
            }
            if(!i.isVoid()){
                var output:Connector=n.addOutput("data")
                var v:Variable=scope.getVariable(i.getReturnValueName())!!
                println(" > Write Connector: ")
                variableConnectors[getVariableId(n,v)]=output
            }
            n.width=400.0
            n.height=100.0
            println("Node: ${i.getCode()?.toString()}")
            prevNode=n
        }
        if(isClassOrScript){
            for(s:Scope in scope.getScopes()){
                scopeToFlow(s,resultflow)
            }
        }
        dataFlowToFlow(scope,resultflow)
        return resultflow
    }
    fun getVariableById(n:VNode,vName:String):Connector?{
        return variableConnectors.get(getVariableId(n,vName))
    }
    companion object{
        @JvmStatic
        fun getVariableId(n:VNode,v:Variable):String{
            var id:String="${n.id}:${v.getName()!!}"
            println("id: $id")
            return id
        }
        @JvmStatic
        fun getVariableId(n:VNode,v:String):String{
            var id:String="${n.id}:${v}"
            println("id: $id")
            return id
        }
    }

}