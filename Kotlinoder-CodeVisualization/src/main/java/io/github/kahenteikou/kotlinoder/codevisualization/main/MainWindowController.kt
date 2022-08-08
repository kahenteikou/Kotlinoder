package io.github.kahenteikou.kotlinoder.codevisualization.main

import eu.mihosoft.vrl.workflow.Connector
import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.VNode
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane
import io.github.kahenteikou.kotlinoder.instrumentation.CodeEntity
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
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
            Logger.getLogger(MainWindowController::class.java.name).severe(ex.toString())
        }
    }
    private fun updateView(){
        if(rootPane==null){
            error("UI NOT READY!")
            return
        }

    }

}