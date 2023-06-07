package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.something.Something;
import com.codecool.dungeoncrawl.logic.something.actors.Actor;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;

    private Something something;

    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Something getSomething() {
        return something;
    }

    public void setSomething(Something something) {
        this.something = something;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
