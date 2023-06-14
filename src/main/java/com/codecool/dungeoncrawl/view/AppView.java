package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Direction;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


public class AppView extends Application {

    private static int SCENE_WIDTH = 400;
    private static int SCENE_HEIGHT = 400;

    private Stage primaryStage;
    private Scene scene;


    private GameViewOld gameView;
    private InventoryMenuView inventoryMenuView;
    private Scene mainMenuScene;
    private Scene classMenuScene;
    private Scene gameScene;
    private Scene inventoryScene;
    @Getter
    @Setter
    private Thread displayThread;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.scene = new Scene(new GridPane());




        this.gameView = new GameViewOld();
        this.mainMenuScene = createMainMenuScene();
        this.classMenuScene = createClassMenuView();
        this.gameScene = createGameScene();
        this.inventoryMenuView = new InventoryMenuView(this, gameView.getGame());
        this.inventoryScene = createInventoryScene();
        showMainMenuView();
        this.displayThread = createDisplayThread();

        Platform.runLater(() -> displayThread.start());

    }

    private Thread createDisplayThread() {
        Thread displayThread = new Thread(() -> {
            while (true) {
                System.out.println("it we cow");
                    gameView.refreshView();

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



    private Scene createMainMenuScene() {
        MainMenuView mainMenuView = new MainMenuView(this);
        Scene scene = new Scene(mainMenuView, SCENE_HEIGHT, SCENE_WIDTH);
        scene.getStylesheets().add("style.css");
        return scene;
    }

    private Scene createClassMenuView() {
        ClassMenuView classMenuView = new ClassMenuView(this, gameView.getGame());
        Scene scene = new Scene(classMenuView, SCENE_HEIGHT, SCENE_WIDTH);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this::onKeyPressed);
        return scene;
    }

    private Scene createGameScene() {
        Scene scene = new Scene(gameView.getBorderPane());
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this::onKeyPressed);
        return scene;
    }

    private Scene createInventoryScene() {
        Scene scene = new Scene(inventoryMenuView.getBorderPane());
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this::onKeyPressed);
        return scene;
    }

    public void showMainMenuView() {
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    public void showInventoryView() {
        inventoryMenuView.refresh();
        primaryStage.setScene(inventoryScene);
        primaryStage.show();
    }

    public void showClassMenuView() {
        primaryStage.setScene(classMenuScene);
        primaryStage.show();
    }


    public void showGameView() {
        primaryStage.setScene(gameScene);
        gameView.refreshView();
        primaryStage.show();

//        displayThread.start();
    }


    private void onKeyPressed(KeyEvent keyEvent){

        switch (keyEvent.getCode()) {
            case UP:
                gameView.getGame().gameTurn(Direction.UP);
                break;
            case DOWN:
                gameView.getGame().gameTurn(Direction.DOWN);
                break;
            case LEFT:
                gameView.getGame().gameTurn(Direction.LEFT);
                break;
            case I:
                this.showInventoryView();
                break;
            case Q:
                this.showGameView();
                break;
            case RIGHT:
                gameView.getGame().gameTurn(Direction.RIGHT);
                break;
        }
//        gameView.refreshView();
    }
}
