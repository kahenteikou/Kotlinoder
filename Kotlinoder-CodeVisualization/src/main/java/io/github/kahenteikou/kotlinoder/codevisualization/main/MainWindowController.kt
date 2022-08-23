package io.github.kahenteikou.kotlinoder.codevisualization.main

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TreeView
import org.apache.logging.log4j.LogManager
import java.net.URL
import java.util.*

class MainWindowController : Initializable {
    @FXML
    lateinit var fileClassTreeView:TreeView<String>
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LogManager.getLogger("Launcher").info("Start!")

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
    @FXML
    fun onAboutAction(e:ActionEvent){

    }


}