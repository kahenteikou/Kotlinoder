package io.github.kahenteikou.kotlinoder.demo

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class Launcher :Application(){
    lateinit private var controller : MainWindowController
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            Application.launch(Launcher::class.java, *args)
        }
    }

    override fun start(stage: Stage) {
        println("Hello, world!")
        var loader = FXMLLoader(this.javaClass.getResource("MainWindow.fxml"))
        var canvas = StackPane()
        var scene = Scene(canvas, 800.0, 600.0)
        try{
            loader.load<Any>()
        }catch (e:Exception){
            e.printStackTrace()
        }
        controller = loader.getController()
        canvas.children.add((loader.getRoot() as Node))
        stage.title = "MainWindow"
        stage.scene=scene
        stage.show()
    }
}