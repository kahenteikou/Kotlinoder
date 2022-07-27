package io.github.kahenteikou.kotlinoder.main

import io.github.kahenteikou.kotlinoder.App.MainApp
import javafx.application.Application

class Launcher {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(MainApp::class.java, *args)
        }
    }
}