package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.view.Tiles;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


public class GameView {

    // containers
    @Getter @Setter
    private BorderPane gamePane;
    @Getter @Setter
    private Canvas canvas;
    @Getter @Setter
    private GridPane inventoryPane;
    @Getter @Setter
    private GridPane messagePane;

    // labels
    @Getter @Setter
    private Label healthLabel;
    @Getter @Setter
    private Label defenceLabel;
    @Getter @Setter
    private Label inventoryLabel;
    @Getter @Setter
    private Label messageLabel;

    // game
    private Game game;


    public GameView(Game game) {
        this.game = game;
        this.canvas = new Canvas(25 * Tiles.TILE_WIDTH, 20 * Tiles.TILE_WIDTH);

        this.healthLabel = new Label();
        this.defenceLabel = new Label();
        this.inventoryLabel = new Label();
        this.messageLabel = new Label();

        prepareGridPane();
        prepareMessagePane();
        prepareBorderPane();
    }

    private void prepareBorderPane() {
        this.gamePane = new BorderPane();
        gamePane.setCenter(canvas);
        gamePane.setRight(getInventoryPane());
        gamePane.setBottom(getMessagePane());
    }

    private void prepareGridPane() {
        inventoryPane = new GridPane();
        inventoryPane.setPrefWidth(300);
        inventoryPane.setPadding(new Insets(10));
        inventoryPane.add(healthLabel, 1, 0);
        inventoryPane.add(defenceLabel,1,1);
        inventoryPane.add(inventoryLabel, 0, 2);
        inventoryPane.add(messageLabel, 0, 3);
    }

    private void prepareMessagePane() {
        messagePane = new GridPane();
        messagePane.setPrefHeight(100);
        messagePane.setPadding(new Insets(10));
        messagePane.add(messageLabel, 1, 0);
    }



    public void refreshView() {
        getCanvas().getGraphicsContext2D().setFill(Color.BLACK);
        getCanvas().getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int levelWidth = game.getCurrentMap().getWidth();
        int levelHeight = game.getCurrentMap().getHeight();

        int playerX = game.getCurrentMap().getPlayer().getX();
        int playerY = game.getCurrentMap().getPlayer().getY();

        int viewportWidth = (int) (canvas.getWidth() / Tiles.TILE_WIDTH);
        int viewportHeight = (int) (canvas.getHeight() / Tiles.TILE_WIDTH);

        int startX = Math.max(0, playerX - viewportWidth / 2);
        int startY = Math.max(0, playerY - viewportHeight / 2);
        int endX = Math.min(levelWidth, startX + viewportWidth);
        int endY = Math.min(levelHeight, startY + viewportHeight);

        drawViewport(startX, startY, endX, endY);
        healthLabel.setText("Health: " + game.getCurrentMap().getPlayer().getHealth());
        defenceLabel.setText("Defence: " + game.getCurrentMap().getPlayer().getDefense());
        inventoryLabel.setText("Inventory: " + game.getCurrentMap().getPlayer().getInventory().toString());
        messageLabel.setText(game.getMessage().getActualMessage());
    }

    private void drawViewport(int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = game.getCurrentMap().getCell(x, y);
                if (cell.getGameObject() != null) {
                    if (cell.getGameObject() instanceof Player) {
                        Tiles.drawTile(getCanvas().getGraphicsContext2D(), cell.getGameObject(), x - startX, y - startY);
                    } else {
                        Tiles.drawTile(getCanvas().getGraphicsContext2D(), cell.getGameObject(), x - startX, y - startY);
                    }
                } else {
                    Tiles.drawTile(getCanvas().getGraphicsContext2D(), cell, x - startX, y - startY);
                }
            }
        }
    }

    public void showSaveGameWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Save");
        alert.setHeaderText("Do you want to save your game state?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            game.getDbManager().savePlayer(game.getPlayer());
            // saving to db
        } else {
            // nothing happens
        }
    }

}
