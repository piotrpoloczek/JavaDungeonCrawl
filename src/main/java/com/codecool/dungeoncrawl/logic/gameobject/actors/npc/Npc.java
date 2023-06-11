package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;

public abstract class Npc extends Actor {
    public Npc(int health) {
        super(health);
    }

    public Npc(Cell cell) {
        super(cell);
    }

    public Npc(Cell cell, int health) {
        super(cell, health);
    }

    @Override
    protected void fight(Actor actor) {
        // there is no fight in this class
    }
}