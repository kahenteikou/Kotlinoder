package io.github.kahenteikou.kotlinoder.demo

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.VFlow
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory
import eu.mihosoft.vrl.workflow.fx.FXValueSkinFactory
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
        canvas.minScaleX=1.0
        canvas.minScaleY=1.0
        view.children.add(canvas)
        var root : Pane = Pane()
        canvas.content=root
        rootPane=root
        flow= FlowFactory.newFlow()
        var fXSkinFactory=FXValueSkinFactory(rootPane)
        //fXSkinFactory.addSkinClassForConnectionType()
        flow.setSkinFactories(fXSkinFactory)

        flow.model.width=1080.0
        flow.model.height=1920.0
        flow.model.x=0.0
        flow.model.y=0.0
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