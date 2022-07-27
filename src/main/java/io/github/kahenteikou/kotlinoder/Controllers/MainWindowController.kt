package io.github.kahenteikou.kotlinoder.Controllers

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.Pane

class MainWindowController : Initializable {
    @FXML
    lateinit var view : Pane
    override fun initialize(location: java.net.URL?, resources: java.util.ResourceBundle?) {

    }
    fun File_Open_OnAction(event : ActionEvent){

    }
    fun File_Quit_OnAction(event: ActionEvent){
        Platform.exit()
    }
}