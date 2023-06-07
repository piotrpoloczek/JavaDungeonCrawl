package com.codecool.dungeoncrawl.logic.something.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.something.Something;

public abstract class Actor extends Something {

    private int health = 10;
    private int attack = 2;
    private int defense = 5;

    public Actor(Cell cell) {
        super(cell);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getSomething() != null) {
            Something something = nextCell.getSomething();
            action(something);
        } else if (nextCell.getType().equals(CellType.FLOOR)) {
            cell.setSomething(null);
            nextCell.setSomething(this);
            cell = nextCell;
        }
    }

    public void attack(Actor actor){
        System.out.println("I attacked the " + actor);
        actor.defense(this.attack);
        if (actor.isAlive()) {
            actor.counterAttack(this);
        }
    }

    private void counterAttack(Actor actor) {
        actor.defense(this.attack);
    }

    public void defense(int attack) {
        health = this.health - attack;
    }

    public boolean isAlive() {
        return health > 0;
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
