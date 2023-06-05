package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.cell.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;


public abstract class Actor implements Drawable {
    @Getter
    private Cell cell;
    @Getter
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
