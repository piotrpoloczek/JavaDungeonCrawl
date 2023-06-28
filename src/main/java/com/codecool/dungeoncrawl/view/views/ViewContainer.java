package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.view.DisplayTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewContainer {


    private static ViewContainer instance;
    private static int SCENE_WIDTH = 800;
    private static int SCENE_HEIGHT = 1200;

    @Getter @Setter
    private Scene scene;
    @Getter @Setter
    private Pane container;
    @Getter @Setter
    private Game game;
    @Getter @Setter
    private GameView gameView;
    @Getter @Setter
    private InventoryView inventoryView;
    @Getter @Setter
    private ClassView classView;
    private DisplayTask displayTask;
    @Getter @Setter
    private GameDatabaseManager dbManager;

    public static ViewContainer getInstance() {
        if (instance == null) {
            instance = new ViewContainer(new Game());
        }
        return instance;
    }


    public ViewContainer(Game game) {
        this.game = game;
        this.gameView = new GameView(game);
        this.inventoryView = new InventoryView(game);
        this.classView = new ClassView(game);

        this.container = new GridPane();
        container.setPrefSize(SCENE_HEIGHT, SCENE_WIDTH);
        this.scene = new Scene(container);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this::onKeyPressed);

        displayTask = new DisplayTask(container, gameView);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(displayTask);
        displayTask.pauseTask();

        showClassView();
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
                showInventoryView();
                break;
            case Q:
                showGameView();
                break;
            case S:
                this.displayTask.pauseTask();
                this.showSaveGameWindow();
                break;
            case RIGHT:
                getGame().gameTurn(Direction.RIGHT);
                break;
        }
    }

    public void showGameView() {
        this.displayTask.resumeTask();
        this.getContainer().getChildren().clear();
        this.getContainer().getChildren().add(
                this.getGameView().getGamePane()
        );
    }

    public void showInventoryView() {
        this.displayTask.pauseTask();
        this.getContainer().getChildren().clear();
        this.getInventoryView().refresh();
        this.getContainer().getChildren().add(
                this.getInventoryView().getMainPane()
        );
    }

    public void showClassView() {
        this.getContainer().getChildren().clear();
        this.getContainer().getChildren().add(
                this.getClassView().getMainPane()
        );
    }

    public void showSaveGameWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Save");
        alert.setHeaderText("Do you want to save your game state?");

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(this::handleSaveStateButton);

        this.displayTask.resumeTask();
    }

    //Sorry for now i don't know where to put this method
    private void handleSaveStateButton(ButtonType buttonType) {
        GameState gameState;
        PlayerModel playerModel = new PlayerModel(game.getPlayer());

        if (buttonType == ButtonType.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String currentMap = objectMapper.writeValueAsString(game.getCurrentMap());
                gameState = new GameState(currentMap, new Date(System.currentTimeMillis()), playerModel);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            game.getDbManager().savePlayer(playerModel);
            game.getDbManager().saveGameState(gameState);
        }
    }
}
