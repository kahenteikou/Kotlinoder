package io.github.kahenteikou.kotlinoder.App

import io.github.kahenteikou.kotlinoder.Controllers.MainWindowController
import io.github.kahenteikou.kotlinoder.assets.AssetStub
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.stage.Stage

class MainApp : Application() {
    lateinit private var controller : MainWindowController
    @Throws(Exception::class)
    override fun start(stage:Stage) {
        var l2:AssetStub = AssetStub()
        var loader: FXMLLoader = FXMLLoader(l2.javaClass.getResource("MainWindow.fxml"))
        

    }
}