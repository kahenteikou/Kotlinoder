package io.github.kahenteikou.kotlinoder.App

import io.github.kahenteikou.kotlinoder.Controllers.MainWindowController
import io.github.kahenteikou.kotlinoder.assets.AssetStub
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class MainApp : Application() {
    lateinit private var controller : MainWindowController
    @Throws(Exception::class)
    override fun start(stage:Stage) {
        var l2:AssetStub = AssetStub()
        var loader: FXMLLoader = FXMLLoader(l2.javaClass.getResource("MainWindow.fxml"))
        var canvas:StackPane = StackPane()
        var scene : Scene= Scene(canvas, 800.0, 600.0)
        try{
            loader.load<Any>()
        }catch (e:Exception){
            e.printStackTrace()
        }
        controller = loader.getController<MainWindowController>()
        canvas.children.add((loader.getRoot() as Node))
        stage.title = "MainWindow"
        stage.scene=scene
        stage.show()

    }
}