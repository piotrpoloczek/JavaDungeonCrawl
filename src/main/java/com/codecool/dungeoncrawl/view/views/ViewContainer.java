package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

public class ViewContainer {

    private static int SCENE_WIDTH = 400;
    private static int SCENE_HEIGHT = 400;

    @Getter @Setter
    private Scene scene;
    @Getter @Setter
    private GridPane container;
    @Getter @Setter
    private Game game;
    @Getter @Setter
    private GameView gameView;


    public ViewContainer(Game game) {
        this.game = game;
        this.gameView = new GameView(game);

        this.container = new GridPane();
        container.setPrefSize(SCENE_HEIGHT, SCENE_WIDTH);
        this.scene = new Scene(container);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this::onKeyPressed);
    }




    private void onKeyPressed(KeyEvent keyEvent){

        switch (keyEvent.getCode()) {
            case UP:
                getGame().gameTurn(Direction.UP);
                break;
            case DOWN:
                getGame().gameTurn(Direction.DOWN);
                break;
            case LEFT:
                getGame().gameTurn(Direction.LEFT);
                break;
            case I:
                //showInventoryView();
                break;
            case Q:
                //showGameView();
                break;
            case RIGHT:
                getGame().gameTurn(Direction.RIGHT);
                break;
        }
//        gameView.refreshView();
    }


}
