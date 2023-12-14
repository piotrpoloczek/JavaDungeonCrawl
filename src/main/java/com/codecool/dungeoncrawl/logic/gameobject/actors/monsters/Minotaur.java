package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class Minotaur extends Monster {
    private int health = 30;

    public Minotaur(Cell cell) {
        super(cell);
        setHealth(health);
        setAttack(4);
        setDefense(4);
        setDexterity(3);
        setExpReward(20);
    }

    @Override
    public String getTileName() {
        return "minotaur";
    }

    @Override
    protected void fight(Actor actor) {
        attack(actor);
    }

    @Override
    public GameEvent action(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            fight(player);
        }

        return null;
    }

}
