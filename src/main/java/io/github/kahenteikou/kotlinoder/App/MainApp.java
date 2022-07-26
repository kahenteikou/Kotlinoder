package io.github.kahenteikou.kotlinoder.App;

import eu.mihosoft.vrl.workflow.fx.VCanvas;
import io.github.kahenteikou.kotlinoder.Controllers.MainWindowController;
import io.github.kahenteikou.kotlinoder.assets.AssetStub;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private MainWindowController controller;
    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader= new FXMLLoader(AssetStub.class.getResource("MainWindow.fxml"));
        StackPane canvas = new StackPane();

        Scene scene = new Scene(canvas, 800, 600);
        try{
            loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        controller=loader.getController();
        canvas.getChildren().add((Node) loader.getRoot());
        stage.setTitle("MainWindow");
        stage.setScene(scene);
        stage.show();
    }
}
