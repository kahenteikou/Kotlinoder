package io.github.kahenteikou.kotlinoder.codevisualization.main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Launcher : Application(){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println("Main!!")
            launch(*args)
        }
    }
    private var controller:MainWindowController?=null

    override fun start(primaryStage: Stage?) {
        var fxmlLoader=FXMLLoader(javaClass.getResource("MainWindow.fxml"))
        try{
            fxmlLoader.load()
        }catch (e:Exception){
            e.printStackTrace()
        }
        controller=fxmlLoader.getController()
        var scene:Scene=Scene(fxmlLoader.getRoot() as Parent, 1200.0, 800.0)
        
    }
}