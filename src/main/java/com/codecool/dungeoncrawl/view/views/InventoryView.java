package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.view.Tiles;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryView {

    // containers
    @Getter @Setter
    private BorderPane mainPane;
    @Getter @Setter
    private Canvas canvas;

    // game
    @Getter @Setter
    private Game game;
    private Map<Point2D, Item> itemMap = new HashMap<>();

    public InventoryView(Game game) {
        this.game = game;
        this.canvas = new Canvas(25 * Tiles.TILE_WIDTH, 20 * Tiles.TILE_WIDTH);

        prepareMainPane();
        refresh();
    }

    private void prepareMainPane() {
        this.mainPane = new BorderPane();
        mainPane.setCenter(canvas);
    }

    public void refresh() {
        System.out.println("it works");
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawItem();

        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Check if the clicked coordinates are within the bounds of any item
            for (Map.Entry<Point2D, Item> entry : itemMap.entrySet()) {
                Point2D itemPosition = entry.getKey();
                Item item = entry.getValue();
                // Adjust the comparison logic according to your needs
                if (mouseX >= itemPosition.getX() && mouseX < itemPosition.getX() + Tiles.TILE_WIDTH &&
                        mouseY >= itemPosition.getY() && mouseY < itemPosition.getY() + Tiles.TILE_WIDTH) {
                    // Item is clicked, perform the desired action
                    System.out.println("Item clicked: " + item.getName() + item);
                    item.use(game.getCurrentMap().getPlayer());
                    getGame().getCurrentMap().getPlayer().getInventory().getSack().remove(item);
                    // ...
                    refresh();
                    break; // Exit the loop since the item has been found
                }
            }
        });
    }

    private void drawItem() {
        List<Item> items = game.getCurrentMap().getPlayer().getInventory().getSack();
        int startX = 0;
        int startY = 0; // Set the y-coordinate for drawing items

        for (Item item : items) {
            System.out.println(startX);
            System.out.println(item);
            Point2D itemPosition = new Point2D(startX * Tiles.TILE_WIDTH, startY * Tiles.TILE_WIDTH);
            Tiles.drawTile(canvas.getGraphicsContext2D(), item, startX, startY);
            itemMap.put(itemPosition, item);
            startX += 2; // Increase the x-coordinate for each item
        }
    }



}
