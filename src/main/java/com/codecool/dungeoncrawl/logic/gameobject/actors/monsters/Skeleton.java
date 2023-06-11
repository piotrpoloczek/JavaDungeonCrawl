package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

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
        attack(actor);
    }

    @Override
    public void action(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            fight(player);
        }
    }

}
