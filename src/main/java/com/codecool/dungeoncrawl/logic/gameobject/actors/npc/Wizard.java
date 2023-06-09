package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;

public class Wizard extends Actor {

    public Wizard(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "wizard";
    }

    @Override
    protected void fight(Actor actor) {

    }

    @Override
    protected void action(GameObject gameObject) throws NewLevelException {
        System.out.println("you met wizard");
    }
}
