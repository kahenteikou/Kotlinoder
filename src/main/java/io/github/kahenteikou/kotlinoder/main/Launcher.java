package io.github.kahenteikou.kotlinoder.main;

import io.github.kahenteikou.kotlinoder.App.MainApp;
import io.github.kahenteikou.kotlinoder.Kotlintestkun;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Kotlintestkun kotlintestkun = new Kotlintestkun();
        System.out.println(kotlintestkun.addkun(1,2));
        Application.launch(MainApp.class, args);
    }
}
