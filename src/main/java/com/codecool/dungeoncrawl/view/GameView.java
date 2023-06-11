package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Game;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


public class GameView {

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

    public GameView() {
        prepareGame();
        setUi(prepareGridPane());
        setBorderPane(prepareBorderPane());
    }

    private BorderPane prepareBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(getCanvas());
        borderPane.setRight(getUi());
        return borderPane;
    }

    private GridPane prepareGridPane() {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        // TODO:  this is special for you @Piotr
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        Label nameLabel = new Label("Name: ");
        ui.add(inventoryLabel, 0, 1);

//        Label ageLabel = new Label("Age: ");
//        ui.add(ageLabel, 0, 2);
//
//        Label genderLabel = new Label("Gender: ");
//        ui.add(genderLabel, 0, 3);
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
    }

    private void drawViewport(int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = game.getCurrentMap().getCell(x, y);
                if (cell.getGameObject() != null) {
                    Tiles.drawTile(context, cell.getGameObject(), x - startX, y - startY);
                } else {
                    Tiles.drawTile(context, cell, x - startX, y - startY);
                }
            }
        }
    }

// original method for the view
//    private void refresh() {
//        get.setFill(Color.BLACK);
//        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        for (int x = 0; x < game.getCurrentMap().getWidth(); x++) {
//            for (int y = 0; y < game.getCurrentMap().getHeight(); y++) {
//                Cell cell = game.getCurrentMap().getCell(x, y);
//                if (cell.getGameObject() != null) {
//                    Tiles.drawTile(context, cell.getGameObject(), x, y);
//                } else {
//                    Tiles.drawTile(context, cell, x, y);
//                }
//            }
//        }
//        healthLabel.setText("" + game.getCurrentMap().getPlayer().getHealth());
//        inventoryLabel.setText(getGame().getCurrentMap().getPlayer().getName());
//    }


}
