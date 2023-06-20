package com.codecool.dungeoncrawl.logic.gameobject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.messages.Message;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;
import com.codecool.dungeoncrawl.view.Tiles;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter @Setter
public abstract class Actor extends GameObject {

    private int health;
    private int attack;
    private int defense;
    private int dexterity;

    private Rectangle solid;
    private int worldX, worldY;
    private int speed;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Actor(int health){
        super();
        this.health = health;

        this.worldX = cell.getX() * Tiles.TILE_WIDTH;
        this.worldY = cell.getY() * Tiles.TILE_WIDTH;
    }
    public Actor(int health, int defense){
        super();
        this.health = health;
        this.defense = defense;
    }

    public Actor(Cell cell) {
        super(cell);
    }


    public Actor(Cell cell, int health) {
        super(cell);
        this.health = health;
    }

    public Actor(Cell cell, int health, int defense) {
        super(cell);
        this.health = health;
        this.defense = defense;
    }

    public void move(int dx, int dy) throws GameException {

        // check next coordinates in pixels
        int nextX = this.worldX + dx * Tiles.TILE_WIDTH;
        int nextY = this.worldY + dy * Tiles.TILE_WIDTH;


        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getGameObject() != null) {
            GameObject gameObject = nextCell.getGameObject();
            action(gameObject);
        }
        else if (nextCell.getType().equals(CellType.FLOOR)) {
            cell.setGameObject(null);
            nextCell.setGameObject(this);
            cell = nextCell;
        }

        // move result
    }

    public void attack(Actor actor){
        Message.getInstance().setActualMessage(this.getName() +" is attacking " + actor.getName());
        System.out.println( this.getName() + " is attacking " + actor.getName());

        if(isDefenceSuccessful(actor)) {
            Message.getInstance().setActualMessage(actor.getName() + " pushes back the attack!");
            System.out.println(actor.getName() + " pushes back the attack!");
            System.out.println();
        }
        else {
            int damage = setHarm(actor, attack);
            Message.getInstance().setActualMessage("Damage: " + damage + ", " + actor.getName()
                    + " health: " + actor.getHealth());
            System.out.println("Damage: " + damage + ", " + actor.getName() + " health: "
                    + actor.getHealth());
            System.out.println();
        }

        if (actor.isAlive()) {
            actor.counterAttack(this);
        }
        else {
            Message.getInstance().setActualMessage(actor.getName() + " died!");
            System.out.println(actor.getName() + " died!");
            actor.cell.setGameObject(null);
        }
    }

    private void counterAttack(Actor actor) {
        actor.defense(this.attack);
    }

    public void defense(int attack) {
        health = this.health - attack;
    }

    private int setHarm(Actor actor, int damage) {
        if(isHitCritical()) {
            Message.getInstance().setActualMessage("Critical hit!");
            System.out.println("Critical hit!");
            actor.setHealth((actor.getHealth() - damage * 2));
            damage *= 2;
        }
        else {
            actor.setHealth(actor.getHealth() - damage);
        }
        return damage;
    }

    private boolean isHitCritical() {
        int diceThrow = RandomGenerator.throwADice() + RandomGenerator.throwADice();
        return diceThrow * 10 >= 110 - dexterity * 4;
    }

    private boolean isDefenceSuccessful(Actor actor) {
        int diceThrow = RandomGenerator.throwADice() + RandomGenerator.throwADice();
        return diceThrow * 10 >= 110 - actor.getDefense() * 4;
    }

    public boolean isAlive() {
        return health > 0;
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

    public String getName() {
        return this.getClass().getSimpleName();
    }

    protected abstract void fight(Actor actor);

    public abstract void action(GameObject gameObject) throws GameException;
}
