package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.view.views.ViewContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;
    private ViewContainer viewContainer;



    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.viewContainer = ViewContainer.getInstance();
        this.scene = viewContainer.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
