package com.codecool.dungeoncrawl.logic.something.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.something.Something;

public abstract class Actor extends Something {

    private int health = 10;

    public Actor(Cell cell) {
        super(cell);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (nextCell.getActor() != null) {
//            Actor actor = nextCell.getActor();
//            fight(actor);
        if (nextCell.getSomething() != null) {
            Something something = nextCell.getSomething();
            action(something);
            // action depending on what it is we can use
            // the method with different arguments
        }
        else if (nextCell.getType().equals(CellType.FLOOR)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    protected abstract void fight(Actor actor);

    protected abstract void action(Something something);
}
