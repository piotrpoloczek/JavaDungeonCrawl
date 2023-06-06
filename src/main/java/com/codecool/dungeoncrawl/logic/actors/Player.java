package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    protected void fight(Actor actor) {
        // implement fight with the actor
    }

    public String getTileName() {
        return "player";
    }
}
