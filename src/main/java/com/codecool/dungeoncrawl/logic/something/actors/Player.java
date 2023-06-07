package com.codecool.dungeoncrawl.logic.something.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.something.Something;
import com.codecool.dungeoncrawl.logic.something.items.Item;
import com.codecool.dungeoncrawl.logic.something.items.StairsDown;

public class Player extends Actor {

    private static int PLAYER_HEALTH = 50;

    public Player(Cell cell) {
        super(cell, PLAYER_HEALTH);
    }


    @Override
    protected void fight(Actor actor) {
        this.attack(actor);
    }

    @Override
    protected void action(Something something) throws NewLevelException {
        if (something instanceof Actor) {
            // private method for fighting with the monsters
            fight((Actor) something);
        } else if (something instanceof StairsDown) {
            // go to next level
            // use exception?
            throw new NewLevelException();

        } else if (something instanceof Item) {
            // take item from the ground
            // get item in inventory
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
