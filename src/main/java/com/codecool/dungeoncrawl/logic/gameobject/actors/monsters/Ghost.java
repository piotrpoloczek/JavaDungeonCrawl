package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class Ghost extends Monster {
    private int health = 25;

    public Ghost(Cell cell) {
        super(cell);
        setHealth(health);
        setAttack(3);
        setDefense(4);
        setDexterity(5);
        setExpReward(15);
    }

    @Override
    public String getTileName() {
        return "ghost";
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
