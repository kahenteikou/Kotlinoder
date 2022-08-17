package io.github.kahenteikou.kotlinoder.demo

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory
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
        flow.setSkinFactories(FXSkinFactory(rootPane))

        flow.model.width=550.0
        flow.model.height=800.0
        flow.model.isVisible=true

        flow.model.title="Test1"
        var nodekun=flow.newNode()
        nodekun.height=300.0
        nodekun.width=300.0
        nodekun.title="X"
        nodekun.x=0.0
        nodekun.y=0.0
    }
}