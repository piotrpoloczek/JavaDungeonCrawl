package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;

        public Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }

        public Tile(int i, int j, int w, int h) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            this.w = w;
            this.h = h;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(1, 1));
        tileMap.put("floor", new Tile(1, 4));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(12, 10, 32, 64));
        tileMap.put("stairs down", new Tile(28, 6));
        tileMap.put("apple", new Tile(15, 29));
        tileMap.put("wizard", new Tile(26, 0));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("door", new Tile(20, 12));
        tileMap.put("gold", new Tile(9, 26));
        tileMap.put("stairs heaven", new Tile(1, 10));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static void drawPlayer(GraphicsContext context, Drawable d, int x, int y) {
        Image image = new Image("classes/mage.png");
        context.drawImage(image, x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
