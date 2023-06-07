package com.codecool.dungeoncrawl.logic.something.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.something.Something;
import com.codecool.dungeoncrawl.logic.something.items.Item;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    protected void fight(Actor actor) {
        // implement fight with the actor
    }

    @Override
    protected void action(Something something) {
        if (something.getClass().equals(Actor.class)) {
            // fight with the monsters
            // private method for fighting with the monsters
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
