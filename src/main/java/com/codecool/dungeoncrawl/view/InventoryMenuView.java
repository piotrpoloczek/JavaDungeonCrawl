package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryMenuView {


    private static int TILE_WIDTH = 32;
    private AppView appView;
    @Getter @Setter
    private Game game;
    @Getter @Setter
    private Label inventoryLabel;
    @Getter @Setter
    Canvas canvas;

    @Getter @Setter
    GraphicsContext context;
    @Getter @Setter
    Label healthLabel;
    @Getter @Setter
    GridPane ui;
    @Getter @Setter
    BorderPane borderPane;

    private Map<Point2D, Item> itemMap = new HashMap<>();


    public InventoryMenuView(AppView appView, Game game) {
        this.appView = appView;
        this.game = game;
        //initializeUI();

        prepareGame();
        setUi(prepareGridPane());
        setBorderPane(prepareBorderPane());
    }

    private void prepareGame() {

        int levelWidth = game.getCurrentMap().getWidth();
        int levelHeight = game.getCurrentMap().getHeight();
        int tileSize = Tiles.TILE_WIDTH;

        System.out.println(levelHeight + " " + levelWidth);

        setCanvas( new Canvas(25 * tileSize, 20 * tileSize));
        setContext(canvas.getGraphicsContext2D());

        setHealthLabel(new Label());
        setInventoryLabel(new Label());
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
        return ui;
    }

    private BorderPane prepareBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(getCanvas());
        borderPane.setRight(getUi());
        return borderPane;
    }


    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        inventoryLabel.setText("Inventory: " + game.getCurrentMap().getPlayer().getInventory().toString());
        drawItem();

        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Check if the clicked coordinates are within the bounds of any item
            for (Map.Entry<Point2D, Item> entry : itemMap.entrySet()) {
                Point2D itemPosition = entry.getKey();
                Item item = entry.getValue();
                // Adjust the comparison logic according to your needs
                if (mouseX >= itemPosition.getX() && mouseX < itemPosition.getX() + TILE_WIDTH &&
                        mouseY >= itemPosition.getY() && mouseY < itemPosition.getY() + TILE_WIDTH) {
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
            Point2D itemPosition = new Point2D(startX * TILE_WIDTH, startY * TILE_WIDTH);
            Tiles.drawTile(context, item, startX, startY);
            itemMap.put(itemPosition, item);
            startX += 2; // Increase the x-coordinate for each item
        }
    }
}
