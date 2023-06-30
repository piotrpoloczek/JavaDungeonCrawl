package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.view.DisplayTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
            case L:
                this.displayTask.pauseTask();
                this.showLoadGameWindow();
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

    public void showLoadGameWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Save");
        alert.setHeaderText("Do you want to save your game state?");

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(this::handleLoadStateButton);

        this.displayTask.resumeTask();
    }

    public void showLoadGameWindow2() {
        List<String> choices = dbManager.getGameStateDao().getGameStatesInfo();

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Load Game");
        dialog.setHeaderText("Choose game from the list:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(letter -> System.out.println("Your choice: " + letter));

        this.displayTask.resumeTask();
    }

    //Sorry for now i don't know where to put this method
    private void handleSaveStateButton2(ButtonType buttonType) {
        GameState gameState;
        PlayerModel playerModel = new PlayerModel(game.getPlayer());

        if (buttonType == ButtonType.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String currentMap = objectMapper.writeValueAsString(game.getCurrentMap());


                gameState = new GameState(currentMap, new Date(System.currentTimeMillis()), playerModel);

                whenWriteStringUsingBufferedWritter_thenCorrect("test", currentMap);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            game.getDbManager().savePlayer(playerModel);
            game.getDbManager().saveGameState(gameState);
        }
    }

    private void handleSaveStateButton(ButtonType buttonType){
        if (buttonType == ButtonType.OK) {
            try {

                FileOutputStream fileOutputStream
                        = new FileOutputStream("yourfile.txt");
                ObjectOutputStream objectOutputStream
                        = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(game.getCurrentMap());
                objectOutputStream.flush();
                objectOutputStream.close();


                game.getDbManager().saveGameMap(game.getCurrentMap());

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void handleLoadStateButton(ButtonType buttonType) {
        if (buttonType == ButtonType.OK) {
            try {


//                FileInputStream fileInputStream
//                        = new FileInputStream("yourfile.txt");
//                ObjectInputStream objectInputStream
//                        = new ObjectInputStream(fileInputStream);
//                GameMap gameMap = (GameMap) objectInputStream.readObject();
//                objectInputStream.close();
//
//                game.setCurrentMap(gameMap);

                GameMap loadedMap = game.getDbManager().getGameMap(1);
                game.setCurrentMap(loadedMap);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void whenWriteStringUsingBufferedWritter_thenCorrect(String fileName, String data)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);

        writer.close();
    }
}
