package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Direction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class AppView extends Application {

    private static int SCENE_WIDTH = 400;
    private static int SCENE_HEIGHT = 400;

    private Stage primaryStage;
    private GameView gameView;
    private InventoryMenuView inventoryMenuView;
    private Scene mainMenuScene;
    private Scene classMenuScene;
    private Scene gameScene;
    private Scene inventoryScene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.gameView = new GameView();
        this.mainMenuScene = createMainMenuScene();
        this.classMenuScene = createClassMenuView();
        this.gameScene = createGameScene();
        this.inventoryMenuView = new InventoryMenuView(this, gameView.getGame());
        this.inventoryScene = createInventoryScene();
        showMainMenuView();
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
        Scene scene = new Scene(inventoryMenuView, SCENE_HEIGHT, SCENE_WIDTH);
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
        gameView.refreshView();
    }
}
