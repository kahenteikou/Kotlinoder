package io.github.kahenteikou.kotlinoder.codevisualization.main

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import java.net.URL
import java.util.*

class MainWindowController : Initializable {
    @FXML
    lateinit var editor: TextArea
    @FXML
    lateinit var view:Pane
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