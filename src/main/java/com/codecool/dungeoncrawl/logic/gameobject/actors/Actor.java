package com.codecool.dungeoncrawl.logic.gameobject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;

public abstract class Actor extends GameObject {

    private int health = 10;
    private int attack = 2;
    private int defense = 5;


    public Actor(int health){
        super();
        this.health = health;
    }

    public Actor(Cell cell) {
        super(cell);
    }

    public Actor(Cell cell, int health) {
        super(cell);
        this.health = health;
    }

    public void move(int dx, int dy) throws NewLevelException {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getSomething() != null) {
            System.out.println("there is something");
            GameObject gameObject = nextCell.getSomething();
            action(gameObject);
        } else if (nextCell.getType().equals(CellType.FLOOR)) {
            cell.setSomething(null);
            nextCell.setSomething(this);
            cell = nextCell;
        }
    }

    public void attack(Actor actor){
        System.out.println("I attacked the " + actor + "health points: " + actor.getHealth());
        System.out.println("My health points: " + this.getHealth());

        actor.defense(this.attack);
        if (actor.isAlive()) {
            actor.counterAttack(this);
        } else {
            actor.cell.setSomething(null);
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

    protected abstract void action(GameObject gameObject) throws NewLevelException;
}
