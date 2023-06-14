package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.view.Tiles;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


public class GameView {

    private Canvas canvas;
    private GraphicsContext context;
    @Getter @Setter
    private GridPane ui;
    @Getter @Setter
    private GridPane messagePane;
    @Getter @Setter
    private Label healthLabel;
    @Getter @Setter
    private Label inventoryLabel;
    @Getter @Setter
    private Label messageLabel;
    private Game game;
    @Getter @Setter
    private BorderPane borderPane;



    public GameView(Game game) {
        this.game = game;
        this.canvas = new Canvas(25 * Tiles.TILE_WIDTH, 20 * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();

        this.healthLabel = new Label();
        this.inventoryLabel = new Label();
        this.messageLabel = new Label();

        prepareGridPane();
        prepareMessagePane();
        prepareBorderPane();
    }

    private void prepareBorderPane() {
        this.borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(getUi());
        borderPane.setBottom(getMessagePane());
    }

    private void prepareGridPane() {
        ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));
        ui.add(healthLabel, 1, 0);
        ui.add(inventoryLabel, 0, 1);
        ui.add(messageLabel, 0, 2);
    }

    private void prepareMessagePane() {
        messagePane = new GridPane();
        messagePane.setPrefHeight(100);
        ui.setPadding(new Insets(10));
        messagePane.add(messageLabel, 1, 0);
    }



    public void refreshView() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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
        inventoryLabel.setText("Inventory: " + game.getCurrentMap().getPlayer().getInventory().toString());
        messageLabel.setText(game.getMessage().getActualMessage());
    }

    private void drawViewport(int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = game.getCurrentMap().getCell(x, y);
                if (cell.getGameObject() != null) {
                    if (cell.getGameObject() instanceof Player) {
                        Tiles.drawPlayer(context, cell.getGameObject(), x - startX, y - startY);
                    } else {
                        Tiles.drawTile(context, cell.getGameObject(), x - startX, y - startY);
                    }
                } else {
                    Tiles.drawTile(context, cell, x - startX, y - startY);
                }
            }
        }
    }

}
