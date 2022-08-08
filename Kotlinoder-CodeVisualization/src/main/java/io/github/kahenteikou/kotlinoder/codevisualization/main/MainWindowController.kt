package io.github.kahenteikou.kotlinoder.codevisualization.main

import eu.mihosoft.vrl.workflow.Connector
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.VNode
import io.github.kahenteikou.kotlinoder.instrumentation.CodeEntity
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class MainWindowController : Initializable {
    @FXML
    lateinit var editor: TextArea
    @FXML
    lateinit var view:Pane
    private var rootPane:Pane
    private var flow:VFlow
    private var invocationNodes:MutableMap<CodeEntity, VNode> = HashMap()
    private var variableConnectors:MutableMap<String,Connector> = HashMap()
    
    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }
    @FXML
    fun onKeyTyped(event: KeyEvent){

    }
    @FXML
    fun onLoadAction(e:ActionEvent){

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

}