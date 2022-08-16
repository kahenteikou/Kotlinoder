package io.github.kahenteikou.kotlinoder.demo

import javafx.application.Application
import javafx.stage.Stage

class Launcher :Application(){
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            Application.launch(Launcher::class.java, *args)
        }
    }

    override fun start(primaryStage: Stage?) {
        println("Hello, world!")
    }
}