package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

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
}
