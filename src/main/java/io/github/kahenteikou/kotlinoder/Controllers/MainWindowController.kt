package io.github.kahenteikou.kotlinoder.Controllers

import eu.mihosoft.vrl.workflow.VFlow
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.Pane

class MainWindowController : Initializable {
    @FXML
    lateinit var view : Pane
    lateinit private var rootPane: Pane
    lateinit private var flow: VFlow
    override fun initialize(location: java.net.URL?, resources: java.util.ResourceBundle?) {

    }
    fun File_Open_OnAction(event : ActionEvent){

    }
    fun File_Quit_OnAction(event: ActionEvent){
        Platform.exit()
    }
}