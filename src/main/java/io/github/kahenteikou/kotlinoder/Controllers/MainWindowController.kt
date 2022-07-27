package io.github.kahenteikou.kotlinoder.Controllers

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane
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
        println("init")
        var canvas:ScalableContentPane=ScalableContentPane()
        canvas.maxScaleX=1.0
        canvas.maxScaleY=1.0
        view.children.add(canvas)
        var root : Pane = Pane()
        canvas.content=root
        rootPane=root
        flow=FlowFactory.newFlow()

    }
    fun File_Open_OnAction(event : ActionEvent){

    }
    fun File_Quit_OnAction(event: ActionEvent){
        Platform.exit()
    }
}