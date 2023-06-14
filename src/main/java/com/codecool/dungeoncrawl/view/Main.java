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
    @Getter @Setter
    private Game game;
    private Thread displayThread;


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.game = new Game();
        this.viewContainer = new ViewContainer(game);
        this.scene = viewContainer.getScene();

        primaryStage.setScene(scene);

        showGameView();
        this.displayThread = createDisplayThread();
        this.displayThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Thread createDisplayThread() {
        Thread displayThread = new Thread(() -> {
            while (true) {
                System.out.println("it we cow");
                Platform.runLater(() -> {
                    viewContainer.getGameView().refreshView();
                    viewContainer.getContainer().getChildren().setAll(viewContainer.getGameView().getUi());
                });

                // Delay between turns (optional)
                try {
                    Thread.sleep(100); // Adjust the delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return displayThread;
    }


    public void showGameView() {
        viewContainer.getContainer().getChildren().add(viewContainer.getGameView().getUi());
        primaryStage.show();
    }


}
