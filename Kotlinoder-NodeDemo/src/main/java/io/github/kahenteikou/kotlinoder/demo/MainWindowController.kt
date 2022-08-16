package io.github.kahenteikou.kotlinoder.demo

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.fx.ScalableContentPane
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.Pane
import java.net.URL
import java.util.*

class MainWindowController: Initializable {
    @FXML
    lateinit var view : Pane
    lateinit private var rootPane: Pane
    lateinit private var flow: VFlow
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("init")
        var canvas: ScalableContentPane = ScalableContentPane()
        canvas.maxScaleX=1.0
        canvas.maxScaleY=1.0
        view.children.add(canvas)
        var root : Pane = Pane()
        canvas.content=root
        rootPane=root
        flow= FlowFactory.newFlow()
    }
}