package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Direction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {

    GameView gameView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // prepare the game
        gameView = new GameView();
        Scene scene = new Scene(gameView.getBorderPane());
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
            case RIGHT:
                gameView.getGame().gameTurn(Direction.RIGHT);
                break;
        }
        gameView.refreshView();
    }
}
