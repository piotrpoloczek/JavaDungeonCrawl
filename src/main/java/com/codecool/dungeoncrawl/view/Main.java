package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.view.views.ViewContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;
    private ViewContainer viewContainer;
    private GameDatabaseManager dbManager;



    @Override
    public void start(Stage stage) throws Exception {

        this.prepareDatabase();

        this.primaryStage = stage;
        this.viewContainer = ViewContainer.getInstance();

        // Check if this is the best way to handle it?
        // but I think that this is the best way to separate concerns and responsibilities
        this.viewContainer.setDbManager(this.dbManager);

        this.scene = viewContainer.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareDatabase() {
        this.dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException e) {
            System.out.println("SQLException");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
