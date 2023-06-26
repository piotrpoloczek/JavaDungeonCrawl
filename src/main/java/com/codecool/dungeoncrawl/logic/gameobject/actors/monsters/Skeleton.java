package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import lombok.Getter;
import lombok.Setter;

public class Skeleton extends Monster {

    private int health = 20;

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(health);
        setAttack(2);
        setDefense(2);
        setDexterity(2);
        setExpReward(10);
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
    public GameEvent action(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            fight(player);
        }

        return null;
    }
}
