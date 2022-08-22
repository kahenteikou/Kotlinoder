package io.github.kahenteikou.kotlinoder.codevisualization.main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Launcher : Application(){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            var loggerkun:Logger=LogManager.getLogger("Launcher")

            loggerkun.warn("Main!!")
            launch(Launcher::class.java,*args)
        }
    }
    private var controller:OLD_MainWindowController?=null

    override fun start(primaryStage: Stage?) {
        var fxmlLoader=FXMLLoader(javaClass.getResource("MainWindow.fxml"))
        try{
            fxmlLoader.load()
        }catch (e:Exception){
            e.printStackTrace()
        }
        controller=fxmlLoader.getController()
        var scene:Scene=Scene(fxmlLoader.getRoot() as Parent, 1200.0, 800.0)
        primaryStage?.title="Code Visualization"
        primaryStage?.scene=scene
        primaryStage?.show()
        scene.stylesheets.add("/io/github/kahenteikou/kotlinoder/codevisualization/main/default.css")

    }
}