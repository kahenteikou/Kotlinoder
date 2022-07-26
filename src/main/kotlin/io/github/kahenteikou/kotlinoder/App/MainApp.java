package io.github.kahenteikou.kotlinoder.App;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("../assets/MainWindow.fxml"));
        Scene scene=new Scene(root);
        stage.setTitle("MainWindow");
        stage.setScene(scene);
        stage.show();
    }
}
