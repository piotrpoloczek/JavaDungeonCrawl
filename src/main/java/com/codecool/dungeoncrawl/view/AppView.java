package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Direction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class AppView extends Application {

    private static int SCENE_WIDTH = 600;
    private static int SCENE_HEIGHT = 600;

    private Stage primaryStage;
    private GameView gameView;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.gameView = new GameView();
        showMainMenuView();
    }

    public void showMainMenuView() {
        MainMenuView mainMenuView = new MainMenuView(this);
        Scene scene = new Scene(mainMenuView, SCENE_HEIGHT, SCENE_WIDTH);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showClassMenuView() {
        ClassMenuView classMenuView = new ClassMenuView(this);
        Scene scene = new Scene(classMenuView, SCENE_HEIGHT, SCENE_WIDTH);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showGameView() {
        Scene scene = new Scene(gameView.getBorderPane());
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        gameView.refreshView();

        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
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
                // gameView.getGame().pauseGame()
                // showInventoryMenu()
                break;
            case RIGHT:
                gameView.getGame().gameTurn(Direction.RIGHT);
                break;
        }
        gameView.refreshView();
    }
}
