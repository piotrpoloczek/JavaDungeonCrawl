package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


public class GameViewOld {

    private static int SCENE_WIDTH = 400;
    private static int SCENE_HEIGHT = 400;

    @Getter @Setter
    Game game;
    @Getter @Setter
    Canvas canvas;
    @Getter @Setter
    GraphicsContext context;
    @Getter @Setter
    Label healthLabel;
    @Getter @Setter
    Label inventoryLabel;
    @Getter @Setter
    GridPane ui;
    @Getter @Setter
    BorderPane borderPane;
    GraphicsContext tempContext;

    public GameViewOld() {
        prepareGame();
        setUi(prepareGridPane());
        setBorderPane(prepareBorderPane());

        Canvas canvas = new Canvas(25 * Tiles.TILE_WIDTH, 20 * Tiles.TILE_WIDTH);
        tempContext = canvas.getGraphicsContext2D();
    }



    private BorderPane prepareBorderPane() {
        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(getCanvas());
        borderPane.setRight(getUi());
        return borderPane;
    }

    private GridPane prepareGridPane() {
        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));

        // TODO:  this is special for you @Piotr
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        Label nameLabel = new Label("Name: ");
        ui.add(inventoryLabel, 0, 1);

        return ui;
    }

    private void prepareGame() {
        setGame(new Game());

        int levelWidth = getGame().getCurrentMap().getWidth();
        int levelHeight = getGame().getCurrentMap().getHeight();
        int tileSize = Tiles.TILE_WIDTH;

        System.out.println(levelHeight + " " + levelWidth);

        setCanvas( new Canvas(25 * tileSize, 20 * tileSize));
        setContext(canvas.getGraphicsContext2D());

        setHealthLabel(new Label());
        setInventoryLabel(new Label());
    }


    public void refreshView() {
        tempContext.setFill(Color.BLACK);
        tempContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

        ui.getChildren().add(tempContext.getCanvas());
    }

    private void drawViewport(int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = game.getCurrentMap().getCell(x, y);
                if (cell.getGameObject() != null) {
                    if (cell.getGameObject() instanceof Player) {
                        Tiles.drawPlayer(tempContext, cell.getGameObject(), x - startX, y - startY);
                    } else {
                        Tiles.drawTile(tempContext, cell.getGameObject(), x - startX, y - startY);
                    }
                } else {
                    Tiles.drawTile(tempContext, cell, x - startX, y - startY);
                }
            }
        }
    }
}
