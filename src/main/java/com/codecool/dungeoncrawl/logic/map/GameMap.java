package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class GameMap implements Serializable {
    private int width;
    private int height;

    @Getter @Setter
//    @JsonManagedReference
    private Cell[][] cells;
    private Player player;
    private List<Monster> monsters;


    public GameMap(Player player, int width, int height, CellType defaultCellType) {
        this.player = player;
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void removeMonsters(Actor actor) {
        this.monsters.remove(actor);
    }
}
