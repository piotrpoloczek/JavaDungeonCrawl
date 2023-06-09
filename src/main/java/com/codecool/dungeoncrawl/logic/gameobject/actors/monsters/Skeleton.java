package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;

public class Skeleton extends Monster {
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
            this.attack(actor);
        }
    }

    @Override
    protected void action(GameObject gameObject) {
        fight((Actor) gameObject);
    }

}