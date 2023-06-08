package com.codecool.dungeoncrawl.logic.gameobject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.StairsDown;
import com.codecool.dungeoncrawl.logic.inventory.Inventory;

public class Player extends Actor {

    Inventory inventory;

    private static int PLAYER_HEALTH = 50;

    public Player(Cell cell) {
        super(cell, PLAYER_HEALTH);
        this.inventory = new Inventory();
    }

    public Player(){
        super(PLAYER_HEALTH);
        this.inventory = new Inventory();
    }

    public String getName() {
        return "piotr";
    }


    @Override
    protected void fight(Actor actor) {
        this.attack(actor);
    }

    @Override
    protected void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Actor) {
            // private method for fighting with the monsters
            fight((Actor) gameObject);
        } else if (gameObject instanceof StairsDown) {
            // go to next level
            // use exception?
            throw new NewLevelException();

        } else if (gameObject instanceof Item) {
            // take item from the ground
            // get item in to inventory
            inventory.putItemToInventory((Item) gameObject);
            gameObject.getCell().setGameObject(null);
        }
    }


    // methods in order to use when we want to apply different action
    // depending on the argument we have
    protected void action(Actor actor) {

    }

    protected void action(Player player) {

    }

    protected void action(Item item) {

    }

    public String getTileName() {
        return "player";
    }
}
