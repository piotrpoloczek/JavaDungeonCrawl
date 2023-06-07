package com.codecool.dungeoncrawl.logic.something.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.something.Something;
import com.codecool.dungeoncrawl.logic.something.items.Item;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    protected void fight(Actor actor) {
        if (actor.getClass().equals(Player.class)) {
            // this should result in fight with player
        }
    }

    @Override
    protected void action(Something something) {

    }

    protected void action(Actor actor) {

    }

    protected void action(Player player) {

    }

    protected void action(Item item) {

    }
}
